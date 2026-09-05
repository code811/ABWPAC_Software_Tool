package gmailapi;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.GmailScopes;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final JsonFactory JSON_FACTORY =
        GsonFactory.getDefaultInstance();

    private static final Path CREDENTIALS_FILE_PATH =
        Path.of("local", "credentials.json");

    private static final List<String> SCOPES =
        Collections.singletonList(GmailScopes.GMAIL_READONLY);

    public static void main(String[] args)
        throws IOException {

        System.out.println("Gmail API spike started.");

        GoogleClientSecrets secrets = loadClientSecrets();
        System.out.println("OAuth client configuration loaded.");
    }

    private static GoogleClientSecrets loadClientSecrets()
        throws IOException {

        try (Reader reader =
            Files.newBufferedReader(CREDENTIALS_FILE_PATH)) {

            return GoogleClientSecrets.load(
                JSON_FACTORY,
                reader
            );
        }
    }
}