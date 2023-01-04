package rdc.ericwangi.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdc.ericwangi.banking.dto.TransactionSumDetails;
import rdc.ericwangi.banking.services.StatistiqueService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
@RequiredArgsConstructor
public class StatistiqueController {

    private final StatistiqueService service;
    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity<List<TransactionSumDetails>>findSumTransactionByDate(
            @RequestParam("date-debut") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateDebut,
            @RequestParam("date-fin") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateFin,
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findSumTransactionByDate(dateDebut,dateFin,userId));
    }

    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfert/{user-id}")
    public ResponseEntity<BigDecimal> highestTransfert(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }

    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }

    @GetMapping("/highest-retrait/{user-id}")
    public ResponseEntity<BigDecimal> highestRetrait(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestRetrait(userId));
    }

}
