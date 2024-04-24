# Setting Up Cassandra with Docker

This README provides instructions for setting up Cassandra using Docker, including downloading the Cassandra image, running a Cassandra container, and creating tables.

## Prerequisites

- Docker installed on your machine. If Docker is not installed, download and install it from [here](https://www.docker.com/get-started).

## Steps

1. **Download the Cassandra Image**

   Pull the latest Cassandra image from Docker Hub by running the following command in your terminal:

```docker pull cassandra:latest```


2. **Run a Cassandra Container**

Use the following command to run a Cassandra container:

```docker run -d -p 9042:9042 --name my-cassandra-container -v /path/to/local/directory:/var/lib/cassandra cassandra:latest```

Replace `/path/to/local/directory` with the path to the directory on your host machine where you want to store Cassandra data.

3. **Create Keystore and Tables**

Enter this command to get acces to the cassandra terminal

```docker exec -it my-cassandra-container cqlsh```

Once the Cassandra container is running, use your preferred tool (e.g., cqlsh or a Cassandra driver) to connect to the Cassandra instance and execute CQL queries to create a keystrore and tables. Below are the CQL queries:



- **Create Keystore**
```CREATE KEYSPACE IF NOT EXISTS minitindermms
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};```

- **MatchEntity Table**

  ```cql
  CREATE TABLE minitindermms.matchentity (
      matchid UUID PRIMARY KEY,
      userid1 BIGINT,
      userid2 BIGINT,
      pending BOOLEAN,
      matchedat TIMESTAMP
  );
  ```

- **MessageEntity Table**

  ```cql
  CREATE TABLE minitindermms.messageentity (
      messageid UUID PRIMARY KEY,
      senderid BIGINT,
      recipientid BIGINT,
      content TEXT,
      sentat TIMESTAMP
  );
  ```
4. **Creating the minitinderMMs container**

Open the minitindermms project and enter these commands in the terminal

```docker build minitindermms .```

This will build the image

``` docker run -p 8080:8080 --name minitindermms_container minitindermms```

This will create a container named minitidenrmms_container based on the minitindermms image and run it.

5. **Creating the Mysql container for the notification service.**

Pull the latest mysql container image and run it.

```docker pull mysql:latest```
```docker run -d --name my_mysql_container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:latest -e MYSQL_DATABASE=MiniTinderNotifications```

This will run a docker container on port 3306 and will initialise the database by itself.

6. **Setup a network for this database**

To have the minitinder notification service communicate with the mysql container we need to establish a network.
To do that we enter the following commands:

```docker network create minitinderns-network```
```docker network connect minitinderns-network my_mysql_container```

This will create the network named minitiderns-network and connect the my_mysql_container to it.

7. **Creating the minitinderNs container**

Open the minitinderNs project and type in the following commands

```docker build minitinderns .```
```docker run -p 8081:8081 --name minitinderns_container --network minitinderns-network -e MYSQL_HOST=my_mysql_container -e MYSQL_PORT=3306 minitinderns```
This will run the image with the enviroment variables set for the mysql container and connected to the minitinderns-network

 