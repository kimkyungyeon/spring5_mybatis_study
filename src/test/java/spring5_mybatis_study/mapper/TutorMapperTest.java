package spring5_mybatis_study.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.Address;
import spring5_mybatis_study.dto.Course;
import spring5_mybatis_study.dto.PhoneNumber;
import spring5_mybatis_study.dto.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class} )
public class TutorMapperTest {
	protected static final Log log = LogFactory.getLog(StudentMapperTest.class);
	
	@Autowired
	private TutorMapper mapper;
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectTutorByTutorId() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Tutor findTutor = new Tutor();
		findTutor.setTutorId(1);
		
		Tutor tutor = mapper.selectTutorByTutorId(findTutor);
		Assert.assertEquals(tutor.getTutorId(), findTutor.getTutorId());
		
		log.trace(tutor.getTutorId() + " : " + tutor.getName());
		log.debug(tutor.toString());
		
		List<Course> list = tutor.getCourses();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void test02InsertTutorAndDeleteTutor() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Address address = new Address();
		address.setAddrId(2);
		PhoneNumber phone = new PhoneNumber("010-2222-2222");
		
		Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
		int res = mapper.insertTutor(tutor);
		
		Tutor findTutor = mapper.selectTutorByTutorId(tutor);
		log.debug(findTutor.toString());
		
		res += mapper.deleteTutor(tutor.getTutorId());
		
		Assert.assertEquals(2, res);
	}
	
	
	@Test
	public void test03InsertTutors() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Tutor> tutors = new ArrayList<Tutor>();
		Address address = new Address();
		address.setAddrId(2);
		tutors.add(new Tutor(6,"Kim","kymonk@naver.com",new PhoneNumber("010-6510-7277"),address));
		tutors.add(new Tutor(7,"Lee","Lee@naver.com",new PhoneNumber("010-3151-7277"),address));
		Map<String, Object> map = new HashMap<>();
		map.put("tutors", tutors);
		
		int res = mapper.insertTutors(map);
		Assert.assertEquals(2, res);
	}
	
	@Test
	public void test04deleteTutors() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Integer> tutorIds = Arrays.asList(6,7);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tutorIds", tutorIds);
		int res = mapper.deleteTutors(map);
		Assert.assertEquals(2, res);
	}
}
