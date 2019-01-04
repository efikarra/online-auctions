This repository contains an online auctions system.</br>
<h2> System description</h2>
The system is built following the web browser/REST server architecture.
The architecture is presented in the following figure:

<img src="/architecture.png" width="350">

A MySQL database was used as a <b>persistence storage</b>.
The back-end is developed in Java. </br>
The Hibernate ORM framework was employed for the Data Access Layer and Spring MVC for the REST API. </br>
The REST API is secured with token-based authentication, implemented using Spring security.</br>
The front-end was written in AngularJS.

<h2> Deployment instructions</h3>

<h4> Database </h4>

1. Create a user in MySQL with full privileges. 
Edit the file /back-end/auctions-source/src/main/resources/properties/database.properties to add your user's username and password.

2. Import the MySQL dump file that is located in the folder /database. 
In this way, a database with name "auctions" will be created, containing some sample auction data.
In the sample data, all users have the password "123456", some of the usernames are “jackiebrown”,”sweetsuzie”,”ecworldauctions” and the name of the admin user is “kostis”.

<h4> Server </h4>

1. Copy the folder /back-end/auctions-photos in the root folder of your machine.

2. Build the project in /back-end/auctions-source by running the command "gradle clean build". A .war file will be created in the directory /back-end⁩/auctions-source⁩/build⁩/⁨libs⁩.
Copy this file into the webapps directory of your Apache Tomcat Server (the project has been tested in Apache Tomcat 8).

<h4> Client </h4>

1. Copy the folder /front-end/auctions-web in the ROOT directory of your Apache Tomcat and restart the server.
Open your favorite browser and navigate to the path http://localhost:8080/auctions-web/app.
