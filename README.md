# Auto service web app

------------
## Summary

--------------
Auto service is a REST-API server application that receives HTTP requests, stores the data 
in a PostgreSQL database management system, processes the requests, and implements CRUD 
(Create, Read, Update, and Delete) operations. The project also implement Swagger to provide 
an API documentation and interactive interface for developers to test the endpoints of the API.

## Technologies

---------
* Java 17 
* Spring Boot
* PostgreSQL
* Swagger UI
* Maven

## Functionality

-----------
* POST - Create "Master" entity
* PUT - Edit entity data
* GET - Get master's orders
* GET - Calculate and issue master's salary
* POST - Create "Car" entity
* PUT - Edit entity data
* POST - Create "Car Owner" entity
* PUT - Edit entity data
* GET - Get orders of this client
* POST - Create "Order" entity
* POST - Add "Product" to "Order" entity
* PUT - Edit entity data
* PUT - Edit "Order" status
* GET - Calculate "Order" cost
* POST - Create "Service" entity 
* PUT - Edit entity data
* PUT - Edit "Service" status
* POST - Create "Product" entity
* PUT - Edit entity data

## How to run this project

-------------
* Install PostgreSQL
* Clone this repository to your IDEA and open it
* Replace your data (username, password) in application.properties
* Start app from CarServiceApplication
* Use this link to watch all end points 
and test their functional