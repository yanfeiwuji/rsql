package io.github.yanfeiwuji.rsql.r2dbc;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import io.github.yanfeiwuji.rsql.common.AbstractRSQLVisitor;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;
import io.github.yanfeiwuji.rsql.common.YfwjRSQLParser;
import org.springframework.data.relational.core.query.Criteria;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 20:06
 */
public class R2dbcRSQLVisitor<E> extends AbstractRSQLVisitor<Criteria, E> {

    public R2dbcRSQLVisitor(HandlerComparisonNode<Criteria> handlerComparisonNode, Class<E> entityClass) {
        super(handlerComparisonNode, entityClass);
    }


    @Override
    public Criteria visit(AndNode node, Criteria param) {
        return node.getChildren().stream()
                .map(n -> n.accept(this, Criteria.empty()))
                .reduce(param, Criteria::and);
    }

    @Override
    public Criteria visit(OrNode node, Criteria param) {
        return node.getChildren().stream()
                .map(n -> n.accept(this, Criteria.empty()))
                .reduce(param, Criteria::or);
    }

    @Override
    public Criteria visit(ComparisonNode node, Criteria param) {
        return handlerComparisonNode.handler(node, param, entityClass);
    }
}
