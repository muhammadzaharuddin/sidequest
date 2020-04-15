# Java Side Quest

*Objective:*<br/>
Write a simple command line program that accepts input as command line arguments and does some basic CRUD operations on an sqllite DB

## Checkpoint 1 - JAVAC

*Objective:*<br/>
You should be able to compile and run your application only using javac and java

Compile and run:<br/>
![](https://raw.githubusercontent.com/muhammadzaharuddin/sidequest/master/images/Screen%20Shot%202020-04-15%20at%201.24.07%20PM.png)

Add pet:<br/>
![](https://raw.githubusercontent.com/muhammadzaharuddin/sidequest/master/images/Screen%20Shot%202020-04-15%20at%201.25.53%20PM.png)

Update pet:<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%201.26.41%20PM.png?raw=true)

Delete pet:<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%201.27.31%20PM.png?raw=true)

## Checkpoint 2 - ANT

*Objective:*<br/>
Dust off the old grand daddy of java build tools..Ant...and use that to compile your application into a jar and run it

1. Test and compile using javac into another folder:<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%202.07.47%20PM.png?raw=true)

2. Create a myManifest file, a jar directory, and create the jar file:<br/>
(Add the sqllite jar file to the same directory)<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%202.21.22%20PM.png?raw=true)

3. Then, write a build.xml in the root folder.

4. Compile and finally run:<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%205.42.18%20PM.png?raw=true)

## Checkpoint 3 - MAVEN

*Objective:*<br/>
Move on from ant to maven

1. Create a java project:<br/>
`mvn archetype:generate -DgroupId=com.example -DartifactId=pet -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`

2. After editing the pom.xml and App.js,<br/>

3. Compile: <br/>
`mvn compile`

4. Package: <br/>
- take the compiled code and package it in its distributable format (a jar file)<br/>
`mvn clean package`

5. Install: <br/>
- install the package into the local repository, for use as a dependency in other projects locally
`mvn clean install`

6. Deploy: <br/>
- done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.
`mvn exec:java`
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%205.24.56%20PM.png?raw=true)

## Additional - Spring Boot

1. Create a spring boot project at [start.spring.io](start.spring.io).

2. Add dependency of sqlite-jdbc in pom.xml and edit the main class file

3. Run the program:<br/>
![](https://github.com/muhammadzaharuddin/sidequest/blob/master/images/Screen%20Shot%202020-04-15%20at%203.00.06%20PM.png?raw=true)

## Final Boss Battle

*Objective:*<br/>
Explain the differences you observed as you progressed

*Javac:*<br/>
- compiling the project creates a .class file
- download and add the dependency to the directory manually
- to run, must specify file path and classpath of the dependency

*ANT:*<br/>
- utilizes a build.xml to specify the location of the .class file, .jar file, and dependency file so that it can be easily compiled
- download and add the dependency to the directory
- generates a .class file and .jar file which will be used to run
- running the program is easy and can be done together when cleaning and compiling the program

*MAVEN:*<br/>
- project easily created using template
- has a specific project convention
- can easily add the dependency in pom.xml

*Springboot:*<br/>
- project easily created using template
- has a specific project convention
- can easily add the dependency in pom.xml
- compiled and run in a single command as compared to just Maven
