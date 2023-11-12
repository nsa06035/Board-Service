package board.spring.repository;

import board.boardspring.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByTitleStartingWith(String title);

    List<Board> findAllListByMemberId(Long id);


}





//package board.boardspring.repository;
//
//import board.boardspring.domain.Board;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//// 여기에는 엔티티 매니저를 사용해서 객체에 접근, crud설계
//@Repository
//@Transactional
//@RequiredArgsConstructor
//public class BoardRepository {
//
//    @PersistenceContext
//    EntityManager em;
//
//    public void save(Board board) { em.persist(board); }
//
//    // 게시글 조회
//    public Board findById(Long id) {
//        return em.find(Board.class, id);
//    }
//
//    // 전체 게시글 목록 조회
//    public List<Board> findAll() {
//        return em.createQuery("SELECT b FROM Board b", Board.class)
//                .getResultList();
//    }
//
//    // 제목으로 조회
//    public List<Board> findByTitle(String title) {
//        return em.createQuery("SELECT b FROM Board b WHERE b.title = :title", Board.class)
//                .setParameter("title", title)
//                .getResultList();
//    }
//
//    // 특정 회원이 작성한 게시글 조회
//    public List<Board> findByMemberId(Long memberId) {
//        return em.createQuery("SELECT b FROM Board b WHERE b.member.id = :memberId", Board.class)
//                .setParameter("memberId", memberId)
//                .getResultList();
//    }
//
//    // 게시글 수정
//    public void update(Board board) {
//        em.merge(board);
//    }
//
//    // 게시글 삭제
//    public void delete(Board board) {
//        em.remove(board);
//    }
//
//    // 게시글 제목과 회원으로 조회
//    public List<Board> findByTitleAndNickname(String title, String nickname) {
//        return em.createQuery("SELECT b FROM Board b WHERE b.title = :title AND b.member.nickname = :nickname", Board.class)
//                .setParameter("title", title)
//                .setParameter("nickname", nickname)
//                .getResultList();
//    }
//
//}
