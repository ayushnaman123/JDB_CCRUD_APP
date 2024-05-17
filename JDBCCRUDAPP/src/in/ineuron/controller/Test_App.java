package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import in.ineuron.ServiceFactory.StudentServiceFactory;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;

public class Test_App {

	static {
		System.out.println("<<<<<<<<< Welcome to CRUD APP >>>>>>>>>>>>");
		System.out.println("===========================================");
		System.out.println("--------------------------------------------");
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			
			System.out.println();
			System.out.println("1. CREATE ");
			System.out.println("2. READ ");
			System.out.println("3. UPDATE ");
			System.out.println("4. DELETE ");
			System.out.println("5. EXIT ");
			System.out.print("ENTER YOUR CHOICE , PRESS [1,2,3,4,5]");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOperation();
				break;
			case "2":
				selectOperation();
				break;
			case "3":
				updateRecord();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("****** Thanks for using Application ******");
				System.exit(0);
			default:
				System.out.println("Invalid option please try again with vaild option ");
				break;
			}
		}

	}

	private static void insertOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the name ");
		String sname = br.readLine();

		System.out.println("Enter the age ");
		String sage = br.readLine();

		System.out.println("Enter the address");
		String saddress = br.readLine();

		String msg = studentService.addStudent(sname, Integer.parseInt(sage), saddress);

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record inserted successfully ");
		} else {
			System.out.println("Record insertion failed ");

		}

	}

	private static void selectOperation() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enterr the id ::");
		String sid = br.readLine();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(Integer.parseInt(sid));

		if (std != null) {
			System.out.println("ID\tNAME\tAGE\tADDRESS");

			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getSaddress());
		} else {
			System.out.println("Record not found for the given id " + sid);
		}

	}

	private static void updateRecord() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the student id to be updated ");
		String sid = br.readLine();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sid));

		if (student != null) {
			Student newStudent = new Student();

			System.out.println("Student id is ::" + student.getSid());
			newStudent.setSid(student.getSid());

			System.out.print("Student old name " + student.getSname() + " Enter new name :");
			String newName = br.readLine();
			if (newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}

			System.out.print("Student old age " + student.getSage() + "  Enter new age  :");
			String newAge = br.readLine();
			if (newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}

			System.out.print("Sudent old address " + student.getSaddress() + " Enter  new address");
			String newAddress = br.readLine();
			if (newAddress.equals("") || newAddress == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddress);
			}

			String status = studentService.updateStudent(newStudent);
			if (status.equalsIgnoreCase("success")) {
				System.out.println("record updated successfully..");
			} else {
				System.out.println("record updation failed...");
			}
		} else {
			System.out.println("Record not available for the given id " + sid + " for updation ");
		}
	}

	private static void deleteOperation() throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the id ");
		String sid = br.readLine();

		String msg = studentService.delete(Integer.parseInt(sid));

		if (msg.equals("success")) {
			System.out.println("Record deleted  successfully ");
		} else if (msg.equalsIgnoreCase("not found")) {
			System.out.println("Record not available for the given id " + sid);
		} else {
			System.out.println("Record deletion failed ");
		}

	}
}