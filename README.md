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

4. **Verify Setup**

You can verify that the Cassandra container is running and the tables are created by connecting to the Cassandra instance and querying the tables.

## Additional Notes

- Ensure that the port 9042 is not blocked by any firewall or security group rules.
- Replace the placeholders in the commands and queries with actual values as required.