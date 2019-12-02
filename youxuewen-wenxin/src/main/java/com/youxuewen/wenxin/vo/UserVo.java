package com.youxuewen.wenxin.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Validated
public class UserVo {

    @NotBlank(message = "用户名不可为空")
    @Size(min = 6,max = 20,message = "用户名长度在6-20个字符之间")
    private String username;

    @NotBlank(message = "密码不可为空")
    @Size(min = 6,max = 20,message = "密码长度在6-20个字符之间")
    private String password;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

    private String cid;
}
