package practice.newbalance.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.newbalance.dto.board.NoticeDto;

import java.util.List;

import static practice.newbalance.domain.board.QNotice.notice;


@Repository
public class NoticeRepositoryImpl implements CustomNoticeRepository {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public NoticeRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<NoticeDto> findNoticeAll(int offset, int limit) {
        return queryFactory.select(Projections.constructor(
                NoticeDto.class,
                notice.id,
                notice.noticeTitle,
                notice.noticeContent,
                notice.noticeCount,
                notice.modifiedDate
                        ))
                .from(notice)
                .orderBy(notice.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
