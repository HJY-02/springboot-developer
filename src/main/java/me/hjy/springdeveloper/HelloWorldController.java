package me.hjy.springdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello1")
    public String hello(){
        return "Hello World";
    }

//    http://localhost:8080/student?firstName=Junyeon&lastName=Hwang
    @GetMapping("/student")
    public Student getStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return new Student(firstName, lastName);
    }

    //    http://localhost:8080/student/Junyeon/Hwang
    @GetMapping("student/{firstName}/{lastName}")
    public Student getStudent2(@PathVariable String firstName,@PathVariable String lastName){
        return new Student(firstName, lastName);
    }

    @GetMapping("/hello")
    public String getStudent3(@RequestParam("name") String name){
        return "반갑습니다."+name+"님";
    }


}


