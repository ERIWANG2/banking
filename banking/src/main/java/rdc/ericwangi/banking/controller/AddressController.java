package rdc.ericwangi.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rdc.ericwangi.banking.dto.AddressDto;
import rdc.ericwangi.banking.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto adressDto){
        return ResponseEntity.ok(service.save(adressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{adress-id}")
    public ResponseEntity<AddressDto>findById(@PathVariable("adress-id") Integer adresseId){
        return ResponseEntity.ok(service.findById(adresseId));
    }

    @DeleteMapping("/{adress-id}")
    public ResponseEntity<Void> delete(@PathVariable("adress-id") Integer adressId){
        service.delete(adressId);
        return ResponseEntity.accepted().build();
    }


}
