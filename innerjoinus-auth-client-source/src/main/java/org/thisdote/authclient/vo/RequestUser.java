package org.thisdote.authclient.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RequestUser {
    private Integer userCode;
    private String userId;
    private String userPassword;
    private Date userBirthday;
    private String userPhone;
    private String userEmail;
    private String userLoginCode;
    private String userRole;
}
