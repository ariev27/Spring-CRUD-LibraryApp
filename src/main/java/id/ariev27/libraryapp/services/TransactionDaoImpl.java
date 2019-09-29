package id.ariev27.libraryapp.services;

import id.ariev27.libraryapp.dto.TransactionDto;
import id.ariev27.libraryapp.enttity.Book;
import id.ariev27.libraryapp.enttity.Student;
import id.ariev27.libraryapp.enttity.Transaction;
import id.ariev27.libraryapp.repository.BookRepository;
import id.ariev27.libraryapp.repository.StudentReporsitory;
import id.ariev27.libraryapp.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class TransactionDaoImpl implements TransactionDao {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentReporsitory studentReporsitory;

    @Override
    public List<TransactionDto> getAllTransactions() throws Exception {
        try {
            List<Transaction> result = transRepo.findAll();
            if (!result.isEmpty()) {
                Type listType = new TypeToken<List<TransactionDto>>() {}.getType();
                return modelMapper.map(result, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public TransactionDto getTransactionById(String transactionId) throws Exception {
        try {
            Optional<Transaction> result = transRepo.findById(transactionId);
            return result.map(data -> modelMapper.map(data, TransactionDto.class)).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @Override
    public TransactionDto addTransaction(TransactionDto tmpTrx) throws Exception {
        try {
            Optional<Student> student = studentReporsitory.findById(tmpTrx.getStudentId());
            Optional<Book> book = bookRepository.findById(tmpTrx.getBookId());

            if (book.isEmpty()) {
                throw new Exception("Sorry, book with id : " + tmpTrx.getBookId() + " not found");
            } else if (book.get().getStock() <= 0) {
                throw new Exception("Sorry, we're out of stock");
            } else if (student.isEmpty()) {
                throw new Exception("Sorry, student with id : " + tmpTrx.getStudentId() + " not found");
            }

            Transaction newTrx = new Transaction();
            book.get().setStock(book.get().getStock() - tmpTrx.getQty());
            newTrx.setBookId(book.get());
            newTrx.setStudentId(student.get());
            newTrx.setReturnDate(tmpTrx.getReturnDate());
            newTrx.setBorrowDate(tmpTrx.getBorrowDate());
            newTrx.setPrice(tmpTrx.getPrice());
            newTrx.setQty(tmpTrx.getQty());
            newTrx.setStatus("Borrowed");
            transRepo.save(newTrx);
            return modelMapper.map(newTrx, TransactionDto.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @Override
    public TransactionDto updateTransactionById(String trxId, TransactionDto tmpTrx) throws Exception {
        try {
            Optional<Transaction> result = transRepo.findById(trxId);
            if (result.isPresent()) {
                Optional<Book> book = bookRepository.findById(tmpTrx.getBookId());
                Optional<Student> student = studentReporsitory.findById(tmpTrx.getStudentId());

                if (book.isEmpty()) {
                    throw new Exception("Sorry, book with id : " + tmpTrx.getBookId() + " not found");
                } else if (book.get().getStock() < tmpTrx.getQty()) {
                    throw new Exception("Stock must be higher than qty");
                } else if (student.isEmpty()) {
                    throw new Exception("Sorry, student with id : " + tmpTrx.getStudentId() + " not found");
                }

                Transaction newTrans = new Transaction();
                newTrans.setTransactionId(trxId);
                newTrans.setBookId(book.get());
                newTrans.setStudentId(student.get());
                newTrans.setStatus(tmpTrx.getStatus());
                newTrans.setBorrowDate(tmpTrx.getBorrowDate());
                newTrans.setReturnDate(tmpTrx.getReturnDate());
                newTrans.setPrice(tmpTrx.getPrice());
                book.get().setStock((book.get().getStock() - tmpTrx.getQty()) + result.get().getQty());
                newTrans.setQty(tmpTrx.getQty());
                transRepo.save(newTrans);
                return modelMapper.map(newTrans, TransactionDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean deleteTransactionById(String transactionId) throws Exception {
        try {
            boolean result = transRepo.findById(transactionId).isPresent();
            if (result) {
                transRepo.deleteById(transactionId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Transaction> findPaging(int page) {
        @Deprecated
        Pageable pageable = new PageRequest(page, 5);
        return transRepo.findPage(pageable);
    }

}
