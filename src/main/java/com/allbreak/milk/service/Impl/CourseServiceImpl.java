package com.allbreak.milk.service.Impl;

import com.alibaba.fastjson.JSON;
import com.allbreak.milk.entity.Course;
import com.allbreak.milk.repository.CourseRepository;
import com.allbreak.milk.service.CourseService;
import com.allbreak.milk.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/20 18:22
 * @Version 1.0
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${prop.folder}")
    private String UPLOAD_FOLDER;

    private String ALL_COURSE_LIST = "allCourseList";

    public List<Course> findAllCourse() {
        // get from redis
        List<Course> courseList = new ArrayList<>();
        String courseListFromString = (String) redisUtil.get(ALL_COURSE_LIST);
        courseList =  JSON.parseArray(courseListFromString, Course.class);

        if (StringUtils.isEmpty(courseListFromString)) {
            // get from db
            courseList = courseRepository.findAll();
            // store in redis
            String courseStringFromLIst = JSON.toJSONString(courseList);
            redisUtil.set(ALL_COURSE_LIST, courseStringFromLIst, -1);
        }

        return courseList;
    }

    @Override
    public Course findOneCourse(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }

    @Override
    public Course uploadCoverImg(Integer courseId, MultipartFile file) throws Exception{
        Course course = courseRepository.findCourseByCourseId(courseId);
        if (course == null) {
            throw new Exception("上传图片失败");
        }

        byte[] bytes = file.getBytes();
        long startTs = System.currentTimeMillis();
        // 文件夹路径
        String filePath = UPLOAD_FOLDER + courseId;
        // 全路径，包含文件名
        String fileName = filePath + "/" + startTs + file.getOriginalFilename();
        Path path = Paths.get(fileName);
        if (!Files.isWritable(path)) {
            Files.createDirectories(Paths.get(filePath));
        }
        Files.write(path, bytes);

        String dbPath = courseId + "/" + startTs + file.getOriginalFilename();
        course.setCourseImg(dbPath);

        return courseRepository.save(course);
    }

    @Override
    public int reduceStock(Integer courseNo) {
        return courseRepository.reduceStockByCourseNo(courseNo);
    }
}
