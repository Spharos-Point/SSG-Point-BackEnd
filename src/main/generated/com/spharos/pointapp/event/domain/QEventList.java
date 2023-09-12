package com.spharos.pointapp.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventList is a Querydsl query type for EventList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventList extends EntityPathBase<EventList> {

    private static final long serialVersionUID = -533003516L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventList eventList = new QEventList("eventList");

    public final QEvent event;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final BooleanPath prize = createBoolean("prize");

    public final com.spharos.pointapp.user.domain.QUser user;

    public QEventList(String variable) {
        this(EventList.class, forVariable(variable), INITS);
    }

    public QEventList(Path<? extends EventList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventList(PathMetadata metadata, PathInits inits) {
        this(EventList.class, metadata, inits);
    }

    public QEventList(Class<? extends EventList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
        this.user = inits.isInitialized("user") ? new com.spharos.pointapp.user.domain.QUser(forProperty("user")) : null;
    }

}

