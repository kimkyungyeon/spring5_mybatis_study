package spring5_mybatis_study.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class} )
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public class CourseMapperTest {
	protected static final Log log = LogFactory.getLog(CourseMapperTest.class);

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private CourseMapper mapper;

	@Test
	public void test01SelectCourseByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 1);
		List<Course> list = mapper.selectCoursesByCondition(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseName", "%Java%");
		List<Course> list = mapper.selectCoursesByCondition(map);
		
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test03SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", cal.getTime());
		List<Course> list = mapper.selectCoursesByCondition(map);
		
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectCaseCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("searchBy", "Tutor");
		map.put("tutorId", 1);
		List<Course> list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.replace("searchBy", "CourseName");
		map.remove("tutorId");
		map.put("courseName", "%Java%");
		list = mapper.selectCaseCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test05SelectWhereCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<String,Object> map = new HashMap<String, Object>();
		List<Course> list = mapper.selectWhereCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.put("tutorId", 1);
		list=mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.put("courseName", "%Core%");
		list=mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		System.out.println();
		
		map.clear();
		map.put("endDate", new Date());
		list = mapper.selectWhereCourses(map);
		list.stream().forEach(System.out::println);
		System.out.println();
	}
	
	@Test
	public void test06SelectTrimCourses() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Course> list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.put("tutorId", 1);
		list=mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.clear();
		map.put("courseName", "%java%");
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
		map.clear();
		map.put("tutorId", 1);
		list = mapper.selectTrimCourses(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test07SelectCoursesForeachByTutors() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		
		List<Integer> tutorIds = new ArrayList<Integer>();
		tutorIds.add(1);
		tutorIds.add(2);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tutorIds", tutorIds);
		
		List<Course> list = mapper.selectCoursesForeachByTutors(map);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test08insertcourses() {
		List<Course> tutors = new ArrayList<Course>();
		tutors.add(new Course(4,"mysql","database", new Date(), new Date(), 3));
		tutors.add(new Course(5,"mssql","database", new Date(), new Date(), 3));
		tutors.add(new Course(6,"mariaDB","database", new Date(), new Date(), 4));
		System.out.println("tutors >>> "+ tutors);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutors", tutors);
		
		int res = mapper.insertCourses(map);
		Assert.assertEquals(3, res);
	}
	
	@Test
	public void test09deleteCourses() {
		List<Integer> courseIds = Arrays.asList(4, 5, 6);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseIds", courseIds);
		int res = mapper.deleteCourses(map);
		Assert.assertEquals(3, res);
	}
	
	@Test
	public void test10updateSetCourse() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Course course = new Course();
		course.setCourseId(1);
		course.setName("파이썬");
		System.out.println(course);
		int result = mapper.updateSetCourses(course);
		Assert.assertSame(1, result);
		
		Course course1 = new Course();
		course.setCourseId(1);
		course.setName("Quickstart Core Java");
		System.out.println(course);
		int result1 = mapper.updateSetCourses(course);
		Assert.assertSame(1, result1);
		
		
	}
	
	@Test
	public void test11insertCourseAndDeleteCourse() {
		Course course = new Course(7,"oracle","database",new Date(), new Date(), 4);
		int res = mapper.insertCourse(course);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorId", 4);
		
		List<Course> list = mapper.selectCoursesByCondition(map);
		list.stream().forEach(System.out::println);
		
		res+=mapper.deleteCourse(course.getCourseId());
		Assert.assertEquals(2, res);
	}
	
	@Test
	public void test12insertCourse() {
		
	}
}
