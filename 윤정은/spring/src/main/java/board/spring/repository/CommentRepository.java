package board.spring.repository;

import board.spring.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByMemberIdAndBoardId(Long memberId, Long boardId);
}
