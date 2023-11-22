package com.example.jpa_bulletin_board.controller;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.dto.request.PostSaveRequest;
import com.example.jpa_bulletin_board.dto.response.PostListDetailResponse;
import com.example.jpa_bulletin_board.dto.response.PostListResponse;
import com.example.jpa_bulletin_board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // 생성자 주입 사용
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시글 작성
     */
    @PostMapping
    public ResponseEntity<String> savePost(
            @RequestBody PostSaveRequest postsaveRequest) {
        if (postService.savePost(postsaveRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        //memberId에 해당하는 사용자가 존재하지 않는 경우 클라이언트가 요청한 리소스를 찾을 수 없음을 나타내기 위함
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("존재하지 않는 회원입니다.");
    }

    /**
     * 게시글 조회
     */
    @GetMapping
    public List<PostListResponse> findPostList() {
        List<PostListResponse> responseList = postService.findPostList();
        return responseList;
    }

//    /**
//     * 제목으로 게시글 검색
//     */
//    // RequestParam(required = true)가 디폴트임
//    @GetMapping("/search?title=title")
//    ///api/posts/search?title=title
//    public List<PostListResponse> findPostListByTitle(@RequestParam("title") String title) {
//        List<PostListResponse> responseList = postService.findPostListByTitle(title);
//        return responseList;
//    }
//
//    /**
//     * 해당 email의 회원이 작성한 게시글 검색
//     */
//    @GetMapping("/searchEmail")
//    public List<PostListResponse> findPostListByEmail(@RequestParam("email") String email) {
//        List<PostListResponse> responseList = postService.findPostListByEmail(email);
//        return responseList;
//    }

    /**
     * 해당 email 또는 title에 해당하는 게시글 검색
     */
    @GetMapping("/search")
    public List<PostListResponse> findPostListByEmailOrTitle(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "title", required = false) String title) {

        if (email != null && title != null) {
            // email과 title을 모두 이용한 검색 로직
            return postService.findPostListByEmailAndTitle(email, title);
        } else if (email != null) {
            // email을 이용한 검색 로직
            return postService.findPostListByEmail(email);
        } else if (title != null) {
            // title을 이용한 검색 로직
            return postService.findPostListByTitle(title);
        }
        // email과 title이 모두 제공되지 않으면 빈 리스트 반환
        return Collections.emptyList();
    }


    //posts/1 -> o
    /**
     * 게시글 상세조회
     */
    @GetMapping("/{postId}")
    public PostListDetailResponse findPostListDetail(@PathVariable("postId") Long postId) {
        return (PostListDetailResponse) postService.findPostListDetail(postId);
    }
}
