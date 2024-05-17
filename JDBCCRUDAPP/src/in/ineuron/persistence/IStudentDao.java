package in.ineuron.persistence;

import in.ineuron.dto.Student;

public interface IStudentDao {
	public String addStudent(String sname, Integer sage, String saddress);
	public Student searchStudent(Integer sid);
	public String updateStuden(Student student);
	public String delete(Integer sid);

}
