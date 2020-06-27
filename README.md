CheckoutCounter
================

This file contains information that is required to get started with Checkout counter application

## Type of Application
This is a pure **Console** base application. Black screen application with lots of menu options. Internally these menu options are available as **View** inside application.

## System and Software Requirements

 * Java 8
 * Maven 3

## Build
Use following command to create executable jar file:
 * mvn clean package
This will create executable jar file **CheckoutCounter-jar-with-dependencies.jar** under target folder.

## Execute
Build result is an executable jar file, so it can easily be triggered using following command:
 * java -jar CheckoutCounter-jar-with-dependencies.jar

## Application process
Once this application comes up following flows will be executed:
 **Index view**
 Landing view for application with following options:
 \+\+\+\+\+\+\+Welcome to the store\+\+\+\+\+\+\+\+
Please select from menu:
    1. Shop
    2. Cart
    3. Checkout
    4. Exit
    Please enter your choice:

* 1 will lead to **Shoppping View**
* 2 will lead to **Cart View**
* 3 will lead to **Checkout View**
* 4 will lead to **Termination** of application.

 **Shopping view**
 Once 1 is entered in above mentioned view following shopping view will be visible to user.
 Please select from following:
    1. Add to cart
    2. Remove from cart
    3. Show cart
    4. Check items in store
    5. Checkout
    6. Exit
Please enter your choice:
 * 1 will lead to **Add to cart** flow
 * 2 will lead to **Removal from cart** flow
 * 3 will lead to **Cart View**
 * 4 will lead to **Inventory View**
 * 5 will lead to **Checkout View**
 * 6 will lead to **Termination** of application
 
 **Cart View**
Will display all items with their product id, name and quantity in cart as follows:

| ID|PRODUCT_NAME|PRODUCT_COUNT|

**Checkout View**
Will display generated bill on basis of cart items:
| ID|        NAME|COUNT|      COST|     TAX %|     TAX AMT|     TOTAL|
|  1|           A|    1|  INR 10.0|       10%|     INR 1.0|  INR 11.0|
|   |            |     |          |          | GRAND TOTAL|  INR 11.0|

## Inventory
Inventory is initialized with followings items initially:

| ID|        NAME|COUNT|      CATEGORY|     PRICE|
|  1|           A|   10|             A|  INR10.00|
|  2|           B|   10|             A|  INR20.00|
|  3|           C|   20|             B|  INR30.00|
|  4|           D|   20|             B|  INR40.00|
|  5|           E|   30|             C|  INR50.00|
|  6|           F|   30|             C|  INR60.00|
|  7|           G|   30|             C|  INR70.00|


