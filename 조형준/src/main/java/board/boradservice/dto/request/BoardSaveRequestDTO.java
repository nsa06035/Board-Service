package board.boradservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequestDTO {
    private String boardTitle;
    private String boardContext;
    private Long memberId;
}
