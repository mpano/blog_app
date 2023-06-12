package net.myblog.blog.Controller;

import net.myblog.blog.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("/student")
    public Student studentinfo(){
        Student student = new Student("23717","mpano" ," akim");
        return student ;
    }
    @GetMapping("/allstudent")
    public List<Student> getallstudent(){
        List<Student> students= new ArrayList<>();
        students.add(new Student("23717","mpano" ," akim"));
        students.add(new Student("23717","mpano" ," akim"));
        students.add(new Student("23717","mpano" ," akim"));
        return students;
    }
    @GetMapping("/student/{id}")
    public Student newexample(@PathVariable("id") String id){
        Student student = new Student(id," keren", "Uhirwa");
        return student;
    }

    @PostMapping("/student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student){
        student.getId();
        student.getFirstname();
        student.getLastname();
        return student;
    }

    @PutMapping("/student/update/{id}")
    public Student updatestudent(@RequestBody Student student, @PathVariable("id") String id){
        student.getLastname();
        student.getFirstname();
        return student;
    }
}
