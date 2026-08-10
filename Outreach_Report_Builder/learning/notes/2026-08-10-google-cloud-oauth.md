# Engineering Learning Log

## Date
2026-08-10

## Current Objective
Understand how a Google Cloud project, enabled APIs, OAuth clients, credentials, and authorization configuration support the outreach reporting system before connecting to Gmail.

## Relevant Requirement
Finish Lesson 3 from ChatGPT generated curriculum on building projects: [Lesson 3 - Google Cloud Projects, OAuth Clients, and Credentials](https://docs.google.com/document/d/1iv4yjKuEd7a_6D1HpNyjTbrI6MWo7sT7UF0fWHV9_ik/edit?usp=sharing)

## Current Mental Model
The Google Cloud Project hosts the Google-side configuration for authorization and deployment. This allows the project source code stored on GitHub and ran on local machines, to integrate with Google APIs and authorization servers to request and gain access to work with users' Google Workspace. Google APIs must be enabled for a project as capabilities must be explicit, not assumed. OAuth client IDs are used as unique identifiers for the multitude of clients that can be integrated within the Google Cloud Project. Redirect URIs are used to know where to return the authorization response to; usually containing the authorization code if successful, or an error. Application OAuth credentials inform clients of their unique identifier inside the Google Cloud Project--thus authentication, while User Access and Refresh Tokens are used to authorize the client on the capabilities distinguished and permitted by the resource owner. Application types affect OAuth configuration as different environments impose different authentication and security practices; a web app may store secret data on a server-side file which is usually inaccessable to users, and a desktop app may store secret data with the download, so it can be assumed that it's inspectable, thus not as secure. Configuration and credentials should not be commited to Git/VCSs as the information would be made public, causing a severe risk. A desktop OAuth client can be appropriate for a technical spike before determining the production architecture as the technical spike is used to determine if the function works, so a simple desktop app is appropriate to test this functionality.

## Knowledge Gaps
- The exact definitions on terminology and vocabulary used

## Targeted Learning
- Explain the purpose of a Google Cloud project.
- Distinguish project source code from Google Cloud configuration.
- Explain why Google APIs must be enabled for a project.
- Understand the purpose of an OAuth client ID.
- Distinguish a client ID from a client secret.
- Explain the purpose of a redirect URI.
- Distinguish application OAuth credentials from user access and refresh tokens.
- Understand why application type affects OAuth configuration.
- Identify configuration and credentials that must not be committed to Git.
- Understand why a desktop OAuth client can be appropriate for a local technical spike without determining the production architecture.

## Planned Implementation
1. Complete the notes [Lesson 3 - Google Cloud Projects, OAuth Clients, and Credentials](https://docs.google.com/document/d/1iv4yjKuEd7a_6D1HpNyjTbrI6MWo7sT7UF0fWHV9_ik/edit?usp=sharing)
2. Review Engineering Notes
3. Answer Retrieval Questions
4. Review GitHub Issue #3: [Learning: Understand Google Cloud and OAuth client configuration](https://github.com/code811/ABWPAC_Software_Tool/issues/3)

## Decisions Made
### Decision
Generalized notes on Google Docs; project specific for engineering notes

### Reason
This allows me to have both concise notes to look back on, but one for general use, and the other with context to this project

## Commands and Reusable References
[Lesson 3 - Google Cloud Projects, OAuth Clients, and Credentials](https://docs.google.com/document/d/1iv4yjKuEd7a_6D1HpNyjTbrI6MWo7sT7UF0fWHV9_ik/edit?usp=sharing)