package me.hjy.springdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController=@Controller+@ResponseBody
@RestController
//모든 메서드가 데이터를 리턴한다.
//@Controller //기본적으로 리턴하는 것이 HTML파일이름이다.
public class TestController {
    @Autowired
    private TestService testService;

//    @GetMapping("/test")
//    //@ResponseBody
//    public ResponseEntity<List<Member>> getAllMembers(){
//        return ResponseEntity.ok(testService.getAllMembers());
//    }
//

    @GetMapping("/test")
    //@ResponseBody
    public String getAllMembers(){
        return "Hello World";
    }


    @PostMapping("/test")
    public ResponseEntity<Member> createMember(@RequestBody Member member){
        return ResponseEntity.ok(testService.saveMember(member));
    }
}
