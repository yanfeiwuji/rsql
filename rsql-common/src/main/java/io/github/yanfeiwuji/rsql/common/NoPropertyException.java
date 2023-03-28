package io.github.yanfeiwuji.rsql.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yanfeiwuji
 * @date 2023/3/25 13:19
 */
@AllArgsConstructor
public class NoPropertyException extends RuntimeException {
    String resolvedPath;

    public NoPropertyException(String resolvedPath, String message) {
        super(message);
        this.resolvedPath = resolvedPath;
    }

}
