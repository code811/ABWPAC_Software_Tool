package gmailapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartBody;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Runs a local OAuth authorization spike for the Gmail API. */
public class Main {
    private static final String APPLICATION_NAME = "Gmail API Spike";

    // JSON factory used by Google client libraries to parse OAuth JSON
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // Filesystem path to the local OAuth client configuration
    private static final Path CREDENTIALS_FILE_PATH =
        Path.of("local", "credentials.json");

    // OAuth scopes that the authorization flow will request
    private static final List<String> SCOPES =
        Collections.singletonList(GmailScopes.GMAIL_READONLY);

    // Directory where per-user authorization state is persisted
    private static final Path TOKENS_DIRECTORY_PATH = Path.of("tokens");

    /**
     * Runs the installed-application OAuth authorization smoke test.
     *
     * @param args command-line arguments; unused by this spike
     * @throws IOException if credential loading, token storage, or OAuth I/O fails
     * @throws GeneralSecurityException if trusted HTTP transport cannot be created
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException {

        // Network transport used by Google's client libraries
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Authorization state usable by API client
        Credential credential = authorize(httpTransport);

        // Construct authorized Gmail service
        Gmail service = new Gmail.Builder(
            httpTransport,
            JSON_FACTORY,
            credential)
            .setApplicationName(APPLICATION_NAME)
            .build();

        String query = "after:2026/9/11 before:2026/9/14";
        long maxResultsPerPage = 55L;
        List<Message> messageReferences = searchMessageReferences(
            service,
            query,
            maxResultsPerPage);
        
        Gmail.Users.Messages.Get messagesGetRequest = service.users().messages()
            .get("me", messageReferences.getFirst().getId())
            .setFormat("full");

        Message firstMessage = messagesGetRequest.execute();
        System.out.println("Message retrieved.");

        MessagePart messageRoot = firstMessage.getPayload();
        System.out.println("Root MIME type: " + messageRoot.getMimeType());
        System.out.println("Root child parts: " + (messageRoot.getParts() != null
            ? messageRoot.getParts().size()
            : 0));

        if (messageRoot.getParts() != null) {
            for (MessagePart messageChild : messageRoot.getParts()) {
                System.out.println("Part:");
                System.out.println("\tMIME type: " + messageChild.getMimeType());
                System.out.println("\tFilename: " + messageChild.getFilename());
                System.out.println("\tBody size: " + messageChild.getBody().getSize());
                System.out.println("\tChild parts: " + (messageChild.getParts() != null
                    ? messageChild.getParts().size()
                    : 0));
            }
        }
    }

    /**
     * Runs the installed-application OAuth flow.
     *
     * @param httpTransport used for OAuth requests
     * @return authorized credential that manages OAuth token state
     * @throws IOException if authorization or persistence fails
     */
    private static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Loads the OAuth client configuration downloaded from Google Cloud
        GoogleClientSecrets clientSecrets;
        try (Reader reader = Files.newBufferedReader(
                CREDENTIALS_FILE_PATH,
                StandardCharsets.UTF_8)) {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
        }

        // Persists and retrieves OAuth token state on disk
        FileDataStoreFactory dataStoreFactory =
            new FileDataStoreFactory(TOKENS_DIRECTORY_PATH.toFile());

        // Describes the rules/configuration for how OAuth should work
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport,
            JSON_FACTORY,
            clientSecrets,
            SCOPES)
            .setDataStoreFactory(dataStoreFactory)
            .setAccessType("offline")
            .build();

        // Local loopback receiver for Google's OAuth redirect
        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
            .setPort(8888)
            .build();

        // Coordinates the installed-app authorization process and returns a Credential
        return new AuthorizationCodeInstalledApp(flow, receiver)
            .authorize("user");
    }

    /**
     * Searches Gmail for messages matching the supplied query and follows
     * pagination until no continuation token remains.
     *
     * @param service authorized Gmail API client
     * @param query Gmail search query
     * @param maxResultsPerPage maximum number of message references requested per page
     * @return message references matching the query; {@code Message} objects contain
     *      {@code id} and {@code threadId}.
     * @throws IOException if a Gmail API request fails
     */
    private static List<Message> searchMessageReferences(
            Gmail service,
            String query,
            long maxResultsPerPage) throws IOException {
        // Creates a consistent filtered search request
        Gmail.Users.Messages.List messageListRequest =
            service.users().messages().list("me")
//                .setLabelIds(Collections.singletonList("SENT"))
                .setQ(query)
                .setMaxResults(maxResultsPerPage);

        ListMessagesResponse messagesResponse;
        List<Message> allMessages = new ArrayList<>();

        String nextPageToken;
        boolean hasNextPage;
        do {
            // Executes one page; maxResults is an upper bound, not a guaranteed page size.
            messagesResponse = messageListRequest.execute();

            List<Message> pageMessages = messagesResponse.getMessages();

            if (pageMessages != null) {
                allMessages.addAll(pageMessages);
            }

            nextPageToken = messagesResponse.getNextPageToken();
            hasNextPage = nextPageToken != null;

            if (hasNextPage) {
                messageListRequest.setPageToken(nextPageToken);
            }

        } while (hasNextPage);

        return allMessages;
    }
}
