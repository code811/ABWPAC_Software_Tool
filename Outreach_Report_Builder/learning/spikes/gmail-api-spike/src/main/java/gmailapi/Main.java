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
import com.google.api.services.gmail.GmailScopes;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/** Runs a local OAuth authorization spike for the Gmail API. */
public class Main {
    // JSON factory used by Google client libraries to parse OAuth JSON
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // Filesystem path to the local OAuth client configuration
    private static final Path CREDENTIALS_FILE_PATH = Path.of("local", "credentials.json");

    // OAuth scopes that the authorization flow will request
    private static final List<String> SCOPES =
        Collections.singletonList(GmailScopes.GMAIL_READONLY);

    // Directory where per-user authorization state is persisted
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Runs the installed-application OAuth authorization smoke test.
     *
     * @param args command-line arguments; unused by this spike
     * @throws IOException if credential loading, token storage, or OAuth I/O fails
     * @throws GeneralSecurityException if trusted HTTP transport cannot be created
     */
    public static void main(String[] args)
        throws IOException, GeneralSecurityException {

        System.out.println("Gmail API spike started.");

        // Network transport used by Google's client libraries
        final NetHttpTransport httpTransport =
            GoogleNetHttpTransport.newTrustedTransport();

        // Authorization state usable by API client
        authorize(httpTransport);
        System.out.println("OAuth authorization completed.");
    }

    /**
     * Runs the installed-application OAuth flow.
     *
     * @param httpTransport used for OAuth requests
     * @return authorized credential that manages OAuth token state
     * @throws IOException if authorization or persistence fails
     */
    private static Credential authorize(final NetHttpTransport httpTransport)
        throws IOException {

        // Loads the OAuth client configuration downloaded from Google Cloud
        GoogleClientSecrets clientSecrets = loadClientSecrets();

        // Securely saves and retrieves tokens
        FileDataStoreFactory dataStoreFactory =
            new FileDataStoreFactory(Path.of(TOKENS_DIRECTORY_PATH).toFile());

        // Describes the rules/configuration for how OAuth should work
        GoogleAuthorizationCodeFlow flow =
            new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                JSON_FACTORY,
                clientSecrets,
                SCOPES
            )
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();

        // Temporary loopback receiver that listens-for/captures Google's browser redirect
        LocalServerReceiver receiver =
            new LocalServerReceiver.Builder()
                .setPort(8888)
                .build();

        // Coordinates the installed-app authorization process and returns a Credential
        return new AuthorizationCodeInstalledApp(flow, receiver)
            .authorize("user");
    }

    /**
     * Reads the local OAuth client configuration and parses it.
     *
     * @return parsed Google OAuth client configuration
     * @throws IOException if the file cannot be read or parsed
     */
    private static GoogleClientSecrets loadClientSecrets() throws IOException {
        try (Reader reader =
                 Files.newBufferedReader(
                     CREDENTIALS_FILE_PATH,
                     StandardCharsets.UTF_8)) {
            return GoogleClientSecrets.load(JSON_FACTORY, reader);
        }
    }
}
