package board.boradservice.repository;

import board.boradservice.domian.Board;
import board.boradservice.domian.Member;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
//@RequiredArgsConstructor
@Transactional
public interface BoardRepository extends JpaRepository<Board, Long> { // Member : 엔티티 타입 / Long : 엔티티 식별자

    public Optional<List<Board>> findByBoardTitle(String boardTitle);

    public Optional<List<Board>> findByMember(Member member);

//    public List<Board> findPostByMemberEmail(String memberEmail);

//    public Board findByBoardId(Long boardId);

//    private final EntityManager em;
//
//    /**
//     * 게시글 생성
//     */
//    public void save(Board board) {
//        em.persist(board); // JPA에 member 저장
//    }
//
//    /**
//     * 게시글 수정
//     */
//    public void modify(String title, String context, Long id) {
//        em.createQuery("update Board m set m.boardTitle = :title, m.boardContext = :context where m.id = :id")
//                .setParameter("title", title)
//                .setParameter("context", context)
//                .setParameter("id", id)
//                .executeUpdate();
//    }
//
//    /**
//     * 게시글 삭제
//     */
//    public void deleteById(Long boardId) {
//        em.createQuery("delete from Board b where b.id = :boardId")
//                .setParameter("boardId", boardId)
//                .executeUpdate();
//    }
//
//    /**
//     * 게시글 존재 여부
//     */
//    public boolean existsById(Long boardId) {
//        try {
//            em.createQuery("SELECT m FROM Board m WHERE m.id = :boardId", Board.class)
//                    .setParameter("boardId", boardId)
//                    .getSingleResult();
//            return true; // 게시글이 존재하는 경우
//        } catch (NoResultException e) {
//            return false; // 게시글이 존재하지 않는 경우
//        }
//    }
//
//    /**
//     * 게시글 상세 조회
//     */
//    public List<Board> findBoardDetailFromTitle(String boardTitle){
//        return em.createQuery("select m from Board m where m.boardTitle = :boardTitle", Board.class)
//                .setParameter("boardTitle", boardTitle)
//                .getResultList();
//    }
//
//    public List<Board> findBoardDetailFromMember(String memberEmail){
//        return em.createQuery("select m from Board m where m.member.memberEmail = :memberEmail", Board.class)
//                .setParameter("memberEmail", memberEmail)
//                .getResultList();
//    }
//
//    /**
//     * 게시글 리스트 조회
//     */
//    public List<String> findBoardListFromTitle(String boardTitle) {
//        return em.createQuery("select m from Board m where m.boardTitle = :boardTitle", String.class)
//                .getResultList();
//    }
//
//    public List<String> findBoardListFromEmail(String memberEmail) {
//        return em.createQuery("select m from Board m where m.member.memberEmail = :memberEmail", String.class)
//                .setParameter("memberEmail", memberEmail)
//                .getResultList();
//    }
//
//    public Board findOne(Long id) {
//        return em.find(Board.class, id);
//    }
}
