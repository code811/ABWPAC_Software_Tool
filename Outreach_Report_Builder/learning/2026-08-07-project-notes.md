# Engineering Learning Log

## Date
2026-08-07

## Current Objective
Start the project and get familiar with Git and GitHub workflow

## Relevant Requirement
Finishing GitHub Skill introductory courses to GitHub and Git

## Current Mental Model
HFollow the project skeleton recommended by ChatGPT and figure out how to properly document and follow the outline provided

## Knowledge Gaps
- How each document included in docs/ should be completed or what they accomplish
- Specific CLI syntax and functions
- How to even start building the tool

## Targeted Learning
- Git
- Bash
- GitHub
- Project Documentation
- SDLC : Planning/Designing and Developing

## Planned Implementation
1. Clone repository started on GitHub through terminal
2. Create the project architecture by following ChatGPT outline
3. Implement through terminal using Bash
4. Create first initial commit for this specific tool
5. Get a template or idea on starting documentation for project with ChatGPT

## Decisions Made
### Decision
Using Terminal and CLI instead of IntelliJ interface

### Reason
Familiarize myself with terminal practices and use.

## Commands and Reusable References
```bash
touch file-path/file-name
mkdir folder-name/
mv file-path/file-name file-path/file-name #mv file-path/{old-file-name,new-file-name}
rm file-path/file-name
cd file-path/file-directory
cp file-path/original-file file-path/copy-file #cp file-path/{original-file,copy-file}.file-extension

#git commands
git --help
git config --global --list
git init
git clone HTTPS
git log #--oneline --graph
git status #--short
git diff #--staged --HEAD~#
git add file-path/file-name
git commit -m "comment"
git branch branch-name
git branch --list
git checkout #branch-name || commit-id
git switch -
git merge --no-ff file-path/file-name -m "comment" #--no-ff merges without fast-forward; optional
```