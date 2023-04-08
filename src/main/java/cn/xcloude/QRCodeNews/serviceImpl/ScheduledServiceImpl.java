package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.entity.News;
import cn.xcloude.QRCodeNews.entity.NewsCategory;
import cn.xcloude.QRCodeNews.mapper.NewsCategoryMapper;
import cn.xcloude.QRCodeNews.mapper.NewsMapper;
import cn.xcloude.QRCodeNews.service.HttpService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableSet;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author XiongShengjie
 * @date 2018/4/9 下午 7:10
 */
@Component
public class ScheduledServiceImpl {
  private static final Logger logger = LoggerFactory.getLogger(ScheduledServiceImpl.class);
  private static final Set<String> TYPES = ImmutableSet.of("top", "shehui", "guonei", "guoji",
      "yule", "tiyu", "junshi", "keji", "caijing", "shishang");

  @Autowired
  private HttpService httpService;
  @Autowired
  private NewsMapper newsMapper;
  @Autowired
  private NewsCategoryMapper newsCategoryMapper;

  @Scheduled(cron = "0 0 0/1 * * ?")
  public void getNews() {
    for (String type : TYPES) {
      getNews(type);
    }
  }

  private void getNews(String type) {
    String result = null;
    try {
      result = httpService.doGet("http://v.juhe.cn/toutiao/index?type=" + type + "&key=bb35fe066731d8ff6a364446027b8acb");
    } catch (URISyntaxException | IOException e) {
      logger.error("get news from v.juhe.cn failed", e);
    }

    JSONObject object = JSON.parseObject(result);
    JSONObject resu = object.getJSONObject("result");
    JSONArray list = resu.getJSONArray("data");
    for (int i = 0; i < list.size(); i++) {
      JSONObject news = list.getJSONObject(i);
      String newsId = news.getString("uniquekey");
      String newsTitle = news.getString("title");
      String newsUrl = getRealUrl(news.getString("url"));
      String newsCategory = news.getString("category");
      int category = 1;
      List<NewsCategory> categories = newsCategoryMapper.selectByExample(null);
      for (NewsCategory cate : categories) {
        if (cate.getCategoryName().equals(newsCategory)) {
          category = cate.getCategoryId();
          break;
        }
      }
      Date datetime = news.getDate("date");
      String img = "";
      String img1 = news.getString("thumbnail_pic_s");
      String img2 = news.getString("thumbnail_pic_s02");
      String img3 = news.getString("thumbnail_pic_s03");
      if (!TextUtils.isEmpty(img1)) {
        if (!TextUtils.isEmpty(img2)) {
          if (!TextUtils.isEmpty(img3)) {
            img += img1 + "|" + img2 + "|" + img3 + "|";
          } else {
            img += img1 + "|" + img2 + "|";
          }
        } else {
          img = img1 + "|";
        }
      }
      News news1 = new News(newsId, newsTitle, newsUrl, "System", img, category);
      news1.setCreateDatetime(datetime);

      try {
        newsMapper.insertSelective(news1);
      } catch (Exception e) {
        logger.error("insert to db failed", e);
      }
    }
  }

  private String getRealUrl(String newsUrl) {
    List<NameValuePair> pairs = Collections.emptyList();
    try {
      pairs = URLEncodedUtils.parse(new URL(newsUrl).getQuery(), StandardCharsets.UTF_8);
    } catch (MalformedURLException e) {
      logger.warn("parse url failed: {}", newsUrl);
    }

    String url = pairs.stream().filter(pair -> "id".equals(pair.getName())).findAny()
        .map(NameValuePair::getValue).orElse(null);
    if (TextUtils.isEmpty(url)) {
      return newsUrl;
    }

    return url;
  }
}
