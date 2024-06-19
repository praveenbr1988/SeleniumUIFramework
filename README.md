Framework Stack
1. Binding language-Java
2. Selenium
3. Thread Local for Webdriver- supports for parallel testing and different browsers. Added Chrome options in chromeconfigurations.yaml file.
4. TestNG
5. Apache POI
6. Ability to read from config.properties file
7. Ability to read from yaml file
8. Allure Reporting
9. ITestListeners and Retry Listeners
10. Log4j2
11. 

**Docker Steps**( Install Docker Desktop and verify the containers status.)
1. Add the docker images in "docker-compose.yaml" file. 
2. Keep it inside the project
3. Run the command "docker-compose up -d" to run the container
4. Update the grid url to "http://localhost:4444/wd/hub" in config.properties file
5. You can access the Selenium Grid console by navigating to http://localhost:4444/grid/console in your web browser. This allows you to verify that the standalone Chrome container is up and running.
6. Execute the test case.
7. After execution, Run command to stop "docker-compose down"