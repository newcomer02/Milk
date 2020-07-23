package com.allbreak.milk.controller;

import com.allbreak.milk.VO.CourseVO;
import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.annotation.JwtIgnore;
import com.allbreak.milk.component.Audience;
import com.allbreak.milk.entity.Course;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.service.CourseService;
import com.allbreak.milk.utils.CourseVOUtil;
import com.allbreak.milk.utils.JwtTokenUtil;
import com.allbreak.milk.utils.ResultVOUtil;
import com.allbreak.milk.validation.BasicValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/20 18:55
 * @Version 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private Audience audience;

    @GetMapping("/courseList")
    @JwtIgnore
    public ResultVO<List<Course>> getAllCourse() {
        return ResultVOUtil.success(courseService.findAllCourse());
    }

    @PostMapping("/uploadCoverImg")
    @JwtIgnore
    public ResultVO uploadAvatar(Integer courseId , MultipartFile file) throws Exception {
        ResultVO resultVO = BasicValidation.checkDataValidate(file);
        if (resultVO != null) return resultVO;

        Course course = courseService.uploadCoverImg(courseId, file);
        if (course != null) {
            return ResultVOUtil.success(course);
        }
        return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg());
    }

    @GetMapping("/courseDetail/{courseNo}")
    @JwtIgnore
    public ResultVO<CourseVO> courseDetail(@PathVariable Integer courseNo) {
        return ResultVOUtil.success(CourseVOUtil.courseToCourseVO(courseService.findOneCourse(courseNo)));
    }
}
