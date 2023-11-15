package com.example.jpa_bulletin_board.repository;

import com.example.jpa_bulletin_board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleStartingWith(String title);
    List<Post> findAllListByMemberId(Long memberId);
}
