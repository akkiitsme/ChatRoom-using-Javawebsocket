package com.chatroom.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ctr_users")
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
