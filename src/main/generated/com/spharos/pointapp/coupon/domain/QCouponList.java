package com.spharos.pointapp.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouponList is a Querydsl query type for CouponList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponList extends EntityPathBase<CouponList> {

    private static final long serialVersionUID = 1667728840L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCouponList couponList = new QCouponList("couponList");

    public final QCoupon coupon;

    public final BooleanPath couponStat = createBoolean("couponStat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.spharos.pointapp.user.domain.QUser user;

    public QCouponList(String variable) {
        this(CouponList.class, forVariable(variable), INITS);
    }

    public QCouponList(Path<? extends CouponList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCouponList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCouponList(PathMetadata metadata, PathInits inits) {
        this(CouponList.class, metadata, inits);
    }

    public QCouponList(Class<? extends CouponList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new QCoupon(forProperty("coupon"), inits.get("coupon")) : null;
        this.user = inits.isInitialized("user") ? new com.spharos.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

