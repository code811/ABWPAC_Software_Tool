# Engineering Learning Log

## Date
YYYY-MM-DD

## Current Objective
What observable result am I trying to produce?

Example:
Allow administrators to create a new project through the dashboard.

## Relevant Requirement
Which user story, requirement, or issue does this support?

## Current Mental Model
How do I currently believe this should work?

Example:
The form sends JSON to the backend. The backend validates it,
inserts the project into the database, and returns the created record.

## Knowledge Gaps
What do I not yet understand?

- How Express parses JSON request bodies
- Where validation should occur
- How PostgreSQL returns an inserted row

## Targeted Learning
What concepts or documentation did I examine?

- Request-body parsing
- Server-side validation
- Parameterized SQL queries
- HTTP 201 responses

## Planned Implementation
1. Create the form
2. Add the POST request
3. Add the backend endpoint
4. Validate the input
5. Insert the database record
6. Return and display the new record

## Decisions Made
### Decision
Validate project names on both the frontend and backend.

### Reason
Frontend validation improves usability, while backend validation
protects the system from malformed or direct API requests.

## Commands and Reusable References
```bash
npm run dev
npm test