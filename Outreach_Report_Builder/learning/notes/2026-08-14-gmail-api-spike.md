# Engineering Learning Log

## Date
2026-08-14

## Current Objective

## Relevant Requirement

## Current Mental Model

## Knowledge Gaps

## Targeted Learning
- What a build is
- What Gradle does
- What the Wrapper does
- Build.gradle vs settings.gradle
- Dependencies and repositories
- Generated output vs source/build configuration
- Why .gradle/ and build/ aren't source
- Why Wrapper files are committed

## Planned Implementation

## Decisions Made
### Decision

### Reason

## Commands and Reusable References
```bash
# Gradle
gradle init --type basic
./gradlew tasks
./gradlew build
./gradlew test
./gradlew dependencies

# Gradle build.gradle configured id 'application'
./gradlew compileJava
./gradlew run

# Bash
find build/classes -type f

# Git
git check-ignore -v #--no-index #file-path/file-name
git reglof
```

### gmail-api-spike/
#### Build Definition
- `settings.gradle` is the entry point of the build which defines the source-root and subprojects when applicable
- `build.gradle` stores information on how the project will be built; commonly defines: plugins, dependencies, repositories, tasks, build configurations
- `gradle.properties` holds Gradle-related properties/configuration; build behavior
- `gradle/libs.versions.toml` which is a version catalog (not to be misinterpreted with Git) that centralizes dependency/plugin versions and aliases

#### Reproducible build tooling
- `gradlew` && `gradlew.bat` are how macOS, Linux && WindowOS launch the Gradle Wrapper through their CLI
- `gradle/wrapper/` which holds wrapper related files and configuration containing the Gradle version to use

#### Git configuration
- `.gitignore` declares what to avoid committing to Git
- `.gitattributes` controls Git behavior towards certain tracked files

#### Generated/local state
- `.gradle/` is Gradle's project-specific working/cache directory; LOCAL Gradle working data so it's not needed to be committed
- `build/` is the output of the build process; remember not to commit to VSCs/Git
