package com.allbreak.milk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 16:30
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"id","openid","password","createTime","lastLoginTime"})
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String openid;

    private String account;

    private String password;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;
}
