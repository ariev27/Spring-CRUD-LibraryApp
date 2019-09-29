package id.ariev27.libraryapp.controller;

import id.ariev27.libraryapp.dto.TransactionDto;
import id.ariev27.libraryapp.enttity.Transaction;
import id.ariev27.libraryapp.services.TransactionDao;
import id.ariev27.jsonresponse.CommonResponse;
import id.ariev27.jsonresponse.CommonResponseGenerator;
import id.ariev27.jsonresponse.JsonUtil;
import id.ariev27.libraryapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json; charset=UTF-8")
public class TransactionController {

    private CommonResponse response = new CommonResponse();
    private CommonResponseGenerator comGen = new CommonResponseGenerator();

    @Autowired
    private TransactionDao transactionDao;

    @GetMapping(Constants.TRANSACTION_ADDR)
    public String getAllTrans() throws Exception {
        try {
            List<TransactionDto> result = transactionDao.getAllTransactions();
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.TRANSACTION_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.TRANSACTION_BY_ID_ADDR)
    public String getBookByBookId(@PathVariable("transactionId") String transactionId) throws Exception {
        try {
            TransactionDto result = transactionDao.getTransactionById(transactionId);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.GET_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.TRANSACTION_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PostMapping(Constants.TRANSACTION_ADDR)
    public String addTransaction(@RequestBody TransactionDto tmpTransaction) throws Exception {
        try {
            TransactionDto result = transactionDao.addTransaction(tmpTransaction);
            response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.POST_SUCCESS_MESSAGE, result);
            return JsonUtil.generateJson(response);
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @PutMapping(Constants.TRANSACTION_BY_ID_ADDR)
    public String updateTransactionById(@PathVariable("transactionId") String transactionId, @RequestBody TransactionDto transaction) throws Exception {
        try {
            TransactionDto result = transactionDao.updateTransactionById(transactionId, transaction);
            if (result != null) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.UPDATE_SUCCESS_MESSAGE, result);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.TRANSACTION_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @DeleteMapping(Constants.TRANSACTION_BY_ID_ADDR)
    public String deleteTransactionById(@PathVariable("transactionId") String transactionId) throws Exception {
        try {
            Boolean result = transactionDao.deleteTransactionById(transactionId);
            if (result) {
                response = comGen.generateCommonResponse(Constants.SUCCESS_CODE, Constants.DELETE_SUCCESS_MESSAGE);
                return JsonUtil.generateJson(response);
            } else {
                response = comGen.generateCommonResponse(Constants.NOT_FOUND_CODE, Constants.TRANSACTION_NOT_FOUND_MESSAGE);
                return JsonUtil.generateJson(response);
            }
        } catch (Exception e) {
            response = comGen.generateCommonResponse(Constants.ERROR_CODE, e.getMessage());
            return JsonUtil.generateJson(response);
        }
    }

    @GetMapping(Constants.TRANSACTION_PAGE_ADDR)
    public Page<Transaction> getPage(@RequestParam(name = "page", defaultValue = "0") int page) throws Exception {
        return transactionDao.findPaging(page);
    }
}
