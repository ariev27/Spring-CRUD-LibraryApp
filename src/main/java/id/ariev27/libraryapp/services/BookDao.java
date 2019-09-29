package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.BookDto;
import id.ariev27.libraryapp.enttity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookDao {

	List<BookDto> getAllBooks() throws Exception;
	BookDto getBookByBookId(Integer bookId) throws Exception;
	List<BookDto> getBookListByTitle(String title) throws Exception;
	List<BookDto> getBookListByAuthor(String author) throws Exception;
	BookDto addBook(BookDto bookTmp) throws Exception;
	BookDto updateBookById(Integer bookId, BookDto book) throws Exception;
	Boolean deleteBookById(Integer bookId) throws Exception;
	Page<Book> findPaging(int page) throws Exception;
}
