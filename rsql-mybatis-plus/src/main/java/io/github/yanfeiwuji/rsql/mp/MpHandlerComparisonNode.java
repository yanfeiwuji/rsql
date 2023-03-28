package io.github.yanfeiwuji.rsql.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.yanfeiwuji.rsql.common.*;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 16:37
 */

public class MpHandlerComparisonNode extends HandlerComparisonNode<QueryWrapper> {

    @Override
    protected void initHandlerFieldsMap(Map<ComparisonOperatorProxy, HandlerField<QueryWrapper>> map) {
        map.put(ComparisonOperatorProxy.EQUAL,
                (node, param) ->
                        (QueryWrapper) param.eq(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.NOT_EQUAL,
                (node, param) ->
                        (QueryWrapper) param.ne(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.GREATER_THAN,
                (node, param) ->
                        (QueryWrapper) param.gt(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.GREATER_THAN_OR_EQUAL,
                (node, param) ->
                        (QueryWrapper) param.ge(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.LESS_THAN,
                (node, param) ->
                        (QueryWrapper) param.lt(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.LESS_THAN_OR_EQUAL,
                (node, param) ->
                        (QueryWrapper) param.le(canDoSingle(node), node.getSelector(), node.getArguments().get(0))
        );
        map.put(ComparisonOperatorProxy.IN,
                (node, param) ->
                        (QueryWrapper) param.in(canDoMul(node), node.getSelector(), node.getArguments())
        );
        map.put(ComparisonOperatorProxy.NOT_IN,
                (node, param) ->
                        (QueryWrapper) param.notIn(canDoMul(node), node.getSelector(), node.getArguments())
        );
        map.put(ComparisonOperatorProxy.EQ_NU,
                (node, param) ->
                        (QueryWrapper) param.isNull(canDoSingle(node), node.getSelector())
        );
        map.put(ComparisonOperatorProxy.NOT_NU,
                (node, param) ->
                        (QueryWrapper) param.isNotNull(canDoSingle(node), node.getSelector())
        );

        map.put(ComparisonOperatorProxy.LIKE,
                (node, param) ->
                        (QueryWrapper) param.like(canDoSingle(node), node.getSelector(), node.getArguments().get(0).replace("*", "%"))
        );

        map.put(ComparisonOperatorProxy.NOT_LIKE,
                (node, param) ->
                        (QueryWrapper) param.notLike(canDoSingle(node), node.getSelector(), node.getArguments().get(0).replace("*", "%"))
        );


    }

    private boolean canDoSingle(ComparisonNode node) {
        return node != null && node.getSelector() != null && node.getArguments() != null && node.getArguments().size() == 1 && node.getArguments().get(0) != null;
    }

    private boolean canDoMul(ComparisonNode node) {
        return node != null && node.getSelector() != null && node.getArguments() != null;
    }

    @Override
    public boolean nodeCanQuery(ComparisonNode node, QueryWrapper param, Class<?> eClass) {
        final String keyColumn = TableInfoHelper.getTableInfo(eClass).getKeyColumn();
        if (node.getSelector().equals(keyColumn)) {
            return true;
        }
        return TableInfoHelper.getTableInfo(eClass)
                .getFieldList()
                .stream()
                .map(TableFieldInfo::getField)
                .map(Field::getName)
                .anyMatch(s -> s.equals(node.getSelector()));
    }
}
