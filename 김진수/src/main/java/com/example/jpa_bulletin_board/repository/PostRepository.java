package com.example.jpa_bulletin_board.repository;

import com.example.jpa_bulletin_board.domain.Post;
import com.example.jpa_bulletin_board.dto.response.PostListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleStartingWith(String title);
    List<Post> findAllListByMemberId(Long memberId);
    
    /*
    List<Post> findByMemberIdAndTitle(Long memberId, String title);
    
    findByMemberIdAndTitleContaining를 이용하여 title의 일부분으로 게시글 조회를 가능하게 한다
     */

    // email과 title의 일부분으로 조회 -> title은 일부분으로도 조회 가능, email은 안됨 -> email 조회하는 부분이 null이 아닌경우만 로직을 수행하도록 코드 수정함
    List<Post> findByMemberIdAndTitleContaining(Long memberId, String title);
}
