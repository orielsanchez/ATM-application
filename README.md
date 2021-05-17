# CISC191-SPRING2021-F

## ATM Application
A CLI ATM application that allows you to create accounts, deposit, withdraw, and transfer funds to different accounts in the database.

## Prerequisites
1. Maven
2. Git
3. Liberica JDK 15
## Cloning
   ```sh
   git clone https://github.com/MiramarCISC/CISC191-SPRING2021-F.git
   ```
## Building
   ```sh
   mvn clean install
   ```

## Running
```sh
java -jar Server/target/Server-1.0.0.jar
java -jar Client/target/Client-1.0.0.jar
```
## Common Module
Shared classes between client and server modules.
## Server Module
The server application that handles multiple clients.
## Client Module
The client application used to connect to the server.