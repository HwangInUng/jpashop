package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * @Repository
 * - JPA 예외를 Spring 추상화 예외로 변경
 * - 서비스 계층으로 예외를 반환하여 처리 가능토록 지원
 * */
@Slf4j
@Repository
public class MemberRepository {
    // 엔티티 매니저 의존성 주입
    // EntityManagerFactory의 역할을 해주는 어노테이션
    // 스프링 컨테이너에서 관리하고 제공
    @Autowired
    private EntityManager entityManager;

    // 저장(영속화)
    public void save(Member member) {
        entityManager.persist(member);
    }

    // 1건 조회
    public Member findOne(Long memberId) {
        return entityManager.find(Member.class, memberId);
    }

    // 여러건 조회
    public List findAll() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 조회
    public List findByName(String memberName) {
        return entityManager.createQuery(
                        "select m from Member m where m.memberName = :memberName", Member.class)
                .setParameter("memberName", memberName) // 파라미터 부여
                .getResultList();
    }
}
