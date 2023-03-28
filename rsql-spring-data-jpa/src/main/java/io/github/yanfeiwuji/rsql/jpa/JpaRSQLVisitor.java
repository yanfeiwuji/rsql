package io.github.yanfeiwuji.rsql.jpa;

import cz.jirutka.rsql.parser.ast.*;
import io.github.yanfeiwuji.rsql.common.AbstractRSQLVisitor;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;
import io.github.yanfeiwuji.rsql.common.YfwjRSQLParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 20:39
 */

public class JpaRSQLVisitor<T> extends AbstractRSQLVisitor<Specification<T>, T> {


    public JpaRSQLVisitor(HandlerComparisonNode<Specification<T>> handlerComparisonNode, Class<T> entityClass) {
        super(handlerComparisonNode, entityClass);
    }

    @Override
    public Specification visit(AndNode node, Specification<T> param) {
        node.getChildren().stream().map(n -> n.accept(this, param))
                .forEach(param::and);
        return param;
    }

    @Override
    public Specification visit(OrNode node, Specification<T> param) {
        node.getChildren().stream().map(n -> n.accept(this, param))
                .forEach(param::or);
        return param;
    }

    @Override
    public Specification visit(ComparisonNode node, Specification<T> param) {
        return handlerComparisonNode.handler(node, param, entityClass);
    }

}
