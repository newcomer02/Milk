package com.allbreak.milk.utils;

import com.allbreak.milk.VO.CourseVO;
import com.allbreak.milk.entity.Course;

/**
 * @ClassName CourseVOUtil
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 10:33
 * @Version 1.0
 */
public class CourseVOUtil {

    private static final int COURSE_NOT_START = 0;

    private static final int COURSE_PROCESSING = 1;

    private static final int COURSE_COMPLETED = 2;

    public static CourseVO courseToCourseVO(Course course) {
        if (course == null) return null;
        CourseVO courseVO = new CourseVO();

        long startTime = course.getStartTime().getTime();
        long endTime = course.getEndTime().getTime();
        long now = System.currentTimeMillis();
        int courseStatus = 0;
        int remainTime = 0;

        if (now < startTime) {
            courseStatus = COURSE_NOT_START;
            remainTime = (int) (startTime - now);
        }
        else if (now > endTime) {
            courseStatus = COURSE_COMPLETED;
            remainTime = -1;
        }
        else {
            courseStatus = COURSE_PROCESSING;
            remainTime = -1;
        }

        courseVO.setCourse(course);
        courseVO.setCourseStatus(courseStatus);
        courseVO.setRemainTime(remainTime);
        return courseVO;
    }
}
