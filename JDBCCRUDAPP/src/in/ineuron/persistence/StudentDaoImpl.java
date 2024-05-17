package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		try {
			connection = JdbcUtil.getJdbcConnection();

			String sqlInsertQuery = "insert into students(`name`, `age`, `address`) Values(?,?,?)";
			if (connection != null) {

				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected == 1) {
					return "success";
				}

			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {

		String sqlSelectQuery = "Select id,name,age,address from students Where id = ?";
		Student student = null;

		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}

			if (pstmt != null) {
				pstmt.setInt(1, sid);
			}
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {

				if (resultSet.next()) {
					student = new Student();

					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));

					return student;
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStuden(Student student) {
		String sqlUpdateQuery = "update students set name=?,age=?,address=? Where id =?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setInt(4, student.getSid());

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public String delete(Integer sid) {
		String sqlDeleteQuery = "delete from students where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);

			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
			}

			if (pstmt != null) {
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failure";
	}

}
