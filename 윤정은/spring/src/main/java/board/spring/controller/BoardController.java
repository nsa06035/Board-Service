package board.spring.controller;


import board.boardspring.domain.Board;
import board.boardspring.domain.Member;
import board.boardspring.dto.request.BoardSaveRequest;
import board.boardspring.dto.response.BoardDetailResponse;
import board.boardspring.dto.response.BoardListResponse;
import board.boardspring.service.BoardService;
import board.boardspring.service.MemberService;
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
    @PostMapping
    public ResponseEntity<Void> saveBoard(@RequestParam Long memberId, @RequestBody BoardSaveRequest request) {
        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();
            request.setMemberId(memberId);
            request.setMember(loggedInMember);
            boardService.savePost(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 게시글 목록 조회 : 전체 게시글 목록 조회
    @GetMapping
    public List<BoardListResponse> findBoardList() {
        List<BoardListResponse> responseList = boardService.findBoardList();
        return responseList;
    }

    // 게시글 제목으로 게시글 목록을 검색
    @GetMapping("searchByTitle") // Updated mapping value
    public List<BoardListResponse> findBoardListByTitle(@RequestParam String title) {
        List<BoardListResponse> responseList = boardService.findBoardListByTitle(title);
        return responseList;
    }

    // 게시글 이메일로 검색
    @GetMapping("/search/emails/{email}")
    public List<BoardListResponse> findBoardListByEmail(@PathVariable String email) {
        List<BoardListResponse> responseList = boardService.findPostListByEmail(email);
        return responseList;
    }


    // 게시글 상세 조회 (제목, 내용, 회원이름, 댓글)을 포함
    @GetMapping("/search/details/{boardId}")
    public ResponseEntity<BoardDetailResponse> findBoardDetailList(@PathVariable Long boardId) {
        // Assuming you have the email information, you need to pass it here.
        String userEmail = "example@example.com"; // Replace with the actual email information.

        BoardDetailResponse response = boardService.findDetailList(boardId, userEmail);
        return ResponseEntity.ok(response);
    }


    // 게시글 수정
    @PutMapping("update")
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
                    // Update the board post
                    request.setMemberId(memberId);
                    request.setMember(loggedInMember);
                    boardService.updatePost(boardId, request);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // User is not the owner of the board
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Board with the given ID not found
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Member not authorized
        }
    }


    // 게시글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBoard(@RequestParam Long boardId,
                                            @RequestParam Long memberId) {
        Optional<Member> optionalMember = memberService.findMemberById(memberId);

        if (optionalMember.isPresent()) {
            Member loggedInMember = optionalMember.get();

            Optional<Board> optionalBoard = boardService.findBoardById(boardId);

            if (optionalBoard.isPresent()) {
                Board existingBoard = optionalBoard.get();

                if (existingBoard.getMember().equals(loggedInMember)) {
                    // Delete the board post
                    boardService.deletePost(boardId);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // User is not the owner of the board
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Board with the given ID not found
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Member not authorized
        }
    }


}