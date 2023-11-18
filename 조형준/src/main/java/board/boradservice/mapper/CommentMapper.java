package board.boradservice.mapper;

import board.boradservice.domian.Comment;
import board.boradservice.dto.response.CommentCreateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CommentMapper extends StructMapper<CommentCreateResponseDTO, Comment> {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
}