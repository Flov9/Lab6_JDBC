# Lab6_JDBC

A simple program to explore the relation between JDBC and RMI with a GUI made using JavaFX

## Requirements

- Scores of students should be stored in a database 
- An RMI component should be implemented as a middle tier between server and client
- Client side should be able to query the desired result score from the server database
- System should be able to block access to certain students' scores if they have not given permission

## General Information

This program uses a simple three tier architecture concept where RMI serves as the connection between the server (database driver loader and manager) 
and client (JavaFX GUI) to retrieve the corresponding scores.

Prior to the execution of the program a simple SQL statement was executed to create a table using Apache Derby database in its embedded mode. The statement can be found in studentscoretable_javadb.txt

## Project status

Project is complete.
