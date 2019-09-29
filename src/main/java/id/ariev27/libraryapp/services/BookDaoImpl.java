package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.BookDto;
import id.ariev27.libraryapp.enttity.Book;
import id.ariev27.libraryapp.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    private Type listType = new TypeToken<List<BookDto>>() {}.getType();
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBooks() throws Exception {
        try {
            List<Book> result = bookRepository.findAll();
            if (!result.isEmpty()) {
                return modelMapper.map(result, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<BookDto> getBookListByTitle(@RequestParam("title") String title) throws Exception {
        try {
            List<Book> result = bookRepository.findByTitleLike("%" + title + "%");
            if (!result.isEmpty()) {
                return modelMapper.map(result, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<BookDto> getBookListByAuthor(@RequestParam("author") String author) throws Exception {
        try {
            List<Book> result = bookRepository.findByAuthorLike("%" + author + "%");
            if (!result.isEmpty()) {
                return modelMapper.map(result, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BookDto getBookByBookId(Integer bookId) throws Exception {
        try {
            Optional<Book> result = bookRepository.findById(bookId);
            return result.map(books -> modelMapper.map(books, BookDto.class)).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BookDto addBook(BookDto bookTmp) throws Exception {
        try {
            Book newBook = modelMapper.map(bookTmp, Book.class);
            bookRepository.save(newBook);
            return bookTmp;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BookDto updateBookById(Integer bookId, BookDto book) throws Exception {
        try {
            boolean result = bookRepository.findById(bookId).isPresent();
            if (result) {
                Book newBook = new Book();
                newBook.setBookId(bookId);
                newBook.setTitle(book.getTitle());
                newBook.setAuthor(book.getAuthor());
                newBook.setPrice(book.getPrice());
                newBook.setStock(book.getStock());
                bookRepository.save(newBook);
                return book;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean deleteBookById(Integer bookId) throws Exception {
        try {
            boolean result = bookRepository.findById(bookId).isPresent();
            if (result) {
                bookRepository.deleteById(bookId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Book> findPaging(int page) {
        @SuppressWarnings("deprecation")
        Pageable pageable = new PageRequest(page, 5);
        return bookRepository.findPage(pageable);
    }

}
