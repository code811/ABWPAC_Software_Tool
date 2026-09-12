# Engineering Learning Log

## Date
2026-09-12

## Current Objective
Given a collection endpoint, you should be able to discover its filters, traverse all result pages correctly, and distinguish candidate retrieval from detailed resource retrieval.

## Relevant Requirement
```markdown
REST contract
    ↓
Java service hierarchy
    ↓
request object
    ↓
.execute()
    ↓
response model
```

## Current Mental Model
1. If Gmail has 11,891 messages and one request can return at most a limited number, what new control-flow problem appears?
> We will have to loop through multiple iterations of each section of the total messages to view all the messages.
2. If our report needs only sent emails from one week, should we retrieve the entire mailbox and filter afterward, or ask Gmail to narrow the candidate set first?
> I believe it would be better to ask Gmail to narrow the candidate set first, as we would be manipulating less data, thus enforcing the data minimization idea, as well as minimizing the space we have to store all the to-be candidates.
3. If messages.list gives you message IDs but not message bodies, can this endpoint alone determine whether an email conforms to the outreach template?
> I would assume we would have to then access the message body by using the message IDs, however there may be a chance we can also filter for search-words via the `q` parameter, as Gmail's filter allows me to filter messages with the template using that filter.

## Knowledge Gaps
- Gmail.Users.Messages.List
- Pagination
- `q` / query

## Targeted Learning
- pagination;
- configurable page size;
- null message lists;
- duplicate IDs;
- thread/message distinctions;
- fixed query ranges;
- response accumulation;
- request-vs-response separation.

## Planned Implementation
```markdown
authenticated Gmail service
        ↓
messages.list

filters:
    user = me
    SENT label
    reporting-period query
        ↓
page 1
page 2
...
page N
        ↓
candidate Message objects

each currently contains primarily:
    id
    threadId
        ↓
later:
messages.get
        ↓
template verification
```

## Decisions Made
### Decision
Initially, while filling out the table, I only thought I'd need the `Messages.List` service, `Messages.Get` service, and `Messages` model. I didn't realize that the invoking the `Messages.List` request returns a `ListMessagesResponse` model. I reread the ChatGPT generation and realized that it hinted towards using `ListMessagesResponse` and I quickly navigated and reviewed the documentation for it. After I completed the table to what it is now, I began connecting the pieces together. I started with creating and invoking the request for `ListMessagesResponse`, then I tried including pagination somehow, but I didn't really know where it fit. I tried fitting the `Message` model in somehow, but it just couldn't see how to create a loop for pagination.

### Reason
I initially assumed that invoking the request would return the entire query response, and then I would have to paginate that. This was around the same time that I found out `ListMessagesResponse` existed, and after a while I ended up with three different variables: `messagesRequest`, `messagesResponse`, and `messages`. I slowly reviewed each documentation for the service and models to then slowly connect the pieces together.

### Resolution
I realized that it is the request that returns the limited response, which I don't know why it took me that long to realize, but after, I repurposed my entire program to now adopt this hypothesis. Instead of creating and invoking the request directly into the `ListMessagesResponse` object in one line, I separated the two operations to `messagesRequest` and `messagesResponse`. This allowed me to keep the request constant with the same filters, while navigating to the different pages of the response using `messagesResponse.getNextPageToken()`. Now that I figured out the pagination process, I decided to use a StringBuilder to save memory, as I remembered that Strings are costly since they create a new String object each reassignment--so this stringBuilder now reassigns itself to the `nextPageToken`, where I can assign the `pageToken` to the `messagesRequest` service to then execute and return that page's response. I created a `List<Messages>` collection to append all the messages into one collection to then use `.size()` for the total messages.

## Commands and Reusable References
```markdown
- [API Discovery Worksheet](https://docs.google.com/document/d/1V3NPPys2Qn3lPT33-EIvPsbz3SkxJjKm8fkxUOXc12I/edit?usp=sharing)
- [Google Quickstart - Create and send draft emails](https://developers.google.com/workspace/gmail/api/guides/drafts)
- [Gmail API Reference - Method: users.getProfile](https://developers.google.com/workspace/gmail/api/reference/rest/v1/users/getProfile)
- [Gmail API Javadocs](https://googleapis.dev/java/google-api-services-gmail/latest/overview-summary.html)
```

| Question                                          | Your finding                                                        |
|---------------------------------------------------|---------------------------------------------------------------------|
| REST operation                                    | `users.messages.list`                                               |
| HTTP method/path                                  | `GET https://gmail.googleapis.com/gmail/v1/users/{userId}/messages` |
| Required path parameter                           | `userId` : string                                                   |
| Java request creation path                        | `GmailServiceObject.users().messages().list(String userId)`;        |
| Java request type                                 | `Gmail.Users.Messages.List`                                         |
| Java response type                                | `ListMessagesResponse`                                              |
| Java message model                                | `Message`                                                           |
| Method for SENT filtering                         | `setLabelIds(List<String> labelIds)` : `Gmail.Users.Messages.List`  |
| Method for search query                           | `setQ(String q)` : `Gmail.Users.Messages.List`                      |
| Method for page size                              | `setMaxResults()` : Long                                            |
| Method for next-page request                      | `setPageToken(String pageToken)` : `Gmail.Users.Messages.List`      |
| Response method giving messages                   | `getMessages()` : List<Message>; from `ListMessagesResponse` model  |
| Response method giving next token                 | `getNextPageToken()` : String; from `ListMessagesResponse` model    |
| Does each listed `Message` contain its full body? | No, it contains `id` and a `threadId`.                              |
| Is `resultSizeEstimate` safe as the final count?  | No.                                                                 |