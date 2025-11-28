package com.example.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String name;
    private  String email;
    private Long phoneNo;
    private boolean collected = false;

    public Client() {}

    public Client(Long id, String transactionId, String name, String email, Long phoneNo) {
        this.id = id;
        this.transactionId = transactionId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

//    public String getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(String transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(Long phoneNo) {
//        this.phoneNo = phoneNo;
//    }
//
//    public String getId() {
//        return String.valueOf(id);
//    }
}
