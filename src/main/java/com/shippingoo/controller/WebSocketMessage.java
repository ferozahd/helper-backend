package com.shippingoo.controller;

public class WebSocketMessage {
    public final String toWhom;
    public final String fromWho;
    public final String message;
    
    public WebSocketMessage(final String toWhom, final String fromWho, final String message){
      this.toWhom  = toWhom;
      this.fromWho = fromWho;
      this.message = message;
    }

    public String getToWhom() {
        return toWhom;
    }

    public String getFromWho() {
        return fromWho;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "WebSocketMessage [fromWho=" + fromWho + ", message=" + message + ", toWhom=" + toWhom + "]";
    }

    
}
