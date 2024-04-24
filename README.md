# Setting Up cluster

This README provides instructions for setting up Cassandra using Docker, including downloading the Cassandra image, running a Cassandra container, and creating tables.

## Prerequisites

- Docker installed on your machine. If Docker is not installed, download and install it from [here](https://www.docker.com/get-started).
- Kubectl installed with a created cubernetes cluster
## Steps

1. **Build and push the docker images**

For every spring project run the following commands

```docker build -t docker push gcr.io/meta-gear-421306/<image_name>:v1 .```
```docker push gcr.io/meta-gear-421306/<image_name>:v1```
The names of the images can be found in the kubernetes yaml files

2. **Setup cassandra with kubernetes**

Run the following commands in the root directory of the repository.

```kubectl apply -f deployments/cassandra_deployment.yaml```

```
# List all pods
kubectl get pods

# Access Cassandra pod
kubectl exec -it cassandra-pod-name -- /bin/bash

# Start CQL shell
cqlsh

# Execute queries
CREATE KEYSPACE IF NOT EXISTS minitindermms
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE minitindermms.matchentity (
    matchid UUID PRIMARY KEY,
    userid1 BIGINT,
    userid2 BIGINT,
    pending BOOLEAN,
    matchedat TIMESTAMP
);

CREATE TABLE minitindermms.messageentity (
    messageid UUID PRIMARY KEY,
    senderid BIGINT,
    recipientid BIGINT,
    content TEXT,
    sentat TIMESTAMP
);
```

```kubectl apply -f service/cassandra_service.yaml```

3. **Create mysql with kubernetes**

Execute these commands in the root directory of the repo.

```kubectl apply -f deployments/mysql_deployment.yaml```

```kubectl exec -it <pod_name> --container <container_name> -- mysql -u<username> -p<password> -e "CREATE DATABASE MiniTinderNotifications"```

```kubectl apply -f service/mysql-service.yaml```

4. **Create Firewall rules**

This depends on what kubernetes provider you are using. Here is an example for GKE.

```gcloud compute firewall-rules create allow-port-30002 --allow tcp:30002 --description="Allow incoming traffic on port 30002" --direction=INGRESS```

5. **Setup apps**

Initialise the deployment and service yaml for all the apps.
 