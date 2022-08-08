package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {  //아까 만든 인터페이스를 implements해야지

    private static Map<Long, Member> store = new HashMap<>();  //저장을 어딘가에 해야하니깐!
    private static long sequence = 0L;  //키값 생성을 위해

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);   //Map에 저장 후..
        return member;  //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));   //null이 반환될 가능성이 있으면 Optional로 감싸
    }           //store에서 꺼내올꺼야. 근데 널일 수도 있잖아. 그래서 Optional로 감싸서 반환

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();  //하나라도 찾아라? 있다면 반환! 결과가 Optional로 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //멤버들만 반환하면 되니깐.
        //store는 Map인데.. store의 값. 즉 member만 리스트로 반환해!
    }

    public void clearStore(){
        store.clear();
    }
}
//지금 이렇게 짠 코드를 검증을 해야하잖아. 검증 --> 테스트 케이스를 작성하는거야!!!
//테스트 케이스 작성. 실무에서는 당연한건데. 처음이라면 ..
//지금 여기까지 짠 코드가 실제 동작하는지 테스트 코드 작성해보자.