package com.example.jpa_bulletin_board.dto.request;

import com.example.jpa_bulletin_board.domain.Member;
import com.example.jpa_bulletin_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
public class PostSaveRequest {
    private String title;
    private String content;
    private Long memberId;

    // toEntitiy가 아니라 Mapper를 사용해보기
    public Post toEntity(Member member) {
        return new Post(title, content, member);
    }

    public PostSaveRequest() {
        // 기본 생성자
    }

    public PostSaveRequest(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
