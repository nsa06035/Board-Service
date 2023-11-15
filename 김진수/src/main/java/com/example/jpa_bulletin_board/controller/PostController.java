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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 작성
     */
    @PostMapping
    public ResponseEntity<Void> savePost(
            @RequestBody PostSaveRequest postsaveRequest,
            @RequestParam("memberId") Long memberId) {
        postService.savePost(postsaveRequest, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 게시글 조회
     */
    @GetMapping
    public List<PostListResponse> findPostList() {
        List<PostListResponse> responseList = postService.findPostList();
        return responseList;
    }

    /**
     * 제목으로 게시글 검색
     */
    // RequestParam(required = true)가 디폴트임
    @GetMapping(value = "/searchTitle")
    public List<PostListResponse> findPostListByTitle(@RequestParam("title") String title) {
        List<PostListResponse> responseList = postService.findPostListByTitle(title);
        return responseList;
    }

    /**
     * 해당 email의 회원이 작성한 게시글 검색
     */
    @GetMapping(value = "/searchEmail")
    public List<PostListResponse> findPostListByEmail(@RequestParam("email") String email) {
        List<PostListResponse> responseList = postService.findPostListByEmail(email);
        return responseList;
    }

    /**
     * 게시글 상세조회
     */
    @GetMapping("/{postId}")
    //posts/1 -> o
    //posts/search/1 지금은 아님
    public PostListDetailResponse findPostListDetail(@PathVariable("postId") Long postId) {
        return (PostListDetailResponse) postService.findPostListDetail(postId);
    }
}
