package board.spring.controller;

import board.spring.domain.Board;
import board.spring.domain.Member;
import board.spring.dto.request.BoardSaveRequest;
import board.spring.dto.response.BoardDetailResponse;
import board.spring.dto.response.BoardListResponse;
import board.spring.service.BoardService;
import board.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    // 게시글 저장 : 게시글은 제목과 내용을 포함한다.
    // POST /api/boards
    @PostMapping
    public ResponseEntity<Void> saveBoard(@RequestParam Long memberId, @RequestBody BoardSaveRequest request) {
        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();
            request.setMemberId(memberId);
            request.setMember(loggedInMember);
            boardService.savePost(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 게시글 목록 조회 : 전체 게시글 목록 조회
    // GET /api/boards
    @GetMapping
    public List<BoardListResponse> findBoardList() {
        List<BoardListResponse> responseList = boardService.findBoardList();
        return responseList;
    }

    // 게시글 제목으로 게시글 목록을 검색
    // GET /api/boards?title=A
    @GetMapping ("/search")
    public List<BoardListResponse> findBoardListByTitle(@RequestParam String title) {
        List<BoardListResponse> responseList = boardService.findBoardListByTitle(title);
        return responseList;
    }

    // 게시글 이메일로 검색
    // GET /api/boards?email=jungeun@naver.com
    @GetMapping("/search")
    public List<BoardListResponse> findBoardListByEmail(@RequestParam String email) {
        List<BoardListResponse> responseList = boardService.findPostListByEmail(email);
        return responseList;
    }


    // 게시글 상세 조회 (제목, 내용, 회원이름, 댓글)을 포함
    // GET /api/details?boardId=1
    @GetMapping("/search/details")
    public ResponseEntity<BoardDetailResponse> findBoardDetailList(@RequestParam Long boardId) {
        String userEmail = "example@example.com";

        BoardDetailResponse response = boardService.findDetailList(boardId, userEmail);
        return ResponseEntity.ok(response);
    }


    // 게시글 수정
    // PUT /api/boards
    @PutMapping
    public ResponseEntity<Void> updateBoard(@RequestParam Long boardId,
                                            @RequestParam Long memberId,
                                            @RequestBody BoardSaveRequest request) {
        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();

            Optional<Board> optionalBoard = boardService.findBoardById(boardId);

            if (optionalBoard.isPresent()) {
                Board existingBoard = optionalBoard.get();

                if (existingBoard.getMember().equals(loggedInMember)) {
                    request.setMemberId(memberId);
                    request.setMember(loggedInMember);
                    boardService.updatePost(boardId, request);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }


    // 게시글 삭제
    // DELETE /api/boards
    @DeleteMapping
    public ResponseEntity<Void> deleteBoard(@RequestParam Long boardId,
                                            @RequestParam Long memberId) {
        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();

            Optional<Board> optionalBoard = boardService.findBoardById(boardId);

            if (optionalBoard.isPresent()) {
                Board existingBoard = optionalBoard.get();

                if (existingBoard.getMember().equals(loggedInMember)) {
                    boardService.deletePost(boardId);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}