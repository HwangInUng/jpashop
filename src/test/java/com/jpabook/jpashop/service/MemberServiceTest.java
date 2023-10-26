package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void join() throws Exception {
        // Given
        Member member = new Member();
        member.setMemberName("test");

        // When
        Long saveId = memberService.join(member);

        // Then
        Assertions.assertEquals(member, memberService.findOne(saveId));
    }

    @Test
    @DisplayName("중복 예외발생 테스트")
    void duplicateMemberName() throws Exception{
        // Given
        Member member1 = new Member();
        member1.setMemberName("test1");
        Member member2 = new Member();
        member2.setMemberName("test1");

        // When
        memberService.join(member1);

        // Then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}
