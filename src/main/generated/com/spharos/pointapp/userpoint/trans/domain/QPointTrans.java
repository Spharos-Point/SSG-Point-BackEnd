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

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.spharos.pointapp.extra.domain.QExtra extra;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.point.domain.QPoint point;

    public final NumberPath<Integer> transPoint = createNumber("transPoint", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

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
        this.extra = inits.isInitialized("extra") ? new com.spharos.pointapp.extra.domain.QExtra(forProperty("extra")) : null;
        this.point = inits.isInitialized("point") ? new com.spharos.pointapp.point.domain.QPoint(forProperty("point")) : null;
    }

}

