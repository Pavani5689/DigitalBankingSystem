DIGITAL BANKING & TRANSACTION MANAGEMENT SYSTEM
Java Servlet + JSP + MySQL  |  Eclipse  |  Tomcat 8.5  |  Java 21

1. DATABASE
   From a terminal:   mysql -u root -p < sql/digital_banking.sql      (password: Nithin123)
   This creates the database "digital_banking" with tables: customer, transactions.
   If your MySQL runs on another port (the reference used 3308), change the URL in
   src/main/java/com/dao/ConnectionManager.java

2. IMPORT
   Eclipse > File > Import > Existing Projects into Workspace > select this folder.
   Right-click project > Run As > Run on Server (Tomcat 8.5).
   Open: http://localhost:8080/DigitalBankingSystem/

3. LOGINS
   Admin    : admin@bank.com / admin@123
   Customer : register from "Open Account", then login with that email + password

4. FEATURES
   Customer: register (gets account number), login, dashboard + balance, deposit,
             withdraw, transfer to another account, transaction history, profile
   Admin   : view / search / edit / delete customers, view all transactions
