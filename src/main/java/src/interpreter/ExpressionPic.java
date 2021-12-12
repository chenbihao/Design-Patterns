package src.interpreter;

import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/12
 * @Description: 表达式实现：图片表达式
 */
public class ExpressionPic implements Expression {

    private static String prefix = "{{@";
    private static String suffix = "}}";

    @Override
    public String interpret(String sourceStr, Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String targerStr = prefix + entry.getKey() + suffix;
            String imageStr = "![" +entry.getKey()+"](assets/"+ entry.getValue() + "]";
            sourceStr = StrUtil.replace(sourceStr, targerStr, imageStr);
        }
        return sourceStr;
    }
}
