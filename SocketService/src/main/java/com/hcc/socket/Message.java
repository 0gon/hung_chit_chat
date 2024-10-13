package com.hcc.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Message {
    private String type;
    private String memberId;
    private String text;
    private String roomId;
    private List<String> members;

    public Message() {
    }

    public Message(String type, String memberId, String text, String roomId, List<String> members) {
        this.type = type;
        this.memberId = memberId;
        this.text = text;
        this.roomId = roomId;
        this.members = members;
    }
}
