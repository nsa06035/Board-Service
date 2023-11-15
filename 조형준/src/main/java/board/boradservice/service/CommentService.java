package board.boradservice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techeerpartners.TecheerPartnersBoardProject.domian.Board;
import techeerpartners.TecheerPartnersBoardProject.domian.Comment;
import techeerpartners.TecheerPartnersBoardProject.domian.Member;
import techeerpartners.TecheerPartnersBoardProject.dto.request.CommentCreateRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.CommentCreateResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    /**
     * 댓글 작성
     */
    public void createComment(HttpServletRequest request, CommentCreateRequestDTO commentCreateRequestDTO) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");

        // 로그인 상태라면
        if (memberEmail != null) {
            // 세션에 저장된 memberId를 이용하여 회원(Member) 객체를 찾음
            Member member = memberService.findMemberByEmail(memberEmail);

            Board board = boardService.findPostById(commentCreateRequestDTO.getBoardId()).get();

            // CommentSaveDTOd 회원 객체를 설정에 회원 객체를 설정
            CommentCreateResponseDTO commentCreateResponseDTO = new CommentCreateResponseDTO();
            commentCreateResponseDTO.setMember(member);
            commentCreateResponseDTO.setBoard(board);
            commentCreateResponseDTO.setCommentContext(commentCreateRequestDTO.getCommentContext());

            // commentContext, board, member가 들어 있는 Comment 객체를 생성(id는 들어있지 않음 -> 자동 생성)
            Comment comment = commentCreateResponseDTO.toCommentForSave(commentCreateResponseDTO);

            commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("로그인 해주세요.");
        }
    }

    /**
     * 댓글 수정
     */
    public void updateComment(HttpServletRequest request, Long commentId, CommentCreateRequestDTO commentCreateRequestDTO) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");

        // 로그인 상태라면
        if (memberEmail != null) {
            if (commentRepository.existsById(commentId)) {
                // 세션에 저장된 memberId를 이용하여 회원(Member) 객체를 찾음
                Member member = memberService.findMemberByEmail(memberEmail);

                Board board = boardService.findPostById(commentCreateRequestDTO.getBoardId()).get();

                // CommentSaveDTOd 회원 객체를 설정에 회원 객체를 설정
                CommentCreateResponseDTO commentCreateResponseDTO = new CommentCreateResponseDTO();
                commentCreateResponseDTO.setMember(member);
                commentCreateResponseDTO.setBoard(board);
                commentCreateResponseDTO.setCommentContext(commentCreateRequestDTO.getCommentContext());

                Comment comment = commentCreateResponseDTO.toComment(commentId, commentCreateResponseDTO);
                commentRepository.save(comment);
            } else {
                throw new IllegalArgumentException("존재하지 않는 댓글입니다..");
            }
        } else {
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(HttpServletRequest request, Long boardId, Long commentId) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");

        // 로그인 검증
        if (memberEmail != null) {
            // 게시판이 존재 검증
            if (boardService.findPostById(boardId).isPresent()) {
                // 댓글이 존재 검증
                if (commentRepository.existsById(commentId)) {
                    commentRepository.deleteById(commentId);
                } else {
                    throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
                }
            } else {
                throw new IllegalArgumentException("게시판이 존재하지 않습니다.");
            }
        } else {
            // 로그인 상태가 아닌 경우
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
    }
}
