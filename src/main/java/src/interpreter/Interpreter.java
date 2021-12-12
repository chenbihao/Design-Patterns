package src.interpreter;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/12
 * @Description:  解释器上下文
 */
public class Interpreter {

    // 源文本
    private String sourceStr;

    // 表达式
    private Map<Expression, Map<String, String>> expressions = new HashMap<>();


    public Interpreter(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    /**
     * 添加表达式
     */
    public void addExpression(Expression expression, Map<String, String> data) {
        expressions.put(expression, data);
    }

    /**
     * 执行解释
     */
    public String interpreter() {

        expressions.forEach((expression, data) -> {
            sourceStr = expression.interpret(sourceStr,data);
        });

        return sourceStr;
    }
}
