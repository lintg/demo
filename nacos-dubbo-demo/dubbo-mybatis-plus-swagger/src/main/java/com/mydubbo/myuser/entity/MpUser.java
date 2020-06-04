package com.mydubbo.myuser.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuan
 * @since 2019-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MpUser extends Model<MpUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "用户地址")
    private String address;

    private String openid;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableLogic
    private Integer deleted;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
