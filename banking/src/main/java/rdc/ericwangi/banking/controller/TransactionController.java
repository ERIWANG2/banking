package rdc.ericwangi.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdc.ericwangi.banking.dto.TransactionDto;
import rdc.ericwangi.banking.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer>save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto>findById(@PathVariable("transaction-id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity< List<TransactionDto> >findAllByUserId(@PathVariable("user-id") Integer id){
        return ResponseEntity.ok(service.findAllByUserId(id));
    }


    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void>delete(@PathVariable("transaction-id") Integer id){
        return ResponseEntity.accepted().build();
    }




}
