package com.ngobrol.cocomp.cocompchatapps.Models;

import java.util.Date;

/**
 * Created by noverio.joe on 3/24/17.
 */

public class ChatMessageModel {

    private String message;
    private String from_UserID;
    private String converstationID;
    private String messageID;
    private Date messageDate;

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getFrom_UserID() {
        return from_UserID;
    }

    public void setFrom_UserID(String from_UserID) {
        this.from_UserID = from_UserID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConverstationID() {
        return converstationID;
    }

    public void setConverstationID(String converstationID) {
        this.converstationID = converstationID;
    }
}
