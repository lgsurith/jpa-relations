package com.relations.jparepo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.relations.jparepo.repositories.employee_repo;
import com.relations.jparepo.repositories.projects_repo;
import com.relations.jparepo.models.Address;
import com.relations.jparepo.models.Employee;
import com.relations.jparepo.models.Projects;


@RestController
@RequestMapping("/employee")
public class employee_controller {

    @Autowired
    private employee_repo employeeRepository;

    @Autowired
    private projects_repo projectsRepository;

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
    public Employee addEmployee(@RequestBody Employee employee){
        Address address = employee.getAddress();
        address.setEmployee(employee);
        return employeeRepository.save(employee);
    }

    //posting the projects based on the many to many relationship)
    @PostMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<Employee> addProject(
        @PathVariable Integer employeeId,
        @PathVariable Integer projectId){
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee Not Found with the given employeeID" + employeeId));
            Projects project  = projectsRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found with the given projectID" + projectId));

            employee.addProject(project);
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