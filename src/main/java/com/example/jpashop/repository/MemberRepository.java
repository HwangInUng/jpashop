package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Repository
 * - JPA 예외를 Spring 추상화 예외로 변경
 * - 서비스 계층으로 예외를 반환하여 처리 가능토록 지원
 * */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMemberName(String memberName);
}
