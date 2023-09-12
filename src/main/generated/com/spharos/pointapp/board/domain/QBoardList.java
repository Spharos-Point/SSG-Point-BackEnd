package com.spharos.pointapp.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardList is a Querydsl query type for BoardList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardList extends EntityPathBase<BoardList> {

    private static final long serialVersionUID = -505241980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardList boardList = new QBoardList("boardList");

    public final com.spharos.pointapp.admin.administrator.domain.QAdministrator administrator;

    public final QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBoardList(String variable) {
        this(BoardList.class, forVariable(variable), INITS);
    }

    public QBoardList(Path<? extends BoardList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardList(PathMetadata metadata, PathInits inits) {
        this(BoardList.class, metadata, inits);
    }

    public QBoardList(Class<? extends BoardList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.administrator = inits.isInitialized("administrator") ? new com.spharos.pointapp.admin.administrator.domain.QAdministrator(forProperty("administrator")) : null;
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
    }

}

