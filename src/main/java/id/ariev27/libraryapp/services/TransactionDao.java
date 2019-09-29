package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.TransactionDto;
import id.ariev27.libraryapp.enttity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionDao {

	List<TransactionDto> getAllTransactions() throws Exception;
	TransactionDto getTransactionById(String TransactionId) throws Exception;
    Boolean deleteTransactionById(String TransactionId) throws Exception;
	Page<Transaction> findPaging(int page) throws Exception;

    @Transactional
    TransactionDto addTransaction(TransactionDto tmpTrans) throws Exception;
    @Transactional
    TransactionDto updateTransactionById(String trxId, TransactionDto tmpTrx) throws Exception;
}
