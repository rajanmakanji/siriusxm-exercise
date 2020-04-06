# siriusxm-exercise

For this exercise, I created a basic Spring Boot Application with a single API endpoint ('GET /incidents'). This API will return a JSON object of the traffic incidents from the XML file. The API calls a Util class (TrafficIncidentUtil.java) that handles the logic for reading the XML and converting it to JSON.

TrafficIncidentUtil.java has two methods one is called by the API controller. The other is the same, except it converts the json object to a json string before returning to the caller. This second method call is demonstrated in the main() method.

1) The buildscript is in the root of the project. There is a buildscript.bat and buildscript.sh. Both of them simply run "mvn clean install" to build the executable jar.
2) Once either buildscript is run, the jar should have been produced in the /target folder.
3) Navigate to the /target folder and run "java -jar siriusxm-exercise-0.0.1-SNAPSHOT.jar"
4) The application will have started up, exposing the endpoint "localhost:8080/incidents". Additionally, because the util class is called once in the main method to demonstrate, you will see the data output as a json string.
