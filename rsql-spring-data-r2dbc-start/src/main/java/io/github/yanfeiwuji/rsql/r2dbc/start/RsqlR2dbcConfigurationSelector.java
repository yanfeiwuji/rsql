package io.github.yanfeiwuji.rsql.r2dbc.start;

import cz.jirutka.rsql.parser.RSQLParser;
import io.github.yanfeiwuji.rsql.common.NoPropertyException;
import io.github.yanfeiwuji.rsql.common.YfwjRSQLParser;
import io.github.yanfeiwuji.rsql.r2dbc.R2dbcHandlerComparisonNode;
import io.github.yanfeiwuji.rsql.r2dbc.R2dbcQueryToParam;
import io.github.yanfeiwuji.rsql.r2dbc.R2dbcRSQLVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yanfeiwuji
 * @date 2023/3/24 13:41
 */

@RestControllerAdvice
public class RsqlR2dbcConfigurationSelector {

    @Bean
    RSQLParser rsqlParser() {
        return YfwjRSQLParser.rsqlParser();
    }

    @Bean
    R2dbcHandlerComparisonNode r2dbcRSQLVisitor(R2dbcEntityTemplate template) {
        return new R2dbcHandlerComparisonNode(template);
    }

    @Bean
    R2dbcQueryToParam r2dbcQueryToParam(RSQLParser parser, R2dbcHandlerComparisonNode r2dbcHandlerComparisonNode) {
        return new R2dbcQueryToParam(parser, r2dbcHandlerComparisonNode);
    }


}
