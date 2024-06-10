package practice.newbalance.repository.board.item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepositoryImpl implements CustomCouponRepository{

    private final JPAQueryFactory queryFactory;

    public CouponRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
