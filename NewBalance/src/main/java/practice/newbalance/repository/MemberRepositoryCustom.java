package practice.newbalance.repository;

import practice.newbalance.dto.member.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDto> findMemberAll(int offset, int limit);

}
