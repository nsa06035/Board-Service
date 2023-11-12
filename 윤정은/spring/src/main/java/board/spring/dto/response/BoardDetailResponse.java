package board.spring.dto.response;

import board.spring.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailResponse {

    private String title;
    private String content;
    private String email;
    private List<Comment> comments;


}
