package board.boradservice.mapper;

import board.boradservice.domian.Member;
import board.boradservice.dto.request.MemberSignupRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MemberMapper extends StructMapper<MemberSignupRequestDTO, Member> {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

//    extends를 안할 경우 필요
//    MemberSignupRequestDTO toMemberSignupRequestDTO(Member member);
//    Member toMember(MemberSignupRequestDTO memberSignupRequestDTO);
//
//    List<MemberSignupRequestDTO> toMemberSignupRequestDTOList(List<Member> memberList);
//    List<Member> toMemberList(List<MemberSignupRequestDTO> memberSignupRequestDTOList);
}
