package com.spharos.pointapp.userpoint.trans.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointTrans is a Querydsl query type for PointTrans
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointTrans extends EntityPathBase<PointTrans> {

    private static final long serialVersionUID = -93373295L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointTrans pointTrans = new QPointTrans("pointTrans");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    public final com.spharos.pointapp.affiliatecard.domain.QAffiliateCard affiliateCard;

    public final com.spharos.pointapp.brand.domain.QBranch branch;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.point.domain.QPoint point;

    public final NumberPath<Integer> transMount = createNumber("transMount", Integer.class);

    public final NumberPath<Integer> trnasPoint = createNumber("trnasPoint", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final StringPath uuid = createString("uuid");

    public QPointTrans(String variable) {
        this(PointTrans.class, forVariable(variable), INITS);
    }

    public QPointTrans(Path<? extends PointTrans> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointTrans(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointTrans(PathMetadata metadata, PathInits inits) {
        this(PointTrans.class, metadata, inits);
    }

    public QPointTrans(Class<? extends PointTrans> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.affiliateCard = inits.isInitialized("affiliateCard") ? new com.spharos.pointapp.affiliatecard.domain.QAffiliateCard(forProperty("affiliateCard"), inits.get("affiliateCard")) : null;
        this.branch = inits.isInitialized("branch") ? new com.spharos.pointapp.brand.domain.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.point = inits.isInitialized("point") ? new com.spharos.pointapp.point.domain.QPoint(forProperty("point")) : null;
    }

}

