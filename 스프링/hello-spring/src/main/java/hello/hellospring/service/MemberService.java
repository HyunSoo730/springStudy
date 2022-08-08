package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {  //ctrl shift T   테스트 만들기
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }   //이렇게 외부에서 인자로 넣어주도록!!!!

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 안되는걸로.
        //ctrl alt v 하면 걍 만들어줘 ..!!!!
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {   //값이 있으면 동작
//            throw new IllegalStateException("이미 존재하는 회원입니다.!");
//        });
        validateDuplicateMember(member);  //중복회원 검증
        //근데 이렇게 되면 메서드로 뽑는게 좋아!   함수로 만들기.. 리팩토링..???

        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())   //이렇게 한번에 짜도 됨.
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }

    /**
     * 전체회원조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
