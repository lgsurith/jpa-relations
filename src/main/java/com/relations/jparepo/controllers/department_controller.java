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

import com.relations.jparepo.repositories.department_repo;
import com.relations.jparepo.models.Department;

@RestController
@RequestMapping("/department")
public class department_controller {
    
    @Autowired
    private department_repo departmentRepository;

    @GetMapping("/test")
    public String test(){
        return "This Port Works !";
    }

    @GetMapping("/all")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    //to get all the employees by department
    @GetMapping("/employees")
    public List<Department> getAllEmployees(){
        return departmentRepository.findAll();
    }
    
    @PostMapping("/add")
     public Department addDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestParam Integer id , @RequestBody Department department){
        Department  updatedepartment = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("address not found with the given id" + id));
        updatedepartment.setName(department.getName());
        Department updatedDepartment = departmentRepository.save(updatedepartment);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDepartment(@RequestParam Integer id){
        if(departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
