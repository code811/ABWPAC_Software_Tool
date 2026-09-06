# Engineering Learning Log

## Date
2026-09-05

## Current Objective
How our desktop Java application turns its client configuration and requested scopes into a user-authorized Credential object.

And, importantly:
Why that Credential is different from GoogleClientSecrets.

## Relevant Requirement

### Domain map
We currently have:
```
credentials.json
↓
GoogleClientSecrets

gmail.readonly
↓
requested authority
```
This lesson fills that ?.

### Activate prior knowledge
Recall:
- The client ID identifies the application.
- gmail.readonly is the authority we're requesting, not authority we already possess.
- The authorization server handles user authentication/consent and token issuance.
- Gmail is the resource server.
- A desktop application can receive Google's callback through a loopback HTTP listener.

## Current Mental Model
1. If the user has already authorized the application once, why might we want to save something locally instead of opening the consent screen every run?
> Because in situations where the user isn't available to grant consent, the program will be blocked from running its operations; that is why we must store refresh tokens which are longer-lived so that the resource owner only has to consent once for the program to continuously generate access tokens afterward until the refresh token expires.
2. Which is more likely to be persisted locally: the authorization code or a refresh token?
> The refresh token is more likely to persist locally, as the authorization code is short-lived and will die as soon as it's used for an access and refresh token through the authorization system.
3. Does GoogleAuthorizationCodeFlow itself represent a logged-in user, or does it describe how authorization should occur?
> I believe `GoogleAuthorizationCodeFlow` describes how authorization should occur.
4. What do you expect the local Jetty receiver to do after it receives Google's redirect—stay running forever or shut down after the flow completes?
> ~~I expect that the local Jetty receiver shuts down after the flow completes, as it temporary hosts the communication of the Google server to the application.~~ / The Jetty receiver does shut down after the flow, but it receives the browser’s local redirect rather than hosting communication from Google directly.

## Knowledge Gaps
```
GoogleClientSecrets
↓
?
↓
resource owner consents
↓
access token
```

## Targeted Learning
```markdown
GoogleClientSecrets
        +
SCOPES
        +
HTTP transport
        +
token storage
        ↓
GoogleAuthorizationCodeFlow
        ↓
AuthorizationCodeInstalledApp
        +
LocalServerReceiver
        ↓
browser consent
        ↓
Credential
```

## Planned Implementation

## Decisions Made
### Decision

### Reason

## Commands and Reusable References