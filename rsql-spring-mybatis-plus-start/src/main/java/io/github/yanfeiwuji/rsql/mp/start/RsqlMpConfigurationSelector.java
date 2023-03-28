package io.github.yanfeiwuji.rsql.mp.start;


import cz.jirutka.rsql.parser.RSQLParser;
import io.github.yanfeiwuji.rsql.common.YfwjRSQLParser;
import io.github.yanfeiwuji.rsql.mp.MpHandlerComparisonNode;
import io.github.yanfeiwuji.rsql.mp.MpQueryToParam;
import org.springframework.context.annotation.Bean;

/**
 * @author yanfeiwuji
 * @date 2023/3/25 15:19
 */
public class RsqlMpConfigurationSelector {
    @Bean
    RSQLParser rsqlParser() {
        return YfwjRSQLParser.rsqlParser();
    }

    @Bean
    MpHandlerComparisonNode mpHandlerComparisonNode() {
        return new MpHandlerComparisonNode();
    }

    @Bean
    MpQueryToParam mpQueryToParam(RSQLParser parser, MpHandlerComparisonNode mpHandlerComparisonNode) {
        return new MpQueryToParam(parser, mpHandlerComparisonNode);
    }


}
