# JavaCoffee

*This document was last updated on March 9, 2023.*

To access the JavaCoffee application code, please navigate to the **Master** branch.

To run the application, follow these steps:

- Ensure that you have updated the SQL database credentials in the `application.properties` file.
- If this is your first time running the application and you require admin access, you have two options:
    - Set up your database manually with the admin information and role, `"ROLE_ADMIN"`.
    - Uncomment the command line runner and associated content that populates data into the database, and ensure that you change the mode in `file.properties` to **Create**. 
    - After completing either of these initialization steps, you can log in with the admin credentials, recommend the initialization code, and update the `file.properties` mode to **Update** instead of **Create**.

Here's an overview of the Application:
 - Admin view: The admin can create, read, update, and delete items from the databse
 - User view: The user can view items listed for sale, and the user can send a resume to apply to work at the store.

Improvements Goal Before March 15, 2023
  - Create Add item to cart functionality 
  - Create view and update cart functionalities


Note: if you run into an error that said failed to build gradle project, just go to the pom.xml file and left click > maven > load maven project
