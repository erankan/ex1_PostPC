package erankan.myselfchat;


import java.util.Date;

public class MyMessage {

    private String sender;
    private String content;
    private Date timeStamp;

    public MyMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = new Date();
    }

    public MyMessage(String sender, String content, String date){
        this.sender = sender;
        this.content = content;
        this.timeStamp = new Date(date);
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timeStamp;
    }

    public String getMessage(){
        return sender + "-" + content + "-" + timeStamp.toString();
    }


    public boolean equals(MyMessage msg){
        return getMessage().equals(msg.getMessage());
    }
}
