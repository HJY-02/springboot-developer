package me.hjy.springdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public List<Member> getAllMembers() {
        return testRepository.findAll(); //select * from member;
    }

    public Member saveMember(Member member){
        return testRepository.save(member); //insert into member(id, name) values(member.getId(), member.getName())
    }


}
