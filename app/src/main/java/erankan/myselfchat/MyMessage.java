package erankan.myselfchat;

import java.util.Date;

public class MyMessage {

    private String sender;
    private String content;
    private String timeStamp;

    public MyMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = new Date().toString();
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timeStamp;
    }
}
