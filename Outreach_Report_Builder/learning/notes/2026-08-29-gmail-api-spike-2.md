# Engineering Learning Log

## Date
2026-08-29

## Current Objective
Why does a Gmail application need several libraries when, conceptually, we're only trying to call one external API?

## Relevant Requirement
1. What is the difference between the authorization server and resource server?
> An authorization server handles resource owner grants and client permissions while the resource server hosts the data; data requires specific levels of authority
2. Which credential is actually presented to the Gmail API?
> Access Tokens
3. Why does our desktop spike need somewhere for Google to redirect the browser after authorization?
> Google needs to send information back to the client, so it uses a temporary server to do so.
4. What does an OAuth client ID identify?
> It authenticates, authorizes, and identifies each system which is granted access to a resource server; multiple clients can be granted authorization, so the client ID helps differentiate between all of them.

## Current Mental Model
1. If google-api-services-gmail gives us Java classes for Gmail, would you expect that library alone to handle Google's entire OAuth browser flow?
> Because Google requires a redirect browser after authorization, I would assume that outside of the java classes, it would require that we set up a temporary server to host this exchange.
2. Our desktop program sends the browser to Google, and Google later needs to redirect the authorization result somewhere. What capability might our local application need?
> It needs to be able to set up a temporary server (local callback listener) to host this exchange.
3. If several Google APIs all use OAuth, would it make sense for Gmail, Drive, Calendar, and Sheets libraries to each completely reimplement Google's OAuth machinery?
> Reuse of code to prevent code duplication is a convention, so I would assume that Google reuses their OAuth machinery for all Google Product APIs.

## Knowledge Gaps
Gmail-specific library
↓
defines Gmail API operations/models

Google API client

↓

shared Google API/HTTP infrastructure

OAuth client

↓

authorization flow machinery

Jetty extension

↓

local callback receiver for desktop spike

## Targeted Learning

## Planned Implementation

## Decisions Made

## Commands and Reusable References
