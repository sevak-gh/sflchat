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


- How to use the application:

  you can see two links in the home page:
    - Admin: you can find the administration functionalities here 
    - User: normal users sign in from here to their profile page.

  Admin:
   there is a one defualt admin user with the following credentials:
   username: admin  
   password: 123456

   you can sign in as admin with the above credentials.
   
   after sign in you will be directed to admin home page where you can find three links:
    - User Management : you can see the list of users, create a new user, delete user, update user profile
    - Chat Message Management : you can see the list chat messages, delete a message, set chat room bad word
    - Chat User Management : you can see the chat room users, remove user from chat room


   User: 
    users can sign in with their username/password, set by admin during user creation.

** please note that users can also sign in through their google account, if the username
   is set to their gmail account name, such as sevak.gharibian@gmail.com, by admin.

    there is one profile page for users where there can see the prfile information
    such as frist_name, last_name, profile_picture.

    in the profile page, there is the chat room messages section where users can see
    the history of messages and online messages sent by all chat room users.

    at the bottom there is text input where user can enter the text and send message to chat room.
    all online users will receive the message immediately and those sign in later will see in the history.
    all messages are saved in database.


    
    

        

    
   

  
   

   

 
    

   
  


