package io.github.yanfeiwuji.rsql.common;

import cz.jirutka.rsql.parser.ast.ComparisonNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfeiwuji
 * @date 2023/3/23 16:36
 */
public abstract class HandlerComparisonNode<P> {

    private Map<ComparisonOperatorProxy, HandlerField<P>> CACHE = Collections.synchronizedMap(new HashMap<>());

    public HandlerComparisonNode() {
        initHandlerFieldsMap(CACHE);
    }

    public P handler(ComparisonNode node, P param) {
        if (nodeCanQuery(node, param)) {
            return CACHE
                    .get(ComparisonOperatorProxy.asEnum(node.getOperator()))
                    .handler(node, param);
        } else {
            return param;
        }

    }

    public boolean nodeCanQuery(ComparisonNode node, P param) {
        return true;
    }

    protected abstract void initHandlerFieldsMap(Map<ComparisonOperatorProxy, HandlerField<P>> map);

}
