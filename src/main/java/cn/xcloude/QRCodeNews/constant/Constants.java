package cn.xcloude.QRCodeNews.constant;

/**
 * @author XiongShengjie
 * @date 2018/2/22 下午 3:13
 */
public final class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    //public final static String baseUrl = "https://www.xcloude.cn/QRCodeNews/";
    public final static String baseUrl = "http://192.168.0.109:8080/QRCodeNews";

    //腾讯云SMS应用
    public final static int AppID = 1400071526;
    public final static String AppKey = "504a8fec500b7d91490ef624c2fe98b2";
    public final static int type = 0;    //短信类型 0：普通 1：营销
    public final static String nationCode = "86";
    public final static long expireTime = 120L;

}
