package practice.newbalance.domain.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaqs is a Querydsl query type for Faqs
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFaqs extends EntityPathBase<Faqs> {

    private static final long serialVersionUID = -714009264L;

    public static final QFaqs faqs = new QFaqs("faqs");

    public final practice.newbalance.domain.QModifierEntity _super = new practice.newbalance.domain.QModifierEntity(this);

    public final StringPath answer = createString("answer");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath question = createString("question");

    public final EnumPath<FaqTag> tag = createEnum("tag", FaqTag.class);

    public QFaqs(String variable) {
        super(Faqs.class, forVariable(variable));
    }

    public QFaqs(Path<? extends Faqs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaqs(PathMetadata metadata) {
        super(Faqs.class, metadata);
    }

}

