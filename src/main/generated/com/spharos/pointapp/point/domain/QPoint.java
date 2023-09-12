package com.spharos.pointapp.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = -895749882L;

    public static final QPoint point1 = new QPoint("point1");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<PointType> pointType = createEnum("pointType", PointType.class);

    public final NumberPath<Integer> totalPoint = createNumber("totalPoint", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final BooleanPath used = createBoolean("used");

    public QPoint(String variable) {
        super(Point.class, forVariable(variable));
    }

    public QPoint(Path<? extends Point> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoint(PathMetadata metadata) {
        super(Point.class, metadata);
    }

}

