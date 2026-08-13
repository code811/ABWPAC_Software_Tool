# Project Brief

## Project
### Outreach Report Builder

## Problem
The Outreach Department Director relies on self-reporting and manual bookkeeping from outreach team members on the number of outreach emails sent each week.
## Intended Users
- Outreach Department Director
- Outreach Employees

## Current Process
The Outreach Department depends on individual employees, each responsible for their own bookkeeping on the amount of emails they've sent for the week. It is a high-trust system, which relies heavily on individual promises.

## Desired Outcome
Employees no longer have to manually keep track of and log their activities, and the Outreach Director can be more confident about the volume produced by the department.

## Goals
- **<ins>Accessibility</ins>**; the program must be intuitive enough to: understand how to use, and for results to be read
- **<ins>Automation</ins>**; the program must simplify the workflow: making tracking convenient, and results instantaneous
- **<ins>Security</ins>**; the program must prevent leakage of sensitive information: proper management of authorization and privacy, preventing data breaches, distrust, and malicious intent

## Non-Goals
- Visual interpretation of data/results
- Randomized audit functionality of team members
- Additional statistics outside total emails sent for the specified week
- Change of time interval to calculate total emails

## MVP
The program should be granted access to view employee personal Gmails created for this internship--specifically for outreach, account for outreach related emails for the time-frame specified, and produce a report--ideally on Google Sheets--for each employee of the Outreach Department to better gauge engagement. The authorization granted to this program should be handled with care, and must not jeopardize any employee, or the organization

## Core Requirements
- The system shall ask employees for consent and authorization to view emails
- The system shall count total emails used for outreach
- The system shall produce an accurate dataset for a specified week for each employee on number of emails they sent for a chosen week

## Constraints
- Time
- Human resources
- Organizational policies
- User skill level

## Nonfunctional Requirements
- Security
- Performance
- Availability
- Accessibility
- Maintainability
- Privacy

## Success Criteria
The product should correctly produce meaningful data for analysis, reduce the time to record and review emails sent for a chosen week, support the pre-existing workflow, and is intuitive enough to use

## Stakeholders
| Stakeholder       | Concern                                              |
|-------------------|------------------------------------------------------|
| Employee          | Privacy, ease of authorization, accurate attribution |
| Outreach Director | Accurate outreach reporting                          |
| Organization      | Security and appropriate data handling               |
| Developer         | Maintainability and feasible implementation          |


## Risks and Assumptions
### Risk: 
- Data leak
- Privacy
- Authorization
- Accuracy
- Adoption/giving consent
- API dependency
- Maintenance
- Scope
### Assumption:
- Google Sheets builds and stores the report
- Program is given authorization
- Outreach Director wants weekly reports
- Employee personal Gmail accounts created for this internship are properly used for oureach
- One sent Gmail Message = one sent email
- Valid outreach email follows template
- Time frame: Monday-Sunday; reports are created on Monday (May need to update and ask if it should be switched to Saturday)

## Open Questions
- What objectively qualifies an email as legitimate outreach?
- What behavior should cause an email to be excluded from the count?
- What data should be stored or used to build the report?
- What's the protocol for employees who reject granting access to their emails?
- When is the start and end for each week?
- Is it necessary for the report to accept flexible time-frames?
- Should the program run real-time or updated at a specified time/event?
- How frequently should the report update?
- What happens if an employee is offboarded?
- How should each dataset be identified as; personal name or internship gmail account?
- What happens when authorization is revoked or expires after an employee previously connected their Gmail account?
- Does the Director need to manually inspect every qualifying email, or only emails selected during an audit?
- If using a template; should this process be streamlined as well?
- If automating sending emails, what is needed from the user to do so?
- If automating sending emails, how should the program receive this information?
- Do multiple replies in one thread count for one response or multiple?