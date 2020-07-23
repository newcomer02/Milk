package com.allbreak.milk.repository;

import com.allbreak.milk.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseByCourseId(Integer courseId);

    @Modifying
    @Query(value = "update course set stock = stock - 1 where course_id = :course_id and stock > 0", nativeQuery = true)
    int reduceStockByCourseNo(@Param("course_id") Integer course_id);
}
