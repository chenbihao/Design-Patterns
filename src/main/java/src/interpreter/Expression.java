package src.interpreter;

import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/12
 * @Description: 表达式抽象类
 */
public interface Expression {

    String interpret(String sourceStr, Map<String, String> data);

}
