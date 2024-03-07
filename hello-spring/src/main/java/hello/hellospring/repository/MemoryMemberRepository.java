package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); // 맵으로 멤버의 ID와 멤버의 참조변수를 저장
    private static long sequence = 0L;
    @Override
    public Member save(Member member) { // 멤버저장 : 멤버의 객체를 인자로 받아 id 생성, 맵 스토어에 id와 변수 저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id));
    }  // 스토어에서 id에 대한 변수를 찾아 반환

    @Override
    public Optional<Member> findByName(String name) { // 스토어에서 스트림으로 모든 네임을 꺼내어 동일한 네임을 찾아 반환
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    } // 스토어의 모든 값들을 리스트로 반환

    public void clearStore(){
        store.clear();
    } // 메모리 클리어
}
