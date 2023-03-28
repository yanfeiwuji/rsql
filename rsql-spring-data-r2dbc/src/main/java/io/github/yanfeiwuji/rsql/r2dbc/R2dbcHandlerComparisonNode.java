package io.github.yanfeiwuji.rsql.r2dbc;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.yanfeiwuji.rsql.common.ComparisonOperatorProxy;
import io.github.yanfeiwuji.rsql.common.HandlerComparisonNode;
import io.github.yanfeiwuji.rsql.common.HandlerField;
import io.github.yanfeiwuji.rsql.common.NoPropertyException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mapping.PersistentPropertyPath;
import org.springframework.data.mapping.context.InvalidPersistentPropertyPath;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.data.relational.core.query.Criteria;

import java.util.Map;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 20:25
 */
@RequiredArgsConstructor
public class R2dbcHandlerComparisonNode extends HandlerComparisonNode<Criteria> {

    private final R2dbcEntityTemplate template;

    @Override
    protected void initHandlerFieldsMap(Map<ComparisonOperatorProxy, HandlerField<Criteria>> map) {
        map.put(ComparisonOperatorProxy.EQUAL,
                (node, param) ->
                        param.and(node.getSelector()).is(node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.NOT_EQUAL,
                (node, param) ->
                        param.and(node.getSelector()).not(node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.GREATER_THAN,
                (node, param) ->
                        param.and(node.getSelector()).greaterThan(node.getArguments().get(0)));
        map.put(ComparisonOperatorProxy.GREATER_THAN_OR_EQUAL,
                (node, param) ->
                        param.and(node.getSelector()).greaterThanOrEquals(node.getArguments().get(0)));
        map.put(ComparisonOperatorProxy.LESS_THAN,
                (node, param) ->
                        param.and(node.getSelector()).lessThan(node.getArguments().get(0)));
        map.put(ComparisonOperatorProxy.LESS_THAN_OR_EQUAL,
                (node, param) ->
                        param.and(node.getSelector()).lessThanOrEquals(node.getArguments().get(0)));
        map.put(ComparisonOperatorProxy.IN,
                (node, param) ->
                        param.and(node.getSelector()).in(node.getArguments()));
        map.put(ComparisonOperatorProxy.NOT_IN,
                (node, param) ->
                        param.and(node.getSelector()).notIn(node.getArguments()));
        map.put(ComparisonOperatorProxy.EQ_NU,
                (node, param) ->
                        param.and(node.getSelector()).isNull());
        map.put(ComparisonOperatorProxy.NOT_NU,
                (node, param) ->
                        param.and(node.getSelector()).isNotNull());

        map.put(ComparisonOperatorProxy.LIKE,
                (node, param) ->
                        param.and(node.getSelector()).like(node.getArguments().get(0)));
        map.put(ComparisonOperatorProxy.NOT_LIKE,
                (node, param) ->
                        param.and(node.getSelector()).notLike(node.getArguments().get(0)));
    }

    @Override
    public boolean nodeCanQuery(ComparisonNode node, Criteria param, Class eClass) {
        try {
            final PersistentPropertyPath<? extends RelationalPersistentProperty> persistentPropertyPath = template.getConverter().getMappingContext().getPersistentPropertyPath(node.getSelector(), eClass);
        } catch (InvalidPersistentPropertyPath e) {
            return false;
        }
        return true;
    }
}
