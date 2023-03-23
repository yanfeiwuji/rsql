package io.github.yanfeiwuji.rsql.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;
import lombok.RequiredArgsConstructor;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 16:28
 */
@RequiredArgsConstructor
public class QueryWrapperVisitor<T> implements RSQLVisitor<QueryWrapper<T>, QueryWrapper<T>> {

    private final HandlerComparisonNode<QueryWrapper> handlerComparisonNode;

    @Override
    public QueryWrapper<T> visit(AndNode node, QueryWrapper<T> wrapper) {
        node.forEach(n -> n.accept(this, wrapper));
        return wrapper;
    }

    @Override
    public QueryWrapper<T> visit(OrNode node, QueryWrapper<T> wrapper) {
        wrapper.or(w -> node.forEach(n -> n.accept(this, w)));
        return wrapper;
    }

    @Override
    public QueryWrapper<T> visit(ComparisonNode node, QueryWrapper<T> wrapper) {
        return handlerComparisonNode.handler(node, wrapper);
    }





}
