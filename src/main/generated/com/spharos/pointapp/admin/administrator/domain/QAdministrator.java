package com.spharos.pointapp.admin.administrator.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdministrator is a Querydsl query type for Administrator
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdministrator extends EntityPathBase<Administrator> {

    private static final long serialVersionUID = 287418021L;

    public static final QAdministrator administrator = new QAdministrator("administrator");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final EnumPath<Roll> roll = createEnum("roll", Roll.class);

    public final StringPath userName = createString("userName");

    public QAdministrator(String variable) {
        super(Administrator.class, forVariable(variable));
    }

    public QAdministrator(Path<? extends Administrator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdministrator(PathMetadata metadata) {
        super(Administrator.class, metadata);
    }

}

