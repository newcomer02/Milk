package com.allbreak.milk.VO;

import com.allbreak.milk.entity.Course;
import lombok.Data;

/**
 * @ClassName CourseVO
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 10:16
 * @Version 1.0
 */

@Data
public class CourseVO {

    private Course course;

    private int CourseStatus = 0;

    private int remainTime = 0;


}
