package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.StudentDto;
import id.ariev27.libraryapp.enttity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentDao {

    List<StudentDto> getAllStudents() throws Exception;
    StudentDto getStudentById(Integer studentId) throws Exception;
    StudentDto addStudent(StudentDto tmpStudent) throws Exception;
    StudentDto updateStudentById(Integer studentId, StudentDto student) throws Exception;
    Boolean deleteStudentById(Integer studentId) throws Exception;
    Page<Student> findPaging(int page) throws Exception;
}
