# Engineering Learning Log

## Date
2026-08-13

## Current Objective
Understand Gmail OAuth scopes and why least privilege and data minimization are used to secure sensitive data.

## Relevant Requirement
Finish Lesson 6 from ChatGPT generated curriculum on building projects: [Lesson 6 - OAuth Scope Selection and Least-Privilege](https://docs.google.com/document/d/1tOshSJdgY2_RoZXvKwjcxA_OZRXB3ss34RcjU0W437A/edit?tab=t.lu25jtem4bpt#heading=h.3hjpvzh9t3d).

## Current Mental Model
Least privileged encourages to use the most minimal scope that reasonably accomplishes the task while data minimization states to only handle the data that's needed to fulfill the requirements despite the other data capabilities accessible by the scope. 
Gmail operations currently required by the candidate design are from the `v1.users.messages` resource: `get` and `list`.
`gmail.metadata` returns headers/metadata and labels, while `gmail.readonly` gives authority to view more sensitive information regarding the email such as the message body. 
`gmail.labels` alone are insufficient, as the specifications ask for total outreach emails within a week, and `gmail.labels` doesn't have an operation/resource which handles time.
The `q` query parameter is not accessible with the `gmail.metadata` credential; must do more retrieval-work to filter for time instead of using Gmail's built-in time system.
The performance/quota consequence of attempting to filter for time is that for each historic email considered, each must be check to see if it's in this time interval, becoming costly as emails/operations scale.

## Knowledge Gaps
- How do you import the API resource into the program?
- How to set up a Gradle environment?
- Is the OAuth scope global for the project?
- What happens if one part of the program needs more access than others?

## Targeted Learning
- Understand Gmail OAuth scopes.
- Distinguish the authority of each scope.
- Apply why least privilege is prevalent when determining scope.
- Explain how least privilege does not necessarily translate to smallest scope.
- Explain how specifications can change the context of least privilege.
- Understand data minimization, especially when scope is restricted.
- Explain the scope selection process.

## Planned Implementation
1. Finish the [Lesson 6 - OAuth Scope Selection and Least-Privilege](https://docs.google.com/document/d/1tOshSJdgY2_RoZXvKwjcxA_OZRXB3ss34RcjU0W437A/edit?tab=t.lu25jtem4bpt#heading=h.3hjpvzh9t3d) notes.
2. Finish the gmail-scopes.md engineering notes
3. Finish the Lesson 6 retrieval exercises
4. Close [Issue #6 Learning: Evaluate Gmail OAuth scopes and least privilege](https://github.com/code811/ABWPAC_Software_Tool/issues/6)

## Decisions Made
### Decision
Determined that the Gmail OAuth scope should be `gmail.readonly` for `users.messages.list` when creating the first technical spike.
### Reason
Specifications asked to validate that the outreach email follows the template enforced by the Outreach Department for the email to be considered. `gmail.readonly` is the least privileged functionally as it allows program to receive the message body.

## Commands and Reusable References
[Lesson 6 - OAuth Scope Selection and Least-Privilege](https://docs.google.com/document/d/1tOshSJdgY2_RoZXvKwjcxA_OZRXB3ss34RcjU0W437A/edit?tab=t.lu25jtem4bpt#heading=h.3hjpvzh9t3d)