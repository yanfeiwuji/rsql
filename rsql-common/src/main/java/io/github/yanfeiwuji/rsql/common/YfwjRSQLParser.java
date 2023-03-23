package io.github.yanfeiwuji.rsql.common;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class YfwjRSQLParser {

    public RSQLParser rsqlParser() {
        Set<ComparisonOperator> defaultOperators = RSQLOperators.defaultOperators();
        defaultOperators.add(ComparisonOperatorProxy.EQ_NU.getOperator());
        defaultOperators.add(ComparisonOperatorProxy.NOT_IN.getOperator());
        defaultOperators.add(ComparisonOperatorProxy.LIKE.getOperator());
        defaultOperators.add(ComparisonOperatorProxy.NOT_LIKE.getOperator());
        return new RSQLParser(defaultOperators);
    }
}
