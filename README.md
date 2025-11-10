Swag Labs Automation - Selenium + TestNG + POM + Data-Driven (JSON)

How to run:

1. Requirements:
   - Java 17+
   - Maven
   - Internet (first run) to let WebDriverManager download the browser driver
   - Chrome browser installed

2. Build & Run tests:
   Open terminal inside project directory and run:
     mvn test

3. Notes:
   - testData.json is at src/test/resources/testData.json
   - If you prefer to run headless or customize browser options, modify BaseTest.java accordingly.
   - The tests include small Thread.sleep waits for simplicity. For production, replace with proper waits (WebDriverWait).

Files created:
 - src/test/java/base/BaseTest.java
 - src/test/java/pages/LoginPage.java
 - src/test/java/pages/InventoryPage.java
 - src/test/java/tests/LoginTest.java
 - src/test/java/tests/InventoryTest.java
 - src/main/java/utils/DataDriven.java
 - src/test/resources/testData.json
 - pom.xml
