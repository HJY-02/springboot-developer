package me.hjy.springdeveloper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    @GetMapping("/quiz") //localhost:8080/quiz?code=1
    public ResponseEntity<String> quiz(@RequestParam("code") int code){
        switch (code){
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("bad Request");
            default:
                return ResponseEntity.ok().body("ok");
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestParam("Code") int code){
        switch (code){
            case 1:
                return ResponseEntity.status(404).body("Forbidden");
            default:
                return ResponseEntity.ok().body("Ok");
        }
    }
}
record Code(int value){}
