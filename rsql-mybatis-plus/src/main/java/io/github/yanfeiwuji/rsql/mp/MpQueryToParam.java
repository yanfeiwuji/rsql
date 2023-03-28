package io.github.yanfeiwuji.rsql.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.github.yanfeiwuji.rsql.common.QueryToParam;
import lombok.RequiredArgsConstructor;

/**
 * @author yanfeiwuji
 * @date 2023/3/27 12:23
 */
@RequiredArgsConstructor
public class MpQueryToParam implements QueryToParam<QueryWrapper> {

    private final RSQLParser parser;
    private final MpHandlerComparisonNode handlerComparisonNode;

    @Override
    public <T> QueryWrapper<T> covert(String query, Class<T> entityClass) {
        if (query == null) {
            return new QueryWrapper<>();
        }
        final Node node = parser.parse(query);
        return node.accept(new MpVisitor<>(handlerComparisonNode, entityClass), new QueryWrapper<>());
    }
}
