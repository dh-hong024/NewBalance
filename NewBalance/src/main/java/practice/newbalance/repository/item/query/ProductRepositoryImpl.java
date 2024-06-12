package practice.newbalance.repository.item.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements CustomProductRepository{

    private final JPAQueryFactory queryFactory;
}
