package id.ariev27.libraryapp.repository;

import id.ariev27.libraryapp.enttity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT t FROM Transaction t", countQuery = "SELECT COUNT(t) FROM Transaction t")
    Page<Transaction> findPage(Pageable pageable);
}
