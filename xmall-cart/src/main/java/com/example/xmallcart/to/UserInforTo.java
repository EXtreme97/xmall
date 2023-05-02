package com.example.xmallcart.to;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserInforTo {
    private Long userId;

    private String userKey;

    /**
     * 是否临时用户
     */
    private Boolean tempUser = false;
}
