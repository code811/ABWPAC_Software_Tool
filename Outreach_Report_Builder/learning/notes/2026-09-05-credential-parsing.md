# Engineering Learning Log

## Date
2026-09-05

## Current Objective
How does the program move from an application OAuth configuration file to a configured authorization process, while still having no Gmail access yet

## Relevant Requirement
1. What does credentials.json identify?
> It identifies the client sending the request inside the Google Cloud project.
2. What does gmail.readonly limit?
> Limits the authority the client has and can access inside the resource server.
3. Why do we need an authorization server before contacting Gmail?
> The client needs to request for permission by the resource owner to access specific scopes and functions from the resource server, and the authorization server handles this transaction of authority.
4. Why can't an access token simply be hard-coded into our application?
> The access token is short-lived and dies shortly after being generated.

## Current Mental Model
1. credentials.json is JSON. What two general operations must Java perform before it can use those values as objects?
> I believe we'd need to parse the JSON file, and then we'd need to store the data somehow; in a class or collection
2. At what point should our program state that it wants gmail.readonly: when loading credentials.json, or when configuring the authorization request?
> ~~I believe when loading the credentials.json file is when the program will state that it wants gmail.readonly, as the credentials themselves identify that they only grant/request for gmail.readonly access.~~ / When configuring the authorization request, the program decides what authority it is requesting.
3. Suppose authorization succeeds today and you rerun the program tomorrow. Would you want the user to manually authorize every execution? What might our program need to persist?
> The user should not have to manually authorize every execution; instead we should expect to receive a refresh token after transacting with the authorization server for the first access token, then we can repeatedly use the refresh token for more access tokens as it's longer-lived.

## Knowledge Gaps
GsonFactory
- JSON parser

GoogleClientSecrets
- Java representation of credentials.json

SCOPES
- requested delegated authority

GoogleAuthorizationCodeFlow
- configuration for performing OAuth

## Targeted Learning
GsonFactory
- Google uses `GsonFactory.getDefaultInstance()` through the `JsonFactory` abstraction.
- raw JSON text → JSON parser/factory → Java representation

GoogleClientSecrets
- Java representation of credentials.json

SCOPES
- requested delegated authority

GoogleAuthorizationCodeFlow
- configuration for performing OAuth

## Planned Implementation


## Decisions Made
### Decision

### Reason

## Commands and Reusable References
```markdown
Everything located inside src is packaged for distribution, so anything local shouldn't live inside src/
`src/main/java` is the Java source which belongs to Gradle's main source set
`src/main/resources` are where non-Java files that the application's main code needs at runtime

GmailScopes.GMAIL_READONLY
↓
one String representing our Gmail scope

Collections.singletonList(...)
↓
put that one scope into a one-item List

SCOPES
↓
list of authority our OAuth flow will request
```