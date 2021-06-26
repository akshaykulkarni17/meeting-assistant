# REST-API For HASHMAP

This project is a REST api service for storing and retreiving data using hashmap
It consists of two REST end points
+ GET - retrives the data
+ POST - sends the data

Clone the repository and run the spring boot application 

The application will be hosted on port 8080(make sure that the port is not used by other services) or change the port in Application.properties file by adding server.port = ####(any unallocated port)

url for GET request is localhost:8080/getvalue/(your key)

url for POST request is localhost:8080/insert (send parameters using postman or curl)
parameters are key,value

This application can handle bucket collisions using linked-lists. A conceptual diagram is shown below
![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(45).png)

Here is a small demo of how the application works with some screenshots.

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(36).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(37).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(38).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(39).png)

Here we can see that the hashmap values are same which cause bucket collisions but the linked-lists handle the issue.

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(40).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(41).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(42).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(43).png)

![app](https://github.com/katikireddy622/Cassandra-Test/blob/master/Screenshot%20(44).png)
