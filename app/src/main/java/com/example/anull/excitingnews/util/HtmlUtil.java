package com.example.anull.excitingnews.util;

import java.util.List;

/**
 * Created by 山东娃 on 2016/6/7.
 * 负责格式化String和List为Html文件
 */
public class HtmlUtil {
    //css样式,隐藏header
    private static final String HIDE_HEADER_STYLE = "<style>div.headline{display:none;}</style>";

    //css style tag,需要格式化
    private static final String NEEDED_FORMAT_CSS_TAG = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";

    // js script tag,需要格式化
    private static final String NEEDED_FORMAT_JS_TAG = "<script src=\"%s\"></script>";

    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODING = "utf-8";

    private HtmlUtil() {

    }

    /**
     * 根据css链接生成Link标签
     *
     * @param url String
     * @return String
     */
    public static String createCssTag(String url) {
        return String.format(NEEDED_FORMAT_CSS_TAG, url);
    }

    /**
     * 根据多个css链接生成Link标签
     *
     * @param urls List<String>
     * @return String
     */
    public static String createCssTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createCssTag(url));
        }
        return sb.toString();
    }

    /**
     * 根据js链接生成Script标签
     *
     * @param url String
     * @return String
     */
    public static String createJsTag(String url) {
        return String.format(NEEDED_FORMAT_JS_TAG, url);
    }

    /**
     * 根据多个js链接生成Script标签
     *
     * @param urls List<String>
     * @return String
     */
    public static String createJsTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createJsTag(url));
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 根据样式标签,html字符串,js标签
     * 生成完整的HTML文档
     *
     * @param html string
     * @param css  string
     * @param js   string
     * @return string
     */
     public static String createHtmlData(String html, String css, String js) {
        return css.concat(HIDE_HEADER_STYLE).concat(html).concat(js);
    }


    public static StringBuffer handleHtml(String body, boolean isNight) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/detail.css\" ></head>");
        stringBuffer.append(isNight ? "<body class=\"night\">" : "<body>");
        stringBuffer.append(body);
        stringBuffer.append("</body></html>");
        return stringBuffer;
    }

    public static String buildHtmlForIt(String content) {
        StringBuilder modifiedHtml = new StringBuilder();
        modifiedHtml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "<head>" + "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\"/>"
                + "<meta http-equiv=\"Cache-control\" content=\"public\" />" + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\" />"
                + "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">"
                + "<script src=\"file:///android_asset/jquery.min.js\"></script>" + "<script src=\"file:///android_asset/info.js\"></script>");
        modifiedHtml.append("<body ");
        modifiedHtml.append(">");
        modifiedHtml.append(content);
        modifiedHtml.append("</body></html>");
        return modifiedHtml.toString();
    }
}
