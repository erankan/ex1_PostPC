package com.kan.eran.mylistapp;

import java.util.Date;

public class MyMessage {

    private String sender;
    private String content;
    private String timestamp;

    public MyMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = new Date().toString();
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
