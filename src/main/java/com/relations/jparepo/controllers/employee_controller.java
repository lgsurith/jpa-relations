package com.relations.jparepo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.relations.jparepo.repositories.employee_repo;
import com.relations.jparepo.models.Employee;


@RestController
@RequestMapping("/employee")
public class employee_controller {

    @Autowired
    private employee_repo employeeRepository;

    @GetMapping("/test")
    public String test(){
        return "This Port Works !";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping()
    public ResponseEntity<Employee> getById(@RequestParam Integer id){
        if (employeeRepository.existsById(id)){
            return ResponseEntity.ok(employeeRepository.findById(id).get());
        }else{
            throw new RuntimeException("Employee Not Found with the given" + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(){
        Employee employee = new Employee();
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestParam Integer id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found with the given" + id));
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestParam Integer id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            throw new RuntimeException("Employee Not Found with the given" + id);
        }
    }
}