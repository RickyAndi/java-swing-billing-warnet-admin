package com.mycompany.application.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

public class AdminConfigService {

    private JSONObject database;
    private JSONObject socketServer;

    public AdminConfigService(String[] configJsonFilePaths) throws Exception {
        FileReader fileReader = null;
        for (String jsonFilePath : configJsonFilePaths) {
            try {
                fileReader = new FileReader(jsonFilePath);
                break;
            } catch (FileNotFoundException e) {

            }
        }

        if (fileReader == null) {
            throw new FileNotFoundException("File config tidak ditemukan");
        }

        JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);
        database = (JSONObject) jsonObject.get("database");
        socketServer = (JSONObject) jsonObject.get("socket_server");
    }

    public Optional<String> getDatabaseConnectionUrl() {
        try {
            return Optional.of((String) database.get("connection_url"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getDatabaseName() {
        try {
            return Optional.of((String) database.get("name"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getDatabasePassword() {
        try {
            return Optional.of((String) database.get("password"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getDatabaseUsername() {
        try {
            return Optional.of((String) database.get("username"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getSocketServerHost() {
        try {
            return Optional.of((String) socketServer.get("host"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Long> getSocketServerPort() {
        try {
            return Optional.of((Long) socketServer.get("port"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
