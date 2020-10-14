package com.bit.university.db;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bit.university.vo.StudentVo;

public class StudentManager {

	public static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "com/example/demo/db/sqlMapConfig.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.print("예외발생:" + e.getMessage());
		}
	}

	public static StudentVo getStudent(String username) {
		StudentVo student_vo = null;
		SqlSession session = sqlSessionFactory.openSession();
		HashMap map = new HashMap();
		map.put("username", username);
		student_vo = session.selectOne("member.selectMember", map);
		session.close();
		return student_vo;
	}
}