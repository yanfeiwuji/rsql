/*
 * The MIT License
 *
 * Copyright 2015 Antonio Rabelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.yanfeiwuji.rsql.common;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ComparisonOperatorProxy Enum
 *
 * Convert RSQLOperators to an Enumeration type.
 *
 * @author AntonioRabelo
 * @since 2015-02-10
 */
public enum ComparisonOperatorProxy {
	EQUAL(RSQLOperators.EQUAL),
	NOT_EQUAL(RSQLOperators.NOT_EQUAL),
	GREATER_THAN(RSQLOperators.GREATER_THAN),
	GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
	LESS_THAN(RSQLOperators.LESS_THAN),
	LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
	IN(RSQLOperators.IN),
	NOT_IN(RSQLOperators.NOT_IN),
	EQ_NU(new ComparisonOperator("=nu=")),
	NOT_NU(new ComparisonOperator("=nnu=")),
	LIKE(new ComparisonOperator("=l=")),
	NOT_LIKE(new ComparisonOperator("=nl="));

	private ComparisonOperator operator;

	private static final Map<ComparisonOperator, ComparisonOperatorProxy> CACHE = Collections
			.synchronizedMap(new HashMap<ComparisonOperator, ComparisonOperatorProxy>());

	static {
		for (ComparisonOperatorProxy proxy : values()) {
			CACHE.put(proxy.getOperator(), proxy);
		}
	}

	private ComparisonOperatorProxy(ComparisonOperator operator) {
		this.operator = operator;
	}

	public ComparisonOperator getOperator() {
		return this.operator;
	}

	public static ComparisonOperatorProxy asEnum(ComparisonOperator operator) {
		return CACHE.get(operator);
	}
}
