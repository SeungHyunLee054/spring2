package kr.mjc.lsh2.web.dao;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Article {
    int articleId;
    String title;
    String content;
    int userId;
    String name;
    String cdate;
    String udate;

    public String getContentHtml(){
        return content.replaceAll("\n", "<br>");
    }
}
