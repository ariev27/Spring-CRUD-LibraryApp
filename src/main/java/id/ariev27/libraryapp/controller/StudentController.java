package id.ariev27.libraryapp.controller;

import id.ariev27.libraryapp.dto.StudentDto;
import id.ariev27.libraryapp.enttity.Student;
import id.ariev27.libraryapp.services.StudentDao;
import id.ariev27.jsonresponse.CommonResponse;
import id.ariev27.jsonresponse.CommonResponseGenerator;
import id.ariev27.jsonresponse.JsonUtil;
import id.ariev27.libraryapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api", produces = "application/json; charset=UTF-8")
public class StudentController {

    private CommonResponse response = new CommonResponse();
    private CommonResponseGenerator comGen = new CommonResponseGenerator();

    @Autowired
    private StudentDao studentDao;

    @GetMapping(Constants.STUDENT_ADDR)
    public String getAllStudents() throws Exception {
        try {
            List<StudentDto> result = studentDao.getAllStudents();
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.STUDENT_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.STUDENT_BY_ID_ADDR)
    public String getStudentById(@PathVariable("studentId") int studentId) throws Exception {
        try {
            StudentDto result = studentDao.getStudentById(studentId);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.STUDENT_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PostMapping(Constants.STUDENT_ADDR)
    public String addStudent(@RequestBody StudentDto newStudent) throws Exception {
        try {
            StudentDto result = studentDao.addStudent(newStudent);
            response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.POST_SUCCESS_MESSAGE, result);
            return JsonUtil.generateJson(response);
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PutMapping(Constants.STUDENT_BY_ID_ADDR)
    public String updateStudentById(@PathVariable("studentId") Integer studentId, @RequestBody StudentDto student) throws Exception {
        try {
            StudentDto result = studentDao.updateStudentById(studentId, student);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.UPDATE_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.STUDENT_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @DeleteMapping(Constants.STUDENT_BY_ID_ADDR)
    public String deleteStudentById(@PathVariable("studentId") Integer studentId) throws Exception {
        try {
            Boolean result = studentDao.deleteStudentById(studentId);
            if (result) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.DELETE_SUCCESS_MESSAGE);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.STUDENT_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.STUDENT_PAGE_ADDR)
    public Page<Student> getPage(@RequestParam(name = "page", defaultValue = "0") int page) throws Exception {
        return studentDao.findPaging(page);
    }
}
