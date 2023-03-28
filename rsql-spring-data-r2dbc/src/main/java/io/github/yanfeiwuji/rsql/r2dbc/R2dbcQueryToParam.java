package io.github.yanfeiwuji.rsql.r2dbc;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.github.yanfeiwuji.rsql.common.QueryToParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.query.Criteria;

/**
 * @author yanfeiwuji
 * @date 2023/3/27 16:01
 */
@RequiredArgsConstructor
public class R2dbcQueryToParam implements QueryToParam<Criteria> {
    private final RSQLParser parser;
    private final R2dbcHandlerComparisonNode handlerComparisonNode;

    @Override
    public <T> Criteria covert(String query, Class<T> entityClass) {
        if (query == null) {
            return Criteria.empty();
        }
        final Node node = parser.parse(query);
        return node.accept(new R2dbcRSQLVisitor<>(handlerComparisonNode, entityClass), Criteria.empty());
    }
}
