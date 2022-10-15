# Tourism-Information-Management-System
 
This comprehensive tourist information website is used to publish information on tourist attractions and to display travel itineraries to visitors.
The system is divided into three modules: the visitor side, the user side and the administrator side. Each module has different rights and functions

Eclipse + MySQL

(1) Visitor function
When the user enters the page for the first time, he can only browse as a tourist, he can view the relevant tourist information in the website, but he cannot leave a message or choose a route.

1. First view 
Once the visitor enters the home page all routes will be displayed to the visitor, it will show the route name, travel city, whether it is a domestic travel route, user price and member price together, the visitor can enter the travel information details view page by clicking on view details.

2. Travel information details view
This interface is used to place orders, by reading out the travel information in the database to achieve the line details display, you can display the title of the travel line, uploaded scenery pictures, travel cities, user prices, member prices, line features, itinerary descriptions, cost descriptions, scenery descriptions, and at the same time you can click on "Place Order" in the operation bar of this page You can click on "Place Order" on this page, but it will show that you are logged in and you will be redirected to the login page.


3. Domestic Tour Information
Click on "Domestic Travel" on the home page to jump to the domestic travel information view interface, only the relevant domestic travel routes and their information will be displayed, you can also click on the view details to view the details of the route.

4. International Travel Information
Click on "International Travel" on the home page and you will be redirected to the international travel information screen, which will only display international related travel itineraries and their information, or you can click on View Details to view the details of this itinerary.

5. Physical travel agency view
After clicking on "Physical Travel Agency" on the home page, you will be redirected to the offline physical travel agency information view interface, which will display the address, phone number, weibo, qq and email address of the offline physical travel agency, so you can contact them directly.


(2) User side function
When the user enters the page, he can register as a user and become an official user of this website. After successful registration, he can log in, and after logging in, he can use some functions that cannot be used by tourists, such as route selection function and message function.

1. Home view
Visitors to the home page will show all the routes to visitors, will be the line name, travel city, whether it is a domestic tourist routes, user prices and member prices together, visitors can click to view details to enter the travel information details view page.

2. Registration
You can enter your username, password, confirmation password, name and mobile phone number to register. The back-end will carry out data standardization testing, for example, no letters in the mobile phone number input box, and the same password input twice.


3. Login function
After registering as an official user, you can click the login button to enter the login page and enter your username and password to login.

4. Comments
You can leave a message on the tour you have participated in. The message will be sent to the administrator for viewing.

5. Tour Information Detail View
This interface is used to place an order, by reading out the travel information in the database to achieve the route details display, you can display the title of the travel route, uploaded scenery pictures, travel city, user price, member price, route features, itinerary description, cost description, scenery description, at the same time, you can click "place order" in the operation column of this page You can place an order on this page, but the visitor will be shown please login and jump to the login page.

6. Domestic Tour Information
Click on "Domestic Travel" on the home page to jump to the domestic travel information view interface, only the relevant domestic travel routes and their information will be displayed, you can also click on the view details to view the details of the route.

7. International Travel Information
Click on "International Travel" on the home page and you will be redirected to the international travel information screen, which will only display international related travel itineraries and their information, or you can click on View Details to view the details of this itinerary.

8. Physical travel agency view
After clicking on "Physical travel agency" on the home page, you will be redirected to the offline physical travel agency information view interface, which will display the address, phone number, WeChat, qq and email address of the offline physical travel agency, so you can contact them directly.

9. Order function
You can place an order on the home page or the domestic travel information view page or the foreign travel information view page, set the travel date, and send the relevant booking information to the backend administrator after placing the order, which will be carried out by the administrator.
10. Route customization function
Users can customise their travel routes, set travel dates, destinations and related itineraries, and submit them to the travel agency administrator, who will reply and price them.

11. Password modification function
Users can change their passwords by entering their original password and repeating the new password twice in the password change screen.

12. View announcement function
Users can view the announcements issued by the administrator side on the home page.


(3) Administrator side functions
After logging in, you will enter the administrator's background page to process order information and message information.

1. Main Page
The main back office page shows all the corresponding functions in the left sidebar, which can be used by clicking on the different function modules.

2. Making announcements
The administrator side can issue announcements, which can be seen by users or visitors after they have logged in.

3. Domestic tourism information management
The administrator side can view information about domestic tourism and perform operations such as adding, deleting, changing and checking.

4. International tourism information management
The administrator can view the information of international tourism, and add, delete, change and check.

5. Order Management
You can manage the orders passed by the users, you can check the customer's name, contact number, the theme of the scheduled tour, the date of the tour, the amount of the order and other information, and perform the operation of passing or rejecting the order.

6. Route customisation management
It is possible to manage the custom orders passed by the user, you can view the customer's name, contact number, destination theme submission time and other information, and reply to the custom orders, you can also delete the custom order information, and then return the reply information to the user side.

7. Message management
The administrator side can view and manage the messages sent by the user, and can view the user's name, message title, message time and carry out the corresponding operations.

8. Password modification function
The administrator can modify the password on the administrator side by entering the original password and repeatedly entering the new password twice in the password modification interface.
