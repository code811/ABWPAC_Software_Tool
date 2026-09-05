# Engineering Learning Log

## Date
20206-08-30

## Current Objective
What credentials.json represents, why it belongs to the application rather than the Gmail user, and why creating it still does not authorize us to read Gmail.

## Relevant Requirement
Domain map
So far we built:

Java application

↓

Gradle dependencies

↓

Gmail + OAuth libraries

But those libraries still have no idea which application we are from Google's perspective.
We're about to add the missing Google-side configuration:

| GitHub/local project | Google                |
|----------------------|-----------------------|
| Java source          | Cloud project         |
| Gradle               | Gmail API enabled     |
| OAuth libraries      | OAuth client          |
|                      | consent configuration |

Activate prior knowledge

Recall:
1. Is a Google Cloud project the same thing as your GitHub repository?
> A Google Cloud project is not the same thing as a GitHub repository, as a GitHub repository stores source code, project documentation, and configuration files for the application, while a Google Cloud project is a conatiner for Google Cloud/API configuration and resources.
2. What does an OAuth client ID identify?
> OAuth client ID identifies what system is making requests for authorization.
3. Does enabling the Gmail API itself authorize access to your Gmail?
> No. The system still must explicitly request for certain authority from the resource owner, and they must accept for the authorization server to even grant the client an access token to have granted access to the Gmail account.
4. What role does the resource owner play in OAuth?
> The resource owner determines what system is permitted, and how much that system is allowed to access the resource. (capable of granting access to the protected resource)

## Current Mental Model
1. Why can't we simply generate a random client ID inside Java?
> I presume then that the Google Cloud project would then need to have some way of identifying that client ID to make the connection that the system is this client ID.
2. If we enable the Gmail API in Google Cloud, why do we still need user consent?
> The user is the resource owner, connecting the Gmail API in Google Cloud only allows us to work with Google-specific functions/models.
3. Why should this spike use an OAuth Desktop app client rather than a Web application client?
> The spike is scoped to test whether we're able to connect our system to a Google Cloud project and the authorzation server, NOT where the system should be hosted. The Desktop client is more simple than setting up an entire server to host this interaction.

## Knowledge Gaps
Google Cloud project

↓

Gmail API enabled

↓

OAuth app configuration

↓

Desktop OAuth client

↓

credentials.json

↓

our Java program

↓

OAuth authorization flow

## Targeted Learning


## Planned Implementation


## Decisions Made
### Decision


### Reason


## Commands and Reusable References
