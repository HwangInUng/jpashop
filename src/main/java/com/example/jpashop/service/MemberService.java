package com.example.jpashop.service;

import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member) {
        // 중복 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByMemberName(member.getMemberName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
