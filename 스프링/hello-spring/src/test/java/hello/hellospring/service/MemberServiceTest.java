package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //테스트는 사실 어떤 상황이 주어졌을 때 이걸 실행했을 때 결과가 이게 나와야해! 이런 형식.

    @BeforeEach    //각 테스트를 실행하기 전에 !!!
    public void beforeEach(){   //MemoryMemberRepository를 만들고 그걸 위 변수에 넣어두고!
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);  //그걸 memberService생성자??에 넣어줘
        //그러면 같은 memoryRepository가 사용돼. 즉 같은 인스턴스 사용!!
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test    //테스트 코드는 한글로 적어도 돼!
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //테스트는 예외가 더 중요!
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //두번쨰 인자에 있는 로직을 실행할건데 첫번째 인자에 있는 예외가 터져야해! 라는 뜻.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//        try{
//            memberService.join(member2);
//            fail();    //예외가 발생해야 한다!는 뜻
//        }
//        catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
          //예외상황! try catch문을 통해 해결..?
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}