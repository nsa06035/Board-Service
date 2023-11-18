package board.boradservice.service;

import board.boradservice.domian.Board;
import board.boradservice.domian.Comment;
import board.boradservice.domian.Member;
import board.boradservice.dto.request.CommentCreateRequestDTO;
import board.boradservice.dto.response.CommentCreateResponseDTO;
import board.boradservice.repository.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    /**
     * 댓글 작성
     */
    public void createComment(CommentCreateRequestDTO commentCreateRequestDTO) {

        Member member = validationMember(commentCreateRequestDTO.getMemberId());
        Board board = validationBoard(commentCreateRequestDTO.getBoardId());

        CommentCreateResponseDTO commentCreateResponseDTO = new CommentCreateResponseDTO(commentCreateRequestDTO.getCommentContext(), board, member);

        // commentContext, board, member가 들어 있는 Comment 객체를 생성(id는 들어있지 않음 -> 자동 생성)
        Comment comment = commentCreateResponseDTO.toCommentForSave(commentCreateResponseDTO);

        commentRepository.save(comment);

        throw new IllegalArgumentException("로그인 해주세요.");
    }

    /**
     * 댓글 수정
     */
    public void updateComment(Long commentId, CommentCreateRequestDTO commentCreateRequestDTO) {

        Member member = validationMember(commentCreateRequestDTO.getMemberId());
        Board board = validationBoard(commentCreateRequestDTO.getBoardId());
        Comment comment = validationComment(commentId);

        CommentCreateResponseDTO commentCreateResponseDTO = new CommentCreateResponseDTO(commentId, commentCreateRequestDTO.getCommentContext(), board, member);

        comment.update(commentCreateResponseDTO);
        commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long boardId, Long commentId, Long memberId) {

        memberService
                .findMemberById(memberId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않은 멤버입니다."));
        boardService
                .findPostById(boardId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));
        commentRepository
                .findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 댓글입니다."));

        commentRepository.deleteById(commentId);
    }

    //------------------------------------------------------------------------------------------------------------------

    private Member validationMember(Long memberId) {
        return memberService
                .findMemberById(memberId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않은 멤버입니다."));
    }

    private Board validationBoard(Long boardId) {
        return boardService
                .findPostById(boardId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));
    }

    private Comment validationComment(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 댓글입니다."));
    }
}
