package cn.xcloude.QRCodeNews.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author XiongShengjie
 * @date 2018/2/22 下午 3:54
 */
public class HtmlUtil {

    private static Element head;
    private static Element body;
    private static Document doc;
    private static String patternHtml = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no\">\n" +
            "\n" +
            "    <link href=\"../../../bootstrap/bootstrap.css\" rel=\"stylesheet\">\n" +
            "    <script src=\"../../../jquery/jquery.js\"></script>\n" +
            "    <script src=\"../../../bootstrap/bootstrap.js\"></script>\n" +
            "\n" +
            "    <link href=\"../../../dist/summernote.css\" rel=\"stylesheet\">\n" +
            "    <script src=\"../../../dist/summernote.js\"></script>\n" +
            "    <title></title>" +
            "</head>\n" +
            "</html>";

    static {
        doc = Jsoup.parse(patternHtml);
        head = doc.head();
        body = doc.body();
    }

    public static String buildHtml(String html,String title){
        head.getElementsByTag("title").get(0).text(title);
        body.html("<div style=\"margin : 10px;\">" + html + "</div>");
        return doc.toString();
    }

    public static String randomHtmlFileName(){
        return UUID.randomUUID()+".html";
    }
}
