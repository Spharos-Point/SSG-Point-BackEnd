package com.spharos.pointapp.faq.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFaqList is a Querydsl query type for FaqList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFaqList extends EntityPathBase<FaqList> {

    private static final long serialVersionUID = -1246365436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFaqList faqList = new QFaqList("faqList");

    public final QFaq faq;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final com.spharos.pointapp.user.domain.QUser user;

    public QFaqList(String variable) {
        this(FaqList.class, forVariable(variable), INITS);
    }

    public QFaqList(Path<? extends FaqList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFaqList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFaqList(PathMetadata metadata, PathInits inits) {
        this(FaqList.class, metadata, inits);
    }

    public QFaqList(Class<? extends FaqList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.faq = inits.isInitialized("faq") ? new QFaq(forProperty("faq")) : null;
        this.user = inits.isInitialized("user") ? new com.spharos.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

