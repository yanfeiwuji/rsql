package io.github.yanfeiwuji.rsql.jpa;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.yanfeiwuji.rsql.common.ComparisonOperatorProxy;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;
import io.github.yanfeiwuji.rsql.common.HandlerField;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Attribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 20:55
 */
public class JpaHandlerComparisonNode extends HandlerComparisonNode<Specification> {

    private final EntityManager entityManager;

    public JpaHandlerComparisonNode(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected void initHandlerFieldsMap(Map<ComparisonOperatorProxy, HandlerField<Specification>> map) {

        map.put(ComparisonOperatorProxy.EQUAL,
                (node, param) ->
                        (root, query, builder) -> builder.equal(root.get(node.getSelector()), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.NOT_EQUAL,
                (node, param) ->
                        (root, query, builder) -> builder.notEqual(root.get(node.getSelector()), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.GREATER_THAN,
                (node, param) ->
                        (root, query, builder) -> builder.gt(root.get(node.getSelector()), Double.valueOf(node.getArguments().get(0)))
        );
        map.put(ComparisonOperatorProxy.GREATER_THAN_OR_EQUAL,
                (node, param) ->
                        (root, query, builder) -> builder.ge(root.get(node.getSelector()), Double.valueOf(node.getArguments().get(0)))
        );
        map.put(ComparisonOperatorProxy.LESS_THAN,
                (node, param) ->
                        (root, query, builder) -> builder.lt(root.get(node.getSelector()), Double.valueOf(node.getArguments().get(0)))
        );
        map.put(ComparisonOperatorProxy.LESS_THAN_OR_EQUAL,
                (node, param) ->
                        (root, query, builder) -> builder.le(root.get(node.getSelector()), Double.valueOf(node.getArguments().get(0)))
        );
        map.put(ComparisonOperatorProxy.IN,
                (node, param) ->
                        (root, query, builder) -> builder.in(root.get(node.getSelector())).in(node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.NOT_IN,
                (node, param) ->
                        (root, query, builder) -> builder.not(root.get(node.getSelector())).in(node.getArguments())
        );
        map.put(ComparisonOperatorProxy.EQ_NU,
                (node, param) ->
                        (root, query, builder) -> builder.in(root.get(node.getSelector()).isNull())
        );
        map.put(ComparisonOperatorProxy.NOT_NU,
                (node, param) ->
                        (root, query, builder) -> builder.isNotNull(root.get(node.getSelector()))
        );
        map.put(ComparisonOperatorProxy.LIKE,
                (node, param) ->
                        (root, query, builder) -> builder.like(root.get(node.getSelector()), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.NOT_LIKE,
                (node, param) ->
                        (root, query, builder) -> builder.notLike(root.get(node.getSelector()), node.getArguments().get(0))
        );
    }

    @Override
    public boolean nodeCanQuery(ComparisonNode node, Specification param, Class<?> entityClass) {
        return entityManager.getEntityManagerFactory()
                .getMetamodel().entity(entityClass)
                .getAttributes().stream()
                .map(Attribute::getName)
                .anyMatch(n -> n.equals(node.getSelector()));

    }
}
