package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.StudentDto;
import id.ariev27.libraryapp.enttity.Student;
import id.ariev27.libraryapp.repository.StudentReporsitory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private StudentReporsitory studentReporsitory;

    @Override
    public List<StudentDto> getAllStudents() throws Exception {
        try {
            List<Student> result = studentReporsitory.findAll();
            if (!result.isEmpty()) {
                Type listType = new TypeToken<List<StudentDto>>() {}.getType();
                return modelMapper.map(result, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public StudentDto getStudentById(Integer bookId) throws Exception {
        try {
            Optional<Student> result = studentReporsitory.findById(bookId);
            return result.map(Students -> modelMapper.map(Students, StudentDto.class)).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public StudentDto addStudent(StudentDto tmpStudent) throws Exception {
        try {
            Student newBook = modelMapper.map(tmpStudent, Student.class);
            studentReporsitory.save(newBook);
            return tmpStudent;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public StudentDto updateStudentById(Integer studentId, StudentDto student) throws Exception {
        try {
            boolean result = studentReporsitory.findById(studentId).isPresent();
            if (result) {
                Student newStudent = new Student();
                newStudent.setStudentId(studentId);
                newStudent.setName(student.getName());
                newStudent.setNim(student.getNim());
                studentReporsitory.save(newStudent);
                return student;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean deleteStudentById(Integer studentId) throws Exception {
        try {
            boolean result = studentReporsitory.findById(studentId).isPresent();
            if (result) {
                studentReporsitory.deleteById(studentId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Student> findPaging(int page) {
        @SuppressWarnings("deprecation")
        Pageable pageable = new PageRequest(page, 5);
        return studentReporsitory.findPage(pageable);
    }

}
