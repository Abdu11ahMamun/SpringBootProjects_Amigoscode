package com.MySQLTest.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository=studentRepository;
	}
	
    public List<student> getStudents(){
       return studentRepository.findAll();
    }
    public void addNewStudent(student student) {
    	Optional<student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
    	
    	if(studentByEmail.isPresent()) {
    		throw new IllegalStateException("Email taken");
    	}
    	studentRepository.save(student);
    	//System.out.println(student);
    }

	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
		boolean exists= studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id "+ studentId+ " does not exists");
			
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
				"student with id "+studentId+" does not exist "));
		if(name !=null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
	}
	

}