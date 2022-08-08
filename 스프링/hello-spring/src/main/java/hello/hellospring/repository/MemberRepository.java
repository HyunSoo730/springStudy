package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);   //Optional은 없을 수도 있어서 감싸서 반환하기 위해???
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 회원들의 리스트 반환
}
