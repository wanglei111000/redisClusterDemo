package com.test.redis;

import com.test.redis.entity.Course;
import com.test.redis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //测试 没有过期时间的api
    @Test
    public void test() {
        redisTemplate.opsForValue().set("nn", "0000111");
        String name = (String) redisTemplate.opsForValue().get("nn");
        System.out.println(name); //输出admin
    }

    //测试 有过期时间的api
    @Test
    public void testRedisWithExpireTime(){
        redisTemplate.opsForValue().set("tttt","wwwwww",30, TimeUnit.SECONDS);
    }

    //测试删除记录 api
    @Test
    public void delete(){
        redisTemplate.delete("time");
    }

    //测试 保存最想，对象需要被序列化
    @Test
    public void testSaveObject() throws Exception{
        Course course = new Course();
        course.setCourseNO("001");
        course.setName("舞蹈");
        course.setTeacherNum("T001");

        Student student = new Student();
        student.setAge(18);
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        student.setCourseList(courseList);
        student.setGender(true);
        student.setName("xiaoxionxion");
        student.setNickName("xioner");
        ValueOperations<String, Object > operations = redisTemplate.opsForValue();
        operations.set("student001",student);
    }


    @Test
    public void getObjectFromRedisCluster(){
        ValueOperations<String, Object > operations = redisTemplate.opsForValue();
        Student student = (Student) operations.get("student001");
        System.out.println(student.getName());
    }


    @Test
    public void testSaveList() throws Exception{
        Course course = new Course();
        course.setCourseNO("001");
        course.setName("舞蹈");
        course.setTeacherNum("T001");

        Course course2 = new Course();
        course2.setCourseNO("002");
        course2.setName("游泳");
        course2.setTeacherNum("T002");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course2);
        ValueOperations<String, Object > operations = redisTemplate.opsForValue();
        operations.set("courses",courseList);
    }


    @Test
    public void testGetList() throws Exception{
        ValueOperations<String, Object > operations = redisTemplate.opsForValue();
        List<Course> result = (List<Course>)operations.get("courses");
        System.out.println(result.get(0));
    }




}
