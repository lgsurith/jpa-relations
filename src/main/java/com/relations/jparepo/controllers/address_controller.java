package com.relations.jparepo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relations.jparepo.repositories.address_repo;
import com.relations.jparepo.models.Address;


@RestController
@RequestMapping("/address")
public class address_controller {

    @Autowired
    private address_repo addressRepository;

    @GetMapping("/test")
    public String test(){
        return "This Port Works !";
    }

    @GetMapping("/all")
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    @GetMapping()
    public Address getById(@RequestParam Integer id){
        if (addressRepository.existsById(id)){
            return addressRepository.findById(id).get();
        }else{
            throw new RuntimeException("Address Not Found");
        }
    }

    @PostMapping("/post")
    public Address addAddress(@RequestBody Address address){
        return addressRepository.save(address);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestParam Integer id , @RequestBody Address address){
        Address  updateAddress = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("address not found with the given id" + id));
        updateAddress.setStreetName(address.getStreetName());
        updateAddress.setHouseNumber(address.getHouseNumber());
        updateAddress.setZipCode(address.getZipCode());
        Address updatedAddress = addressRepository.save(updateAddress);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/delete")
    public String deleteAddress(@RequestParam Integer id){
        if (addressRepository.existsById(id)){
            addressRepository.deleteById(id);
            return "Address Deleted Successfully";
        }else{
            throw new RuntimeException("Address Not Found");
        }
    }
    
}
