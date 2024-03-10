package org.thisdote.innerjoinus.user.vo;

import lombok.Data;

@Data
public class RequestLogin {
    private String userId;
    private String userPassword;
}
