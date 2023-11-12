package board.spring.repository;

import board.boardspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPassword(String email, String password);

    Member findByEmail(String email);
}



//package board.boardspring.repository;
//
//
//import board.boardspring.domain.Member;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class MemberRepository {
//
//    @PersistenceContext
//    EntityManager em;
//
//    // 회원 엔티티 저장
//    public void save(Member member){
//        em.persist(member);
//    }
//
//    // 회원 엔티티 조회
//    public Member findOne(Long id){
//        return em.find(Member.class, id);
//    }
//
//    // 닉네임으로 회원 엔티티 조회
//    public List<Member> findByName(String name) {
//        return em.createQuery("select m from Member m where m.nickname = :name", Member.class)
//                .setParameter("name",name)
//                .getResultList();
//    }
//
//    // 이메일과 비밀번호로 회원 조회 (로그인 시 사용)
//    public Member findByEmailAndPassword(String email, String password) {
//        return em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
//                .setParameter("email", email)
//                .setParameter("password", password)
//                .getResultList()
//                .stream()
//                .findFirst()
//                .orElse(null);
//    }
//}
