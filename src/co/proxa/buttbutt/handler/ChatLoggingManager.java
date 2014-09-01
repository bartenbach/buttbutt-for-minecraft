package co.proxa.buttbutt.handler;

import java.util.HashMap;

public class ChatLoggingManager {

    private HashMap<String,String> messageLog = new HashMap<String, String>();

    public void logMessage(String playerName, String message) {
        messageLog.put(playerName, message);
    }

    public boolean hasQuoteFrom(String playerName) {
        return messageLog.containsKey(playerName);
    }

    public String getLastQuoteFrom(String playerName) {
        return messageLog.get(playerName);
    }

}
