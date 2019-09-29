package id.ariev27.libraryapp.repository;

import id.ariev27.libraryapp.enttity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleLike(String title);
    List<Book> findByAuthorLike(String author);
    @Query(value = "SELECT b FROM Book b",
            countQuery = "SELECT COUNT(b) FROM Book b")
    Page<Book> findPage(Pageable pageable);

}