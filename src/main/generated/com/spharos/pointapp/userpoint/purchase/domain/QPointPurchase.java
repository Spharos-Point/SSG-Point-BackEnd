package com.spharos.pointapp.userpoint.purchase.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointPurchase is a Querydsl query type for PointPurchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointPurchase extends EntityPathBase<PointPurchase> {

    private static final long serialVersionUID = 1256316361L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointPurchase pointPurchase = new QPointPurchase("pointPurchase");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    public final com.spharos.pointapp.brand.domain.QBranch branch;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.point.domain.QPoint point;

    public final NumberPath<Integer> purchaseMount = createNumber("purchaseMount", Integer.class);

    public final NumberPath<Integer> purchasePrice = createNumber("purchasePrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QPointPurchase(String variable) {
        this(PointPurchase.class, forVariable(variable), INITS);
    }

    public QPointPurchase(Path<? extends PointPurchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointPurchase(PathMetadata metadata, PathInits inits) {
        this(PointPurchase.class, metadata, inits);
    }

    public QPointPurchase(Class<? extends PointPurchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new com.spharos.pointapp.brand.domain.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.point = inits.isInitialized("point") ? new com.spharos.pointapp.point.domain.QPoint(forProperty("point")) : null;
    }

}

