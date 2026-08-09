# Engineering Learning Log

## Date
2026-08-09

## Current Objective
Understand Java compilation through use of CLI java and javac.

## Relevant Requirement
Finish Lesson 1 from ChatGPT generated curriculum on building projects: [Lesson 1 - APIs and HTTP: How Software Talks to Software](https://docs.google.com/document/d/1uFTeQxj1xVXnULwXATzOxK9kDHhRN6AZP4m9QnjtL2Q/edit?usp=sharing).

## Current Mental Model
Java compiles its code into `.class` files so that the JVM can read and run. Its classpath must be specified so that the JVM knows where to look to read the `.class` files. By using `javac`, developers are able to compile their `.java` files into java bytecode and specify its destination/directory with `-d`. `java` runs this file, and `-cp` is used to specify the classpath--where the JVM should look to run.

## Knowledge Gaps
- CLI compile/run specific commands

## Targeted Learning
- Understand the difference between `javac` and `java`.
- Compile a `.java` file located outside the project's main `src/` directory.
- Understand where `.class` files are generated.
- Use `-d` to control compilation output.
- Use `-cp` to specify the runtime classpath.
- Run a single Java source file using source-file mode.

## Planned Implementation
### Using `javac` and `java`
1. `cd Outreach_Report_Builder/`
2. `mkdir -p learning/labs/cli-basics/`
3. `touch learning/labs/cli-basics/HelloCli.java` 
4. Create a simple command line output test.
5. `mkdir -p ../out/production/Outreach_Report_Builder/learning/labs/cli-basics/`
6. `javac -d ../out/production/Outreach_Report_Builder/learning/labs/cli-basics/ learning/labs/cli-basics/HelloCli.java`
7. `java -cp ../out/production/Outreach_Report_Builder/learning/labs/cli-basics/ HelloCli`
### Using `java`
8. `java learning/labs/cli-basics/HelloCli.java`

## Decisions Made
### Decision
Learned javac and java through command line
### Reason
To remove dependency on IntelliJ's interface and automated compile/run feature. This allows me to understand what's being done at a lower level, and gives me universal terminal experience

## Commands and Reusable References
```bash
java --version
javac --version
javac -d out-path/ class-path/file-name
java -cp out-path/ file-name
java class-path/file-name
```