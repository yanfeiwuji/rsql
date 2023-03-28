package io.github.yanfeiwuji.rsql.common;

/**
 * @author yanfeiwuji
 * @date 2023/3/27 12:17
 */
@FunctionalInterface
public interface QueryToParam<P> {
    <T> P covert(String query, Class<T> entityClass);
}
