package com.zhouy.login.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "账户不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String avatar;

    private String email;

    private String nickname;

    private Timestamp gmtCreated;

    private Timestamp gmtModified;

}

