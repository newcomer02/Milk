package com.allbreak.milk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Course
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/20 18:02
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "course")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"id"})
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_lecturer")
    private String courseLecturer;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private BigDecimal price;

    @Column(name = "course_img")
    private String courseImg;

    private String introduction;

    private Integer stock;
}
