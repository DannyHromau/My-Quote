package com.dannyhromau.quote.mapper;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    @Mapping(target = "id", ignore = true)
    Vote mapToVote(VoteDto voteDto);

    VoteDto mapToVoteDto(Vote vote);


    void updateVoteFromDto(VoteDto voteDto, @MappingTarget Vote vote);

    List<VoteDto> mapToListVoteDto(List<Vote> votes);
}
