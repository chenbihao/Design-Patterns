package src.facade;

/**
 * @author: chenbihao
 * @create: 2021/11/27
 * @Description:
 */
public class DataPdf {

    private String title;
    private String content;
    private String pwd;

    public DataPdf(DataWord word) {
        this.title = word.getTitle();
        this.content = word.getContent();
    }

    public void encrypt(String pwd) {
        this.pwd = pwd;
        System.out.println("对当前文档进行加密，密码：" + pwd);
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPwd() {
        return pwd;
    }
}
