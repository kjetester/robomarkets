**Installation instructions**
0. Perform `git clone`
1. Install Java_14
2. Install Maven
3. Install Report Portal using instructions https://reportportal.io/installation
4. Fill up required fields in `src/test/resources/reportportal.properties` according to http://localhost:8080/report/ui/#user-profile
5. Run `mvn clean -pl ui -am test`