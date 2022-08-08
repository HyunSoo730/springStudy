package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach   //메서드들이 실행이 끝날 때마다 어떤 동작을 하는 것. 즉 콜백 메서드
    public void afterEach() {
        repository.clearStore();
        //이렇게 되면 테스트가 실행이 되고 끝날때마다 저장소를 지워. 그러면 테스트 순서가 상관 없어져!
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //검증은???
        //new에서와 result와 같은지 비교해야지
        //Assertions 사용!
        Assertions.assertEquals(member, result);  //이렇게 검증해도 되고
        assertThat(member).isEqualTo(result);  //이렇게 간편하게 해도 돼
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //shift f6      ctrl alt v    이 단축키들 암기.
        //그리고 테스트가 하나 끝날때마다 데이터를 clear 해줘야해
    }
}
