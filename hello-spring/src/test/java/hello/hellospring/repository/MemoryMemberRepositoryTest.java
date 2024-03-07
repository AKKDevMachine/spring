package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){ // 테스트가 하나 끝날때마다 메모리를 클리어 해줌
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findByID(member.getId()).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // 두 값을 비교하여 일치하는지 확인
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        Member reuslt = repository.findByName("spring1").get();
        assertThat(reuslt).isEqualTo(member1); // 두 값을 비교하여 일치하는지 확인
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2); // 두 값을 비교하여 일치하는지 확인
    }
}
