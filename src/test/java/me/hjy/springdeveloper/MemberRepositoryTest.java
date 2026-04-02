package me.hjy.springdeveloper;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insertMembers.sql")
    @Test
    void getAllMembers(){
        //given(준비)

        //when(실행)
        List<Member> members=memberRepository.findAll();

        //then(검증)
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insertMembers.sql")
    @Test
    void getMemberById(){
        //given(준비)

        //when(실행)
        Member members=memberRepository.findById(2L).get();

        //then(검증)
        assertThat(members.getName()).isEqualTo("B");
    }

    @Sql("/insertMembers.sql")
    @Test
    void getMemberByName(){
        //given(준비)

        //when(실행)
        Member members=memberRepository.findByName("C").get();

        //then(검증)
        assertThat(members.getId()).isEqualTo(3);
    }

    @DisplayName("레코드 삽입 테스트")
    @Test
    @Transactional
    void saveMember(){
        //given
        Member m=new Member(null,"hjy");

        //when:레코드 삽입 테스트
        Member savedMember=memberRepository.save(m);
        //1.Member객체 m에 pk인 id가 없으면:insert into member(name) values("hjy");
        //2.Member 객체 m에 pk가 이미 설정되어 있으면: update member set name="hjy" where id=1;
        //save 메서드가 성공하면 삽입된 또는 update된 레코드를 Member객체로 반환
        //3.return new Member(부여된 id, "hjy");

        //then
        //Optional<Member>
        assertThat(savedMember.getId()).isNotNull();//삽입에 성공했는지 체크
        assertThat(memberRepository.findById(savedMember.getId()).get().getName()).isEqualTo("hjy");
        //select * from member where id=:id
        //return new Optional<member> (1L, "hjy")
        Long id= savedMember.getId();
        Optional<Member> result=memberRepository.findById(id);
        Member member=result.get();
        String name=member.getName();
        assertThat(name).isEqualTo("hjy");
        //위에 줄을 요약하면
        //assertThat(memberRepository.findById(savedMember.getId()).get().getName()).isEqualTo("hjy");이거임
    }
    @DisplayName("")
    @Test
    void saveMembers(){
        List<Member> members=List.of(new Member("HongGD"), new Member("Park MunSu"));

        memberRepository.saveAll(members);

        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Sql("/insertMembers.sql")
    @DisplayName("레코드 삭제 테스트")
    @Test
    void deleteAll(){
        //given
        //when
        memberRepository.deleteAll();

        //then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    @Sql("/insertMembers.sql")
    @DisplayName("업데이트 테스트")
    @Test
    void update(){
        //given
        Member member=memberRepository.findById(2L).get();
        //when

        member.changeName("HWANGJY");
//        memberRepository.save(member);

        //then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("HWANGJY");
    }

}
