package com.spharos.pointapp.userpoint.gift.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointGift is a Querydsl query type for PointGift
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointGift extends EntityPathBase<PointGift> {

    private static final long serialVersionUID = 385816617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointGift pointGift = new QPointGift("pointGift");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath giftImage = createString("giftImage");

    public final StringPath giftMessage = createString("giftMessage");

    public final NumberPath<Integer> giftPoint = createNumber("giftPoint", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.point.domain.QPoint point;

    public final EnumPath<PointGiftType> pointGiftType = createEnum("pointGiftType", PointGiftType.class);

    public final StringPath senderUuid = createString("senderUuid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QPointGift(String variable) {
        this(PointGift.class, forVariable(variable), INITS);
    }

    public QPointGift(Path<? extends PointGift> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointGift(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointGift(PathMetadata metadata, PathInits inits) {
        this(PointGift.class, metadata, inits);
    }

    public QPointGift(Class<? extends PointGift> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new com.spharos.pointapp.point.domain.QPoint(forProperty("point")) : null;
    }

}

