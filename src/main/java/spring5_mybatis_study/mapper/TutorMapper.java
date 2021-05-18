package spring5_mybatis_study.mapper;

import java.util.Map;

import spring5_mybatis_study.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
	
//	Transaction
	int insertTutor(Tutor tutor);
	int deleteTutor(int tutorId);
	
	int insertTutors(Map<String, Object> map);
	int deleteTutors(Map<String, Object> map);
}
