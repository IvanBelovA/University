package ua.com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.service.TimeLessonService;

@Controller
@RequestMapping("/timelessons")
public class TimeLessonController {

    private TimeLessonService timeLessonService;

    @Autowired
    public TimeLessonController(TimeLessonService timeLessonService) {
        this.timeLessonService = timeLessonService;
    }

    @GetMapping()
    public String findAll(Model model) {
        
        model.addAttribute("timelessons", timeLessonService.findAll());
        return "timelessons/all";
    }
}
