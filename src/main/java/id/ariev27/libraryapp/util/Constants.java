package id.ariev27.libraryapp.util;

public class Constants {
    public static final String BOOK_ADDR = "/book";
    public static final String BOOK_BY_ID_ADDR = BOOK_ADDR + "/{bookId}";
    public static final String BOOK_BY_TITLE_ADDR = BOOK_ADDR + "/title";
    public static final String BOOK_BY_AUTHOR_ADDR = BOOK_ADDR + "/author";
    public static final String BOOK_PAGE_ADDR = BOOK_ADDR + "/page";

    public static final String STUDENT_ADDR = "/student";
    public static final String STUDENT_BY_ID_ADDR = STUDENT_ADDR + "/{studentId}";
    public static final String STUDENT_PAGE_ADDR = STUDENT_ADDR + "/page";

    public static final String TRANSACTION_ADDR = "/transaction";
    public static final String TRANSACTION_BY_ID_ADDR = TRANSACTION_ADDR + "/{transactionId}";
    public static final String TRANSACTION_PAGE_ADDR = TRANSACTION_ADDR + "/page";

    public static final String BOOK_NOT_FOUND_MESSAGE = "Book not found";
    public static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found";
    public static final String TRANSACTION_NOT_FOUND_MESSAGE = "Transaction not found";

    public static final String GET_SUCCESS_MESSAGE = "Get success";
    public static final String POST_SUCCESS_MESSAGE = "Post success";
    public static final String UPDATE_SUCCESS_MESSAGE = "Update success";
    public static final String DELETE_SUCCESS_MESSAGE = "Delete success";

    public static final String SUCCESS_CODE = "200";
    public static final String NOT_FOUND_CODE = "404";
    public static final String ERROR_CODE = "500";
}
