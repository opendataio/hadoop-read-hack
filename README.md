# hadoop-read-hack

This project contains two subprojects, one is the `root` subproject, the other one is `testapp`.

This project suppose to do a POC for monitor the hadoop `FSDataInputStream.read` method, and do something before invoke the real method.

# How to Build

```Console
mvn clean package
cd testapp
mvn clean package
```

# How to use

```Console
java -javaagent:./target/hadoop-read-hack-1.0.0.jar  -jar testapp/target/testapp-1.0.0.jar ./pom.xml 
Calling FSDataInputStream.read
enter buffer: 0
<project x
Hello World!
```