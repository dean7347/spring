package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    //저장소
    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성이슈가 있어서 콘커런트 해쉬맵을 사용해야 한다

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
