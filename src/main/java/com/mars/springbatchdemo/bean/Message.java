package com.mars.springbatchdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "object_id", nullable = false)
    private String objectId;

    @Column(name = "content")
    private String content;
//
//    @Column(name = "last_modified_time")
//    private LocalDateTime lastModifiedTime;
//
//    @Column(name = "created_time")
//    private LocalDateTime createdTime;
}
