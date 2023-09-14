package com.spharos.pointapp.notice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1314280430L;

    public static final QNotice notice = new QNotice("notice");

    public final com.spharos.pointapp.config.common.QBaseTimeEntity _super = new com.spharos.pointapp.config.common.QBaseTimeEntity(this);

    public final StringPath context = createString("context");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

