# Engineering Learning Log

## Date
2026-08-11

## Current Objective
Understand why a Java project uses a build and dependency-management tool before introducing the Google libraries required for the Gmail API spike.

## Relevant Requirement
Finish Lesson 4 from ChatGPT generated curriculum on building projects: [Lesson 4 - Gradle and Dependency Management](https://docs.google.com/document/d/19F8V8txYxRlcTwdAvCMC_LJJuaOuN9XP-9GrwMiVEdk/edit?tab=t.0).

## Current Mental Model
Dependencies are external logic which can be borrowed by importing into a project. Transitive dependencies are dependencies which require their own dependencies to function properly. By manually managing all the dependencies you download via JAR files, you must include each import and it's classpath for each launch that you do the build the project. This scales out of hand as now, not only do you have to keep track of the multitude of class files that you compiled, but you have to manage the imported dependencies as well. `javac` is how Java compiles code into .class files, `java` is how the JVM launches and executes the software, and Gradle is a build tool which uses these Java mechanics and streamlines the process for compilation and building of programs. Gradle tasks represent each process that can take place when building the program. Gradle plugin adds capabilities to Grade, giving it more features specific for that project. A GitHub repository stores source code; a depdency repository stores the libraries which resolve dependencies. `implementation` means the project requires the artifact for the dependencies that it uses. Gradle Wrapper should be used, as it configures the Gradle version to the one used by the project, to ensure reproducibility of the project. Wrapper files are committed while build/ output is normally not, as what's committed to version control should support the reproducability of the development environment. Wrapper files ensure that the version is consistent for each local repository that's cloned from the main, so it's a necessity to commit, while build/ is something which should be reproduced each time the software is run, so it isn't needed to be committed.

## Knowledge Gaps
- What's the difference between Build Tools?
- What makes Gradle stand out?
- What are the other functionalities of Gradle?
- How to run Gradle on IntelliJ?
- How to actively use Gradle in production?

## Targeted Learning
- Explain why javac and java alone become inconvenient when a project has external dependencies.
- Explain what a dependency is.
- Explain what a transitive dependency is.
- Understand how dependencies relate to the Java classpath.
- Explain the purpose of Gradle.
- Understand Gradle tasks, plugins, and dependency repositories at a conceptual level.
- Explain the purpose of the Gradle Wrapper.
- Distinguish build configuration from generated build output.

## Planned Implementation
1. Finish the [Lesson 4 - Gradle and Dependency Management](https://docs.google.com/document/d/19F8V8txYxRlcTwdAvCMC_LJJuaOuN9XP-9GrwMiVEdk/edit?tab=t.0) notes.
2. Finish the gradle-dependencies.md engineering notes
3. Finish the Lesson 4 retrieval exercises
4. Close [Issue #4 Learning: Understand Gradle and dependency management](https://github.com/code811/ABWPAC_Software_Tool/issues/4)

## Decisions Made
### Decision
N/A
### Reason
N/A

## Commands and Reusable References
```groovy
plugins {
    id 'application'
}

repository{
    mavenCentral()
}

dependencies {
    implementation 'some.group:some.library:1.2.3'
}
```