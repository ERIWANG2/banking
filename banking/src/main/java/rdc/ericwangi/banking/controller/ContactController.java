package rdc.ericwangi.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdc.ericwangi.banking.dto.ContactDto;
import rdc.ericwangi.banking.services.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }


    @GetMapping("/")
    public ResponseEntity<List<ContactDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContactDto>>findAllByUserId(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }
}
