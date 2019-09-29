package id.ariev27.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class TransactionDto {

    private String transactionId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date borrowDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date returnDate;
    private double price;
    private Integer qty;
    private String status;
    private Integer bookId;
    private Integer studentId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
