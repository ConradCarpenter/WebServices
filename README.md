# WebServices
# NewsFeed Application
1. Sign up or log in
2. New user selects which topics they want
3. Their feed is displayed
4. Users should be able to comment on posts

Netbeans JS/D3/Dojo/HTML/CSS with Tomcat JS/JAX-RS/JPA and MySQL DB AJAJ web 2.0 application template

## Topics
- Sports
- Gadgets
- Gaming
- Music
- Food

## Tools
- curl (get and post requests) to grab links from reddit

## Login/Registration
- Username + Password + Topic selection (Post Methods)
  - Password Security (SSL or hash?)
- After login/registration feed is populated 
  - Ability to comment
  - Update preferences 
  - Change Password
  
# Roles
- MySQL =  Erik (shermanator138@gmail.com)
- JAX-RS = Ryan & Zack (ahearn@hartford.edu zjones@hartford.edu)
- Client JS = Conrad


AJAJ based framework as covered in class that uses JAX-RS/JPA to provide user based read/write on a MySQL DB back end.  The included example will have to have the connection adjusted and persistance.xml, context.xml edited as well as to the creation of a db name test with the following schema: users(varchar(25) uid PK, varchar(45) name).  The uid is the primary key and so the B tree index and the unique constraints will be imposed automatically.
