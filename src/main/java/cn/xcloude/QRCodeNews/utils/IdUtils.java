package cn.xcloude.QRCodeNews.utils;

import java.util.UUID;

/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:16
 */
public final class IdUtils {
  private IdUtils() {}

  public static String getUUID() {
    return UUID.randomUUID().toString();
  }

  public static boolean isValidId(Integer id) {
    return id != null && id > 0;
  }

  public static boolean isNullOrEmpty(String id) {
    return id == null || id.isEmpty();
  }
}
