This is a Java based Playwright Test Framework. It supports the following browsers: chromium (chrome, ms edge etc.), webkit (safari) and gecko (firefox).
Tested with a playwright web suite (dev in progress). Fully tested with parallel runs on different browsers with multiple test cases.

Supports reading object ids stored in a separate properties file (key/value pair) defined in your test suite. The name of the properties file is assumed as "object.repo.properties". Can be modified as needed.

    Playwright 1.43 or above
    TestNG
    Apache Maven
    Java 21 (can be used with any Java version starting with 8).
    
To compile as a jar, mvn clean install

The intent of this project is for illustrative purposes only. The code can be freely used and modified as needed.
