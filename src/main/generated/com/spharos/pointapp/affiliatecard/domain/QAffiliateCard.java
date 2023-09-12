package com.spharos.pointapp.affiliatecard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAffiliateCard is a Querydsl query type for AffiliateCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAffiliateCard extends EntityPathBase<AffiliateCard> {

    private static final long serialVersionUID = -1164480698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAffiliateCard affiliateCard = new QAffiliateCard("affiliateCard");

    public final StringPath affiliateNum = createString("affiliateNum");

    public final com.spharos.pointapp.extra.domain.QExtra extra;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath uuid = createString("uuid");

    public QAffiliateCard(String variable) {
        this(AffiliateCard.class, forVariable(variable), INITS);
    }

    public QAffiliateCard(Path<? extends AffiliateCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAffiliateCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAffiliateCard(PathMetadata metadata, PathInits inits) {
        this(AffiliateCard.class, metadata, inits);
    }

    public QAffiliateCard(Class<? extends AffiliateCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.extra = inits.isInitialized("extra") ? new com.spharos.pointapp.extra.domain.QExtra(forProperty("extra")) : null;
    }

}

