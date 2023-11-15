package com.example.jpa_bulletin_board.service;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import com.example.jpa_bulletin_board.dto.request.PostSaveRequest;
import com.example.jpa_bulletin_board.dto.response.PostListDetailResponse;
import com.example.jpa_bulletin_board.dto.response.PostListResponse;
import com.example.jpa_bulletin_board.repository.MemberRepository;
import com.example.jpa_bulletin_board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 작성
     */
    public void savePost(PostSaveRequest postSaveRequest, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            Post post = postSaveRequest.toEntity(member);
            //toEntity(Member member)로 바꾸면 어떨까?
            //post.setMember(member);
            postRepository.save(post);
        } else {
            throw new IllegalArgumentException("ID에 해당하는 회원을 찾을 수 없습니다." + memberId);
        }
    }

    /**
     * 게시글 목록 조회
     */
    public List<PostListResponse> findPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostListResponse> responseList = new ArrayList<>();

        /*return postList.stream()
                .map(PostListResponse::from)
                .collect(Collectors.toList());*/

        for (Post post : postList) {
            responseList.add(PostListResponse.from(post));
        }

        return responseList;
    }

    /**
     * 제목으로 게시글 목록 조회
     */
    public List<PostListResponse> findPostListByTitle(String title) {
        List<Post> postList = postRepository.findAllByTitleStartingWith(title);
        List<PostListResponse> responseList = new ArrayList<>();

        /*return postList.stream()
                .map(PostListResponse::from)
                .collect(Collectors.toList());*/

        for (Post post : postList) {
            responseList.add(PostListResponse.from(post));
        }

        return responseList;
    }

    /**
     * 특정 회원이 작성한 게시글 목록 조회
     */
    //1. 회원의 이메일을 param으로 하여 Get 요청
    //2. 회원의 이메일으로 member의 Id 찾기
    //3. memberId로 post 조회
    public List<PostListResponse> findPostListByEmail(String email) {
        Member findMember = memberRepository.findByEmail(email);
        List<PostListResponse> responseList = new ArrayList<>();

        List<Post> postList = postRepository.findAllListByMemberId(findMember.getId());

        /*return postList.stream()
                .map(PostListResponse::from)
                .collect(Collectors.toList());*/

        for (Post post : postList) {
            responseList.add(PostListResponse.from(post));
        }

        return responseList;
    }

    /**
     * 게시글 상세 조회
     */
    public PostListDetailResponse findPostListDetail(Long postId) {
        Optional<Post> postListDetail = postRepository.findById(postId);

        if (postListDetail.isPresent()) {
            return PostListDetailResponse.from(postListDetail.get());
        }
        throw new IllegalArgumentException("ID에 해당하는 포스트를 찾을 수 없습니다: " + postId);
    }

}
