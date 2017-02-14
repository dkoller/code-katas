# SonarQube

## Sonar Lint

A few years ago, one of the main problems of SonarQube was it didn't allow to get on-the-fly feedback in IDEs. Nowadays, that's possible with [Sonar Lint](http://www.sonarlint.org/index.html) (available for Eclipse, IntelliJ IDEA and Visual Studio).

Furthermore, it allows to connect a project from your IDE with a project from your SonarQube, in order to use the quality profile from that SonarQube project.

## Scan Maven projects

In order to scan Maven projects with SonarQube, [this pom](pom.xml) can be used as parent POM. In this way, you avoid repeating the same configuration in every POM.

### Install SonarQube

To install SonarQube, just download it and start it (it will use a HSQLdb; it fits only for local use). For example:

```
./bin/linux-x86-64/sonar.sh console
```

Configure your `.m2/settings.xml`:

```
<settings>
    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>
    <profiles>
        <profile>
            <id>sonar</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <!-- Optional URL to server. Default value is http://localhost:9000 -->
                <sonar.host.url>
                  http://localhost:9000
                </sonar.host.url>
            </properties>
        </profile>
     </profiles>
</settings>
```

### Scan a Maven project

Firstly, install [the parent POM](pom.xml) in your local repository:

```
mvn install
```

Secondly, you can use it as a parent in your project POMs:

```
<project>
  <parent>
    <groupId>es.rachelcarmena</groupId>
    <artifactId>kata-sonarqube</artifactId>
    <version>1.0.0</version>
  </parent>
  ...
</project>
```

Finally, in order to scan a project:

```
mvn verify sonar:sonar
```

Or if you only have unit tests:

```
mvn test sonar:sonar
```
