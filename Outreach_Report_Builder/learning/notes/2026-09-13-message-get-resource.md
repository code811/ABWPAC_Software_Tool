# Engineering Learning Log

## Date
2026-09-13

## Current Objective
Why messages.get is separate from messages.list, what format=full gives us, how Gmail represents MIME as MessagePart objects, and why extracting the body may require traversing a tree rather than reading one field.

## Relevant Requirement
```markdown
searchMessages(...)
    ↓
List<Message>
    ↓
each Message currently gives you mainly:
    id
    threadId
```

## Current Mental Model
1. If an email can contain plain text, HTML, inline images, and attachments, does it make sense for Gmail to expose the body as one simple String?
> From the way it's being ask no? But I would personally assume so, since all of this document is technically one big string--simple, no--but it still could be one string.
2. If Gmail already has a parsed representation of the email structure, would you rather use that or ask for the entire raw RFC email and parse everything yourself?
> I'd expect that it depends on the situation, especially given the fact they give both options--however for this project I'd want to use the already parsed representation of the email structure.
3. If the message body is encoded for transport, what extra transformation will be needed before your Java code can compare it to the outreach template?
> The message body would have to be deserialized or decoded before my Java code can compare it to the outreach template.

## Knowledge Gaps
```markdown
message ID
    ↓
messages.get
    ↓
full Message resource
    ↓
payload
    ↓
MIME structure
    ↓
actual textual content
```

## Targeted Learning
```markdown
REST operation:
users.messages.get

Java request path:
?

required arguments:
?
?

method controlling response format:
?

response Java type:
?

method exposing root MIME structure:
?

root MIME Java type:
?
```

## Planned Implementation
```markdown
1 candidate Message reference
        ↓
extract ID
        ↓
messages.get(..., id)
        ↓
request FULL
        ↓
execute
        ↓
Message
        ↓
payload
        ↓
print structural MIME information only
```

## Decisions Made
### Decision

### Reason


## Commands and Reusable References
- [Gmail Method: users.messages.get RESTful Document](https://developers.google.com/workspace/gmail/api/reference/rest/v1/users.messages#Message.MessagePart)
- [Gmail Message Model Javadoc](https://googleapis.dev/java/google-api-services-gmail/latest/com/google/api/services/gmail/model/Message.html)
- [Gmail Messages.Get Service Javadoc](https://googleapis.dev/java/google-api-services-gmail/latest/com/google/api/services/gmail/Gmail.Users.Messages.Get.html#setId-java.lang.String-)

| Question                          | Your finding                                                             |
|-----------------------------------|--------------------------------------------------------------------------|
| REST operation                    | `users.messages.get`                                                     |
| HTTP method/path                  | `GET https://gmail.googleapis.com/gmail/v1/users/{userId}/messages/{id}` |
| Required path parameters          | `userId` : string && `id` : string                                       |
| Java request path                 | `gmailServiceObject.users().messages().get(userId, message.id)`          |
| Java request type                 | `Gmail.Users.Messages.Get`                                               |
| Java response type                | `Message`                                                                |
| Response format we chose          | `full`                                                                   |
| Why not `metadata`?               | Does not give us the body.                                               |
| Why not `raw` initially?          | `raw` requires that we maually parse ourselves; extra meaningless work   |
| Method returning root payload     | `messageObject.getPayload()`                                             |
| Java root payload type            | `MessagePart`                                                            |
| Method returning MIME type        | `messagePartObject.getMimeType()` : `String`                             |
| Method returning child parts      | `messagePartObject.getParts()` : `List<MessagePart>`                     |
| Java body type                    | `MessagePartBody`                                                        |
| Is body data plain text directly? | No, we must decode the data before we can work with it.                  |
| Can child parts be absent?        | Yes, there's a chance it may return null.                                |