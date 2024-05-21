package practice.newbalance.repository.board.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import practice.newbalance.domain.board.FaqTag;
import practice.newbalance.domain.board.Faqs;
import practice.newbalance.domain.board.QFaqs;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.dto.board.QFaqDto;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class FaqRepositoryImpl implements CustomRepository {

    private final JPAQueryFactory query;

    public Page<FaqDto> findAll(Pageable pageable) {
        QFaqs faq = QFaqs.faqs;
        List<FaqDto> contents = query
                .select(
                        Projections.constructor(FaqDto.class,
                                faq.id,
                                faq.question,
                                faq.answer,
                                faq.tag
                        ))
                .from(faq)
                .orderBy(faq.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(faq.count())
                .from(faq);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

    public Page<FaqDto> findAll(Pageable pageable, String condition, String tag) {
        QFaqs faq = QFaqs.faqs;
        List<FaqDto> contents = query
                .select(
                        Projections.constructor(FaqDto.class,
                                faq.id,
                                faq.question,
                                faq.answer,
                                faq.tag
                        ))
                .from(faq)
                .where(null, tagEq(tag))
                .where(null, searchEq(condition))
                .orderBy(faq.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(faq.count())
                .from(faq)
                .where(null, tagEq(tag))
                .where(null, searchEq(condition));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

    @Override
    public Long getSearchCount(String condition, String tag) {
        QFaqs faq = QFaqs.faqs;
        return query
                .select(faq.count())
                .from(faq)
                .where(null, tagEq(tag))
                .where(null, searchEq(condition))
                .fetchOne();

    }


    private BooleanExpression tagEq(String tag){
        QFaqs faq = QFaqs.faqs;
        return (tag == null || tag.equals("ALL")) ?
                null : faq.tag.eq(FaqTag.valueOf(tag));
    }

    private BooleanExpression searchEq(String condition){
        QFaqs faq = QFaqs.faqs;
        return (condition == null) ?
                null : faq.question.like("%" + condition + "%");
    }

}
