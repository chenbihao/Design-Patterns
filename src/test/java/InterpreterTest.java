import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.interpreter.ExpressionPic;
import src.interpreter.ExpressionStr;
import src.interpreter.Interpreter;
import src.memento.SnapshotHolder;
import src.memento.StringUtil;

import java.util.HashMap;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class InterpreterTest {

    @Test
    public void test() {

        String content = "这是一篇文章，文章标题为{{title}}，内容主要是讲{{subject}}，" +
                "目前评分{{score}}分，附带题图{{@picture}}，内容图{{@picture2}}";

        Interpreter interpreter = new Interpreter(content);

        // 套用文字模板进行渲染
        ExpressionStr expressionStr = new ExpressionStr();
        interpreter.addExpression(expressionStr, new HashMap() {{
            put("title", "解释器模式");
            put("subject", "模仿poi-tl的渲染方式展示解释器模式");
            put("score", "3.7");
        }});

        // 套用图片模板进行渲染
        ExpressionPic expressionPic = new ExpressionPic();
        interpreter.addExpression(expressionPic, new HashMap() {{
            put("picture", "/picture/1.jpg");
            put("picture2", "/picture/2.jpg");
        }});

        // 进行解释
        String result = interpreter.interpreter();
        Assertions.assertEquals("这是一篇文章，文章标题为解释器模式，内容主要是讲模仿poi-tl的渲染方式展示解释器模式，" +
                        "目前评分3.7分，附带题图![picture](assets//picture/1.jpg]，内容图![picture2](assets//picture/2.jpg]"
                , result);
    }

}
