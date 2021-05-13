package spring5_mybatis_study.service;

import java.util.Map;

import spring5_mybatis_study.dto.Student;

public interface StudentService {
	public Map<Integer ,Student> selectStudentForMap();
	public Map<Integer ,String> selectStudentForMap(int studId);
	public Map<Integer ,Student> selectStudentForMap2(int studId);
}
