# Engineering Learning Log

## Date
2026-08-13

## Current Objective
Understand Gmail's mailbox data model and determine the correct unit and retrieval behavior for the outreach productivity metric before implementing the Gmail API spike.

## Relevant Requirement
Finish Lesson 5 from ChatGPT generated curriculum on building projects: [Lesson 5 - Gmail API Fundamentals: Messages, Threads, Labels, and Pagination](https://docs.google.com/document/d/1cTp-RcgeTO5BcHngMF4Pusy9Ldtz71pHKamaOMju790/edit?usp=sharing).

## Current Mental Model
Gmail classifies a Message as one email sent, as a container for multiple messages. 
Using threads as a unit could produce an incorrect outreach total, as it's hard to distinguish whether there are specific emails which should be counted/considered towards a quantifiable total due to the thread being compared instead. 
The SENT system label represents the emails which have been sent by the user.
`messages.list` returns a collection of message pointers which represent each individual message.
`messages.get` may be necessary as the specifications require that we are able to validate that the outreach message is appropriate and thus, should be considered.
`maxResults`, `pageToken`, `nextPageToken`, demonstrates pagination, and are useful as the API limits the amount of results it can return. `maxResults` informs the total results were queried, `pageToken` identifies the pages, and `nextPageToken` points to the next page after the current page.
`resultSizeEstimate` is not sufficient for an exact report, as relying on an estimate for something which needs to be exact is errornous. 
Date filtering allows for the set time-frame intervals to be queried for and properly calculate the total outreach emails per week.

## Knowledge Gaps
- How do I use Gradle to import the Gmail API into my project?
- How do I read/navigate documentation effectively for APIs/in-general?

## Targeted Learning
- Distinguish Gmail messages from threads.
- Explain how labels relate to individual messages.
- Understand the SENT system label.
- Understand users.messages.list.
- Explain list endpoints versus detail endpoints.
- Understand pagination and page tokens.
- Explain why resultSizeEstimate should not be treated as an exact productivity count.
- Understand the role of date filtering.
- Apply data minimization to the Gmail integration.
- Identify unresolved business assumptions around what qualifies as outreach.

## Planned Implementation
1. Finish the [Lesson 5 - Gmail API Fundamentals: Messages, Threads, Labels, and Pagination](https://docs.google.com/document/d/1cTp-RcgeTO5BcHngMF4Pusy9Ldtz71pHKamaOMju790/edit?usp=sharing) notes.
2. Finish the gmail-api-fundamentals.md engineering notes
3. Finish the Lesson 5 retrieval exercises
4. Close [Issue #5 Learning: Understand Gmail messages, labels, and pagination](https://github.com/code811/ABWPAC_Software_Tool/issues/5)

## Decisions Made
### Decision
N/A
### Reason
N/A

## Commands and Reusable References
[Google Workspace / Gmail / Reference](https://developers.google.com/workspace/gmail/api/reference/rest)