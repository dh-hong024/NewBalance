package practice.newbalance.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.newbalance.dto.member.MemberDto;

import java.util.List;

import static practice.newbalance.domain.member.QMember.member;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public List<MemberDto> findMemberAll(int offset, int limit) {
        return queryFactory.select(Projections.constructor(
                MemberDto.class,
                member.userId,
                member.name,
                member.sex,
                member.email,
                member.role
        )).from(member)
                .orderBy(member.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
