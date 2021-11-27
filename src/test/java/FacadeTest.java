import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.adapter.SocketAdapter;
import src.adapter.SocketClassAdapterImpl;
import src.adapter.SocketObjectAdapterImpl;
import src.facade.Data;
import src.facade.DataPdf;
import src.facade.Facade;

import java.util.Arrays;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class FacadeTest {

    @Test
    public void test() {

        Data data = new Data();
        data.setTitle("标题");
        data.setContent("内容");

        DataPdf pdfData = Facade.getPdfData(data);

        Assertions.assertEquals("标题",pdfData.getTitle());
        Assertions.assertEquals("内容",pdfData.getContent());
        Assertions.assertEquals("asd123!@#",pdfData.getPwd());
    }

}
