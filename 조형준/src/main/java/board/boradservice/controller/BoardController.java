package board.boradservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techeerpartners.TecheerPartnersBoardProject.dto.request.BoardSaveRequestDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardPostResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardListResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.dto.response.BoardGetDetailResponseDTO;
import techeerpartners.TecheerPartnersBoardProject.service.BoardService;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    // 생성자 주입
    private final BoardService boardService;

    /**
     * 게시글 생성
     */
    @PostMapping("/")
    public ResponseEntity<String> createPost(HttpServletRequest request, @ModelAttribute BoardSaveRequestDTO boardSaveRequestDTO) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");
        try {
            boardService.createPost(memberEmail, boardSaveRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("게시물 작성 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<String> updatePost(HttpServletRequest request, @PathVariable Long boardId, @ModelAttribute BoardSaveRequestDTO boardSaveRequestDTO) {
        String memberEmail = (String) request.getSession().getAttribute("memberEmail");
        try {
            boardService.updatePost(memberEmail, boardId, boardSaveRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("게시물 수정 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * 게시글 삭제
     * 게시글 삭제를 위해서는 boardId만 있으면 되므로 인자로 boardId만 받는다.
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deletePost(HttpServletRequest request, @PathVariable Long boardId) {

        try {
            boardService.deletePost(request, boardId);
            return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제 성공");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardListResponseDTO>> getAllPosts() {
        // 게시물이 없는 경우 빈 리스트가 나오도록 설정(예외처리 굳이?)
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getList());
    }

    /**
     * 게시글 상세 조회
     * @PathVariable로 교체
     */
    @GetMapping("/boardTitle/{boardTitle}")
    public ResponseEntity<List<BoardGetDetailResponseDTO>> getPostByTitle(@PathVariable String boardTitle) throws NoSuchObjectException {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.getPostByTitle(boardTitle));
        } catch (NoSuchObjectException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //
        }
    }

    @GetMapping("/memberEmail/{memberEmail}")
    public ResponseEntity<List<BoardGetDetailResponseDTO>> getPostByEmail(@PathVariable String memberEmail) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.getPostByEmail(memberEmail));
        } catch (NoSuchObjectException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
