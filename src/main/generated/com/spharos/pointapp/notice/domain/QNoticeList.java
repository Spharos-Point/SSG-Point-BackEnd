package com.spharos.pointapp.notice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeList is a Querydsl query type for NoticeList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeList extends EntityPathBase<NoticeList> {

    private static final long serialVersionUID = -768421460L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeList noticeList = new QNoticeList("noticeList");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final QNotice notice;

    public final com.spharos.pointapp.user.domain.QUser user;

    public QNoticeList(String variable) {
        this(NoticeList.class, forVariable(variable), INITS);
    }

    public QNoticeList(Path<? extends NoticeList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeList(PathMetadata metadata, PathInits inits) {
        this(NoticeList.class, metadata, inits);
    }

    public QNoticeList(Class<? extends NoticeList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.notice = inits.isInitialized("notice") ? new QNotice(forProperty("notice")) : null;
        this.user = inits.isInitialized("user") ? new com.spharos.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

