Shopping-Cart
=============

This application has 3 projects:

Shopping-Cart-Api
-----------------
Spring-boot application, responsible for manage the products and shopping orders.

Payment Service
---------------
Spring-boot application, responsible for processing payments sent from the shopping-cart-api, the communication is made by an ActiveMQ queue.

Shopping Cart App
-------------------
Front-end angular app, responsible for show the products list, 
manage the shopping cart items, and send the payment data to the api.


Technologies
------------
Java8, Spring-Boot, Jpa/Hibernate, MySql Database, ActiveMQ, Angular4