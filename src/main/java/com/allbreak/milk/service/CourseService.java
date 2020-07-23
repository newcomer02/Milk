package com.allbreak.milk.service;

import com.allbreak.milk.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    List<Course> findAllCourse();

    Course findOneCourse(Integer courseId);

    Course uploadCoverImg(Integer courseId, MultipartFile file) throws Exception;

    int reduceStock(Integer courseNo);

}
