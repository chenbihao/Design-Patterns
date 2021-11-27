package src.facade;

/**
 * @author: chenbihao
 * @create: 2021/11/27
 * @Description:
 */
public class DataWord {

    private String title;
    private String content;
    private String template;

    public DataWord(Data data,String template) {
        this.title = data.getTitle();
        this.content = data.getContent();
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
