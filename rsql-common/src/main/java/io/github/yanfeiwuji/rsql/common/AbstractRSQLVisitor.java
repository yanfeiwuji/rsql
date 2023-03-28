package io.github.yanfeiwuji.rsql.common;

import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.RequiredArgsConstructor;


/**
 * @author yanfeiwuji
 * @date 2023/3/23 19:35
 */
@RequiredArgsConstructor
public abstract class AbstractRSQLVisitor<T, E> implements RSQLVisitor<T, T> {
    protected final HandlerComparisonNode<T> handlerComparisonNode;
    protected final Class<E> entityClass;

}
