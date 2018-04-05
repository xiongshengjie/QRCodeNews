package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.constant.Constants;
import cn.xcloude.QRCodeNews.entity.News;
import cn.xcloude.QRCodeNews.mapper.NewsMapper;
import cn.xcloude.QRCodeNews.service.NewsService;
import cn.xcloude.QRCodeNews.utils.FileUploadUtils;
import cn.xcloude.QRCodeNews.utils.HtmlUtil;
import cn.xcloude.QRCodeNews.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.xcloude.QRCodeNews.constant.Api.SERVER_ERROR_MESSAGE;


/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:11
 */

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    private Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsMapper newsMapper;

    public Map<String, Object> publishNews(MultipartFile[] files, String title, String author, String category, String html, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        StringBuilder allImgUrlBuilder = new StringBuilder();
        // 根据日期得到目录
        String randomDir = FileUploadUtils.generateRandomDir();

        if (files != null && files.length > 0) {

            Document doc = Jsoup.parseBodyFragment(html);
            Element element = doc.body();

            Elements elements = element.getElementsByTag("img");

            // 图片存储父目录
            String imgurlParent = "img" + randomDir;
            File parentDir = new File(request.getServletContext().getRealPath(imgurlParent));
            // 验证目录是否存在，如果不存在，创建出来
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                // 得到随机名称
                String randomName = FileUploadUtils.generateRandonFileName(fileName);
                String imgurl = imgurlParent + "/" + randomName;

                for (Element imgNode : elements) {
                    String src = imgNode.attr("src");
                    if (fileName.equals(src.substring(src.lastIndexOf('/') + 1, src.length()))) {
                        imgNode.attr("src", Constants.baseUrl + "/" + imgurl);
                        elements.remove(element);
                    }
                }
                allImgUrlBuilder.append(imgurl + "|");
                File diskFile = new File(parentDir + "/" + randomName);
                try {
                    file.transferTo(diskFile);
                } catch (IOException e) {
                    log.error("图片上传IO异常:" + e);
                    result.put(Api.STATUS, Api.SERVER_ERROR);
                    result.put(Api.MESSAGE, SERVER_ERROR_MESSAGE);
                    return result;
                }
            }

            html = doc.body().toString();
        }

        String htmlParent = "html" + randomDir;
        String randomHtml = HtmlUtil.randomHtmlFileName();
        File parentDir = new File(request.getServletContext().getRealPath(htmlParent));
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        File diskFile = new File(parentDir + "/" + randomHtml);

        try (
                FileOutputStream fos = new FileOutputStream(diskFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        ) {

            osw.write(HtmlUtil.buildHtml(html, title));
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常：" + e);
        } catch (FileNotFoundException e) {
            log.error("文件写入NotFound异常：" + e);
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE, SERVER_ERROR_MESSAGE);
            return result;
        } catch (IOException e) {
            log.error("文件写入IO异常：" + e);
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE, SERVER_ERROR_MESSAGE);
            return result;
        }

        String allImgUrl = allImgUrlBuilder.toString();
        String id = IdUtils.getUUID();
        News recordNew = new News(id, title, htmlParent + "/" + randomHtml, author, allImgUrl, Integer.parseInt(category));
        newsMapper.insertSelective(recordNew);

        result.put(Api.STATUS, Api.SUCCESS);
        result.put(Api.MESSAGE, "发布成功");
        result.put("result", recordNew);

        return result;
    }

    @Override
    public Map<String, Object> listNews(int category, int pageNum, int pageCount) {
        PageHelper.startPage(pageNum, pageCount);
        List<News> news = newsMapper.selectByCategoryPage(category);
        Map<String, Object> result = new HashMap<>();
        result.put(Api.STATUS, Api.SUCCESS);
        result.put(Api.MESSAGE, "获取成功");
        result.put("result", news);
        return result;
    }

    @Override
    public Map<String, Object> getNewsById(String id) {
        Map<String,Object> result = new HashMap<>();
        News news = newsMapper.selectByPrimaryKey(id);
        if(news == null){
            result.put(Api.STATUS,Api.NO_NEWS_ERROR);
            result.put(Api.MESSAGE,"查无此文");
            return result;
        }
        result.put(Api.STATUS,Api.SUCCESS);
        result.put(Api.MESSAGE,"获取成功");
        result.put("result",news);
        return result;
    }

    @Override
    public Map<String, Object> listNewsByUser(String userId, int pageNum, int pageCount) {
        PageHelper.startPage(pageNum, pageCount);
        List<News> list = newsMapper.selectByUserPage(userId);
        Map<String,Object> result = new HashMap<>();
        result.put(Api.STATUS, Api.SUCCESS);
        result.put(Api.MESSAGE, "获取成功");
        result.put("result", list);
        return result;
    }

    @Override
    public Map<String, Object> delNews(String id) {
        int flag = newsMapper.deleteByPrimaryKey(id);
        Map<String,Object> result = new HashMap<>();
        if(flag > 0){
            result.put(Api.STATUS, Api.SUCCESS);
            result.put(Api.MESSAGE, "删除成功");
            return result;
        }else {
            result.put(Api.STATUS, Api.ERROR);
            result.put(Api.MESSAGE, "删除失败");
            return result;
        }
    }
}
