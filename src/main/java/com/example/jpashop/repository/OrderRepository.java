package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    /* EntityManager 사용 코드
    public List<Order> findAll(OrderSearch orderSearch) {
        // 쿼리 API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            // 주문과 회원 내부조인
            Join<Order, Member> m = o.join("member", JoinType.INNER);
            Predicate name = cb.like(
                    m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%"
            );
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        // 최대 1000건 조회
        TypedQuery<Order> query = entityManager.createQuery(cq).setMaxResults(1000);

        return query.getResultList();
    }
    */
}
