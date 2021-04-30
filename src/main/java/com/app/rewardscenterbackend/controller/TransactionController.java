package com.app.rewardscenterbackend.controller;

import com.app.rewardscenterbackend.entity.Transactions;
import com.app.rewardscenterbackend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Profiles/{id}")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transactions> getAllTransactionsForCustomer(@PathVariable String id) throws InterruptedException, ExecutionException {
        return transactionService.getAllTransactions(id);
    }

}
