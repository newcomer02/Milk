package com.allbreak.milk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 15:13
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "[order]")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"id","userId","courseId"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_no")
    private Integer courseNo;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "sk_price")
    private BigDecimal skPrice;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "pay_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payTime;

    @Column(name = "pay_status")
    private Integer payStatus;

}
