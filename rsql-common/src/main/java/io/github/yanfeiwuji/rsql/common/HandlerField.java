package io.github.yanfeiwuji.rsql.common;

import cz.jirutka.rsql.parser.ast.ComparisonNode;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 16:48
 */
@FunctionalInterface
public interface HandlerField<P> {
    P handler(ComparisonNode node, P param);
}
