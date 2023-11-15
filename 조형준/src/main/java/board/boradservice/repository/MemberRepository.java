package board.boradservice.repository;



import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;

import java.util.Optional;


@Repository
//@RequiredArgsConstructor
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> { // Member : 엔티티 타입 / Long : 엔티티 식별자

    /**
     * Optional로 선언하는 이유
     * 1. 만약 값이 없는 경우 Optional.empty()를 반환하여 값이 없음을 명시적으로 알려줄 수 있음(즉, null이 아닌 명시적으로 빈 상태임을 알려줄 수 있음)
     * 1. API 일관성 : Spring Data JPA에서는 대부분의 findOne, findById 등이 Optional을 반환하므로 일관성을 유지하려면 Optional을 사용하는 것이 자연스러울 수 있음
     * 2. null을 처리할 필요가 없어지므로 코드가 간결해짐 (if (member == null) 대신 if (optionalMember.isPresent()))
     */

//    Optional<Member> findByEmailAndPassword(String memberEmail, String memberPassword);

    Optional<Member> findByMemberEmail(String memberEmail);

//    Member findByMemberId(Long memberId);


//    private final EntityManager em;
//
//    public void save(Member member) {
//        em.persist(member); // JPA에 member 저장
//    }
//
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
//
//    public Member findOne(String email) {
//        TypedQuery<Member> query = em.createQuery("select m from Member m where m.memberEmail = :email", Member.class)
//                .setParameter("email", email);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null; // 원하는 결과가 없을 경우에 대한 처리
//        }
//    }
//
//    public List<Member> findByEmail(String email) {
//        return em.createQuery("select m from Member m where m.memberEmail = :email", Member.class)
//                .setParameter("email", email)
//                .getResultList();
//    }
//
//    public List<Member> findById(Long id) {
//        return em.createQuery("select m from Member m where m.id = :id", Member.class)
//                .setParameter("id", id)
//                .getResultList();
//    }
//
//    public List<Member> findByPassword(String password) {
//        return em.createQuery("select m from Member m where m.memberPassword = :password", Member.class)
//                .setParameter("password", password)
//                .getResultList();
//    }
}