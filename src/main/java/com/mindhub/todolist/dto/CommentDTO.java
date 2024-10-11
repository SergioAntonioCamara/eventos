package com.mindhub.todolist.dto;

import java.util.UUID;

public class CommentDTO {

        private UUID comment_id;
        private String comment;
        private String customerName;
        private String eventName;

        public CommentDTO(UUID comment_id, String comment, String customerName, String eventName){
            this.comment_id = comment_id;
            this.comment = comment;
            this.customerName = customerName;
            this.eventName = eventName;
        }

    public UUID getCommentId() {
        return comment_id;
    }

    public void setCommentId(UUID comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
