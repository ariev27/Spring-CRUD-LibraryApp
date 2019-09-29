package id.ariev27.libraryapp.repository;

import id.ariev27.libraryapp.enttity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentReporsitory extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s FROM Student s",
            countQuery = "SELECT COUNT(s) FROM Student s")
    Page<Student> findPage(Pageable pageable);
}
