package src.interpreter;

import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/12
 * @Description:  表达式实现：文本表达式
 */
public class ExpressionStr implements Expression{

    private static String prefix = "{{";
    private static String suffix = "}}";

    @Override
    public String interpret(String sourceStr, Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String targerStr = prefix + entry.getKey() + suffix;
            sourceStr = StrUtil.replace(sourceStr, targerStr, entry.getValue());
        }
        return sourceStr;
    }
}
