package com.sp.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.sp.entity.Student;

public class DaoFactory {

	public static HashMap<Integer, Student> getStudents() throws ClassNotFoundException, SQLException {
		int h = 1;
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/test";
		String username = "sa";
		String password = "";
		String query = "select * from students";
		HashMap<Integer, Student> hmap = new HashMap<Integer, Student>();
		System.out.println("I am called I am Dao Factory");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Student s = new Student();

			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setDept(rs.getString(3));
			s.setDoj(rs.getString(4));
			hmap.put(h, s);
			h++;

		}
		st.close();
		conn.close();
		return hmap;
	}

	public static void insertStudent(int id, String name, String dept, String doj)
			throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/test";
		String username = "sa";
		String password = "";
		String query = "insert into students values(?,?,?,to_date(?,'DDMMYYYY'))";

		Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, dept);
		st.setString(4, doj);
		int count = st.executeUpdate();
		st.close();
		conn.close();

	}

	public static void updateStudent(int id, String name, String dept, String doj)
			throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/test";
		String username = "sa";
		String password = "";
		String query = "update students set name=?, dept=?, doj=to_date(?,'DDMMYYYY') where id=?";

		Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(4, id);
		st.setString(1, name);
		st.setString(2, dept);
		st.setString(3, doj);
		int count = st.executeUpdate();
		st.close();
		conn.close();

	}

	public static void deleteStudent(int id) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/test";
		String username = "sa";
		String password = "";
		String query = "delete from students where id=?";

		Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, id);

		int count = st.executeUpdate();
		st.close();
		conn.close();

	}

}
