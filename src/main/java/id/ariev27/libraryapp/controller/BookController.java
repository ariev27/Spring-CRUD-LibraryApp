package id.ariev27.libraryapp.controller;

import id.ariev27.libraryapp.dto.BookDto;
import id.ariev27.libraryapp.enttity.Book;
import id.ariev27.libraryapp.services.BookDao;
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
public class BookController {

    private CommonResponse response = new CommonResponse();
    private CommonResponseGenerator comGen = new CommonResponseGenerator();

    @Autowired
    private BookDao bookDao;

    @GetMapping(Constants.BOOK_ADDR)
    public String getAllBooks() throws Exception {
        try {
            List<BookDto> result = bookDao.getAllBooks();
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.BOOK_BY_TITLE_ADDR)
    public String getBookListByTitle(@RequestParam("title") String title) throws Exception {
        try {
            List<BookDto> result = bookDao.getBookListByTitle(title);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.BOOK_BY_AUTHOR_ADDR)
    public String getBookListByAuthor(@RequestParam("author") String author) throws Exception {
        try {
            List<BookDto> result = bookDao.getBookListByAuthor(author);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.BOOK_BY_ID_ADDR)
    public String getBookByBookId(@PathVariable("bookId") int bookId) throws Exception {
        try {
            BookDto result = bookDao.getBookByBookId(bookId);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PostMapping(Constants.BOOK_ADDR)
    public String addBook(@RequestBody BookDto newBook) throws Exception {
        try {
            BookDto result = bookDao.addBook(newBook);
            response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.POST_SUCCESS_MESSAGE, result);
            return JsonUtil.generateJson(response);
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PutMapping(Constants.BOOK_BY_ID_ADDR)
    public String updateBookById(@PathVariable("bookId") Integer bookId, @RequestBody BookDto book) throws Exception {
        try {
            BookDto result = bookDao.updateBookById(bookId, book);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.UPDATE_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @DeleteMapping(Constants.BOOK_BY_ID_ADDR)
    public String deleteBookById(@PathVariable("bookId") Integer bookId) throws Exception {
        try {
            Boolean result = bookDao.deleteBookById(bookId);
            if (result) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.DELETE_SUCCESS_MESSAGE);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.BOOK_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.BOOK_PAGE_ADDR)
    public Page<Book> getPage(@RequestParam(name = "page", defaultValue = "0") int page) throws Exception {
        return bookDao.findPaging(page);
    }
}
