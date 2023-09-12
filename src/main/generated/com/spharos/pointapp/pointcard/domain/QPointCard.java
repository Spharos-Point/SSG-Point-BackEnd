package com.spharos.pointapp.pointcard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointCard is a Querydsl query type for PointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointCard extends EntityPathBase<PointCard> {

    private static final long serialVersionUID = -463070426L;

    public static final QPointCard pointCard = new QPointCard("pointCard");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    public final NumberPath<Long> branchId = createNumber("branchId", Long.class);

    public final NumberPath<Integer> brandId = createNumber("brandId", Integer.class);

    public final StringPath cardnumber = createString("cardnumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath cvc = createString("cvc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final StringPath uuid = createString("uuid");

    public QPointCard(String variable) {
        super(PointCard.class, forVariable(variable));
    }

    public QPointCard(Path<? extends PointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointCard(PathMetadata metadata) {
        super(PointCard.class, metadata);
    }

}

