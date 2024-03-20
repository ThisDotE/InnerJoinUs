package org.thisdote.authclient.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String role;
    private String name;
    private String userName;
}
