# sflchat
SFL chat service


- How to deploy and run the application:

  there are some external dependencies we need to config first:

  databae:
    we need a MysqL database for this application. 
    there is a sql script for creating schema and some sample data for test.
    please create a new database in Mysql and then run this scritp;
   
    the following is mysql CLI commands for sample:
     to create a new database:  
        - create database sflchat;
     to run the script (creates tables and inserts sample data)
        - source /your-path/data/sflchat.sql;

     now we have a new database for sflchat application with some sample data.
     
     please set the mysql connection paramaters in src/main/resources/application.properties file.
     there three parameters you need to set:

     - spring.datasource.url=jdbc:mysql://localhost/sflchat
     - spring.datasource.username=root
     - spring.datasource.password=111111

     
   user profile upload folder:
    please create a folder on the systm and give read/write permission for the application.
    there is a static field in src/main/java/com/sfl/chat/controller/AdminController.java 
    named PROFILE_PICTURE_UPLOAD_PATH, please set your folder system path here and save the file.


   now we are ready to build and deploy the application.
   
   please checkout the source code and run the following maven command:
   - mvn clean package

   this will create a new package inside target folder names sflchat.jar
   this is an executable jar file with embedded tomcat based on Spring boot.


   now we can run the application using the following command:
   java -jar your-path/sflchat.jar   

   if everything goes well, you can the check the web app in your browser:
   - http://localhost:8080/
   
   you will see the home page there.


** you can check the logs in your current directory where you run the application in a file named "sflchat.log". 

   

 
    

   
  


