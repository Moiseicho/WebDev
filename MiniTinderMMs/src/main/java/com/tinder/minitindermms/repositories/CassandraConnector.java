package com.tinder.minitindermms.repositories;

import com.datastax.oss.driver.api.core.CqlSession;
import java.net.InetSocketAddress;

public class CassandraConnector {
    private final CqlSession session;

    public CassandraConnector(String ipAddress, int port, String localDatacenter) {
        // Create the session builder with contact point and local datacenter
        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress(ipAddress, port))
                .withLocalDatacenter(localDatacenter)
                .build();

        this.session = session;
    }

    public CqlSession getSession() {
        return this.session;
    }

    public void close() {
        this.session.close();
    }
}
