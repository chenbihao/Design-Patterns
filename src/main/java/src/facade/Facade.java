package src.facade;

/**
 * @author: chenbihao
 * @create: 2021/11/27
 * @Description:
 */
public class Facade {

    /**
     * 传入数据，生成 PDF 文件
     */
    public static DataPdf getPdfData(Data data) {

        // 数据通过模板引擎生成 word
        // 即数据通过模板引擎，套用模板文件（模板.docx）生成出一个新的word文件
        DataWord dataWord = new DataWord(data, "./xxx/xxxx/模板.docx");

        // word 转换成 PDF
        DataPdf dataPdf = new DataPdf(dataWord);
        // PDF 进行加密处理
        dataPdf.encrypt("asd123!@#");

        return dataPdf;
    }

}
