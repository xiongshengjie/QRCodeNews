package cn.xcloude.QRCodeNews.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/2/26 上午 11:08
 */
public class Api {
  private Api() {
    throw new IllegalStateException("Utility class");
  }

  public static final String STATUS = "status";
  public static final String MESSAGE = "message";
  public static final String RESULT = "result";

  public static final int ERROR = 4000;
  public static final int SUCCESS = 2000;
  public static final int SERVER_ERROR = 5000;
  public static final int USER_ERROR = 4001;
  public static final int MOBILE_ERROR = 4002;
  public static final int NO_NEWS_ERROR = 4003;
  public static final int NO_COMMENT_ERROR = 4004;
  public static final int REQUEST_PARAM_ERROR = 4005;

  public static final String SERVER_ERROR_MESSAGE = "服务器内部错误";

  public static Map<String, Object> generateResult(int status, String message) {
    Map<String, Object> result = new HashMap<>(2);
    result.put(Api.STATUS, status);
    result.put(Api.MESSAGE, message);
    return result;
  }
}
