# Engineering Learning Log

## Date
2026-09-06

## Current Objective
“Given a REST API operation I need, I can locate its official documentation, map it into the Java client library, determine the required classes/imports, and make the request without being handed the completed Java statement.”

## Relevant Requirement
```markdown
OAuth client configuration
        ↓
authorization flow
        ↓
resource owner grants gmail.readonly
        ↓
Credential
```

1. Which OAuth object now represents the user's authorized state?
> The `Credential` object which is instantiated from `new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");`
2. What is the difference between the authorization server and the Gmail resource server?
> The authorization server handles the authentication and authorization for the client to receive credentials to use for each request to using the resource owner's resources, whereas the Gmail resource server hosts the actual resources that the client is requesting for, to work with.
3. Why did adding google-api-services-gmail make Gmail Java classes compilable but not automatically authorize requests?
> Adding `google-api-services-gmail` made Gmail Java classes compilable, as we were able to import that class into our project from the dependency defined in our `build.gradle` file so our compiler can define the Gmail class, however this did not authorize our request to use the resource owner's Gmail as we didn't request nor was approved for access to explicit authority to their resources.
4. What does `.execute()` sound like it should represent when using an API client library?
> ~~`.execute()` sounds like it'll run the specific request it's connected to, to the API client library and receives a response with a payload from the resource.~~ / `.execute()` invokes the API request causing the client library to communicate with the remote resource server and return the server's response

## Current Mental Model
1. The Gmail service object will probably need some way to know how to:
- communicate over HTTP,
- parse JSON,
- and attach authorization to its requests.

Which objects you've already created seem likely to satisfy those roles? 
> `httpTransport`, `JSON_FACTORY`, and a `Credential` object
2. If we want to prove authenticated Gmail connectivity without touching individual messages, what kind of Gmail endpoint would be safer than `messages.list`?
> A Gmail endpoint which only reads metadata?
3. At the HTTP level, would you expect retrieving account/profile information to be a GET, POST, or DELETE?
> GET; we're not requesting to update nor delete any data inside the Google Server, so I'd assume attempting to retrieve information would require GET

## Knowledge Gaps
```markdown
Credential
    ↓
Gmail client
    ↓
Gmail request
    ↓
Gmail resource server
    ↓
response
```

## Targeted Learning
```markdown
authorized Credential
        +
HTTP transport
        +
JSON handling
        ↓
Gmail service client
        ↓
users resource
        ↓
one API operation
        ↓
request object
        ↓
execute()
        ↓
response model
```

## Decisions Made
### Decision
Investigated, discovered and experimented with API imports and classes instead of allowing ChatGPT to give me what's needed.

### Reason
Taught me to navigate documentation, how to read and understand it, and implement against other sample code.

## Commands and Reusable References
```markdown
**A reusable API-discovery algorithm**

1. State the capability I need.

2. Find the REST/API operation.
   - method
   - path
   - parameters
   - response
   - authorization

3. Find the language client root service.

4. Follow the resource hierarchy.
   REST: users.getProfile
   Java: service.users().getProfile(...)

5. Identify:
   REQUEST TYPE
   RESPONSE TYPE

6. Determine where execution occurs.
   Usually something like execute()/send()/await().

7. Use the type signatures to determine required inputs.

8. If composition is unclear:
   consult an official sample.

9. Return to reference docs to verify
   what the sample is doing.

10. Compile/run one minimal request.

11. Explain the full data path in my own words.
```
- [API Discovery Worksheet](https://docs.google.com/document/d/1V3NPPys2Qn3lPT33-EIvPsbz3SkxJjKm8fkxUOXc12I/edit?usp=sharing)
- [Google Quickstart - Create and send draft emails](https://developers.google.com/workspace/gmail/api/guides/drafts)
- [Gmail API Reference - Method: users.getProfile](https://developers.google.com/workspace/gmail/api/reference/rest/v1/users/getProfile)
- [Gmail API Javadocs](https://googleapis.dev/java/google-api-services-gmail/latest/overview-summary.html)
