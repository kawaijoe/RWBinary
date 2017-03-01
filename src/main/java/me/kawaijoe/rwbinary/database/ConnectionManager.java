/*
 * Copyright (C) 2016 KarusMC @ karusmc.com
 * All rights reserved.
 *
 * https://github.com/Pante/Karus-Commons/tree/master/src/main/java/com/karuslabs/commons/database
 */
package me.kawaijoe.rwbinary.database;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;

public class ConnectionManager {

    private MongoClient client;

    public ConnectionManager(String connectionString) {
        client = MongoClients.create(connectionString);
    }

    public ConnectionManager(DatabaseConfiguration dc) {
        client = MongoClients.create(connectionStringBuilder(dc));
    }

    protected String connectionStringBuilder(DatabaseConfiguration dc) {
        String configHost = dc.getHost();
        int configPort = dc.getPort();
        String configDB = dc.getDb();
        boolean configAuth = dc.isAuth();
        String configUser = dc.getUser();
        String configPass = dc.getPass();

        String connectionURL = "mongodb://";
        if (configAuth)
            connectionURL += configUser + ":" + String.valueOf(configPass) + "@";

        connectionURL += configHost + ":" + configPort + "/" + configDB;
        return connectionURL;
    }

    public void disconnect() {
        client.close();
    }

    public MongoClient getClient() {
        return client;
    }
}
