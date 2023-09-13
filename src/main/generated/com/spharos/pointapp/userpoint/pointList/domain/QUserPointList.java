package com.spharos.pointapp.userpoint.pointList.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPointList is a Querydsl query type for UserPointList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPointList extends EntityPathBase<UserPointList> {

    private static final long serialVersionUID = -850822876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPointList userPointList = new QUserPointList("userPointList");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.point.domain.QPoint point;

    public final EnumPath<com.spharos.pointapp.point.domain.PointType> pointType = createEnum("pointType", com.spharos.pointapp.point.domain.PointType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final StringPath uuid = createString("uuid");

    public QUserPointList(String variable) {
        this(UserPointList.class, forVariable(variable), INITS);
    }

    public QUserPointList(Path<? extends UserPointList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPointList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPointList(PathMetadata metadata, PathInits inits) {
        this(UserPointList.class, metadata, inits);
    }

    public QUserPointList(Class<? extends UserPointList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new com.spharos.pointapp.point.domain.QPoint(forProperty("point")) : null;
    }

}

