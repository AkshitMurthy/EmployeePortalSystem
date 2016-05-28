Employee Portal System is a web application for a portal system which employees of any organisation can use for formal communications
like subdividing and assigning tasks.
You should have Eclipse with Apache Tomcat 7. 
See https://www.eclipse.org/webtools/jst/components/ws/1.0/tutorials/InstallTomcat/InstallTomcat.html or http://www.coreservlets.com/Apache-Tomcat-Tutorial/tomcat-7-with-eclipse.html

Steps to set up project in Eclipse:
1. Import the project into eclipse
2. Go to configure build path(right click on the project main folder in eclipse and click 'Build path')
3. Remove existing 'servlet-api.jar' and 'com.mysql.jdbc_5.1.5.jar'
4. 'Add external JARS' -> Add two .jar files: 'servlet-api.jar' and 'com.mysql.jdbc_5.1.5.jar'(provided with the project)
5. To run right click on project -> Run on server -> choose an existing server or define a new one.
6. The index page opens in the default eclipse browser

