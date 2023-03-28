package io.github.yanfeiwuji.rsql.r2dbc.start;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

/**
 * @author yanfeiwuji
 * @date 2023/3/24 13:40
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RsqlR2dbcConfigurationSelector.class})
@EnableScheduling
public @interface EnableRsqlR2dbc {
}
