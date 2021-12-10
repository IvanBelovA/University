package ua.com.foxminded.university;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


import ua.com.foxminded.university.configuration.ApplicationConfig;
import ua.com.foxminded.university.dao.impl.StudentDaoImpl;
import ua.com.foxminded.university.model.Student;


public class UniversityApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        StudentDaoImpl dao = new StudentDaoImpl(context.getBean("jdbcTemplate", JdbcTemplate.class));
        Student std = new Student("lalal", "skksk");
        dao.add(std);
        
        System.out.println(dao.findAll());
        context.close();
    }

}
