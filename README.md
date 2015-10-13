company-people-graph
=======================

## Tools setup (command instructions are for OSX)

1. Install Gradle (http://www.gradle.org/installation)

2. Install Java 1.8

## Run tests

```
./gradlew test
```
## Run application

*Provide the full path to the Companies.txt and People.txt files. The order matters :)

```
./gradlew run '-PfilePaths=companyTxtFilePath,peopleTxtFilePath'
```
## Development Setup with IntelliJ

```
$ ./gradlew idea
$ open *.ipr  #on OSX
```
*
Anytime you adjust the gradle build files, you have to rerun `./gradlew idea` and
let the IDE reload the project.

* Install "lombok" plugin & Enable: Settings -> Compiler -> Annotation Processor -> Enable annotation processing

