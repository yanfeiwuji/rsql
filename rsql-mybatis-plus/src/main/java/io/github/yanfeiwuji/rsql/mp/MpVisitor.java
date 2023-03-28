package io.github.yanfeiwuji.rsql.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import io.github.yanfeiwuji.rsql.common.AbstractRSQLVisitor;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;

import java.util.stream.Collectors;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 16:28
 */


public class MpVisitor<T> extends AbstractRSQLVisitor<QueryWrapper, T> {


    public MpVisitor(HandlerComparisonNode<QueryWrapper> handlerComparisonNode, Class<T> entityClass) {
        super(handlerComparisonNode, entityClass);
    }

    @Override
    public QueryWrapper visit(AndNode node, QueryWrapper wrapper) {
        node.forEach(n -> n.accept(this, wrapper));
        return wrapper;
    }

    @Override
    public QueryWrapper visit(OrNode node, QueryWrapper wrapper) {
        node.forEach(n -> wrapper.or(nw -> n.accept(this, (QueryWrapper) nw)));
        return wrapper;
    }

    @Override
    public QueryWrapper<T> visit(ComparisonNode node, QueryWrapper wrapper) {
        return handlerComparisonNode.handler(node, wrapper, entityClass);
    }


}
