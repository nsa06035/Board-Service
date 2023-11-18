package board.boradservice.mapper;

import board.boradservice.domian.Board;
import board.boradservice.dto.response.BoardPostResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BoardMapper extends StructMapper<BoardPostResponseDTO, Board> {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
}