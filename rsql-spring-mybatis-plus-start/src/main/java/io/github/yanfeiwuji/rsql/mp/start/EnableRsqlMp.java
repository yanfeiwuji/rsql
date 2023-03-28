package io.github.yanfeiwuji.rsql.mp.start;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

/**
 * @author yanfeiwuji
 * @date 2023/3/25 15:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RsqlMpConfigurationSelector.class})
@EnableScheduling
public @interface EnableRsqlMp {

}
