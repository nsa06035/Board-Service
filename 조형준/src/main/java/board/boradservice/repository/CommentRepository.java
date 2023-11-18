package board.boradservice.repository;


import board.boradservice.domian.Comment;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//@RequiredArgsConstructor
@Transactional
public interface CommentRepository  extends JpaRepository<Comment, Long> { // Member : 엔티티 타입 / Long : 엔티티 식별자



//    private final EntityManager em;
//
//    // 댓글 작성
//    public void saveComment(Comment comment) {
//        em.persist(comment); // JPA에 member 저장
//    }
//
//    // 댓글 수정
//    public void modifyComment(String context, Long boardId, Long commentId) {
//        em.createQuery("update Comment m set m.context = :context where m.board.id = :boardId and m.id = :commentId ")
//                .setParameter("context", context)
//                .setParameter("boardId", boardId)
//                .setParameter("commentId", commentId)
//                .executeUpdate();
//    }
//
//    // 댓글 삭제
//    public void deleteComment(Long commentId) {
//        em.createQuery("delete from Comment c where c.id = :commentId")
//                .setParameter("commentId", commentId)
//                .executeUpdate();
//    }
}
