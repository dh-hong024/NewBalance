package practice.newbalance.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QModifierEntity is a Querydsl query type for ModifierEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QModifierEntity extends EntityPathBase<ModifierEntity> {

    private static final long serialVersionUID = 367035205L;

    public static final QModifierEntity modifierEntity = new QModifierEntity("modifierEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath modifiedBy = createString("modifiedBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public QModifierEntity(String variable) {
        super(ModifierEntity.class, forVariable(variable));
    }

    public QModifierEntity(Path<? extends ModifierEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModifierEntity(PathMetadata metadata) {
        super(ModifierEntity.class, metadata);
    }

}

