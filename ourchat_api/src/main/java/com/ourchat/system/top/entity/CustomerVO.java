package com.ourchat.system.top.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerVO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("邮箱")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("昵称")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("性别")
    private Boolean gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("地址")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("个性签名")
    private String signature;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("头像路径")
    private String iconPath;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("背景路径")
    private String backgroundImagePath;


}
