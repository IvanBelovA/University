package ua.com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentsService;
    
    @Autowired
    public StudentController(StudentService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping()
    public String list() {
        return "students/studentshome";
    }
    
    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("students", studentsService.findAll());
        return "students/all";
    }
    
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }
    
    @PostMapping()
    public void create(@ModelAttribute("student") Student student) {
        studentsService.add(student);
    }
    
    @GetMapping("/delete")
    public void delete() {
        
    }
}
