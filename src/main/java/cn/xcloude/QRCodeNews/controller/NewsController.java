package cn.xcloude.QRCodeNews.controller;


import cn.xcloude.QRCodeNews.service.NewsService;
import cn.xcloude.QRCodeNews.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:13
 */

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/publish")
    @ResponseBody
    public Map<String, Object> publishNews(@RequestParam("file") MultipartFile[] files,
                                           @RequestParam("title") String title,
                                           @RequestParam("author") String author,
                                           @RequestParam("category") String category,
                                           @RequestParam("html") String html,
                                           HttpServletRequest request) {

        String allImgUrl = "";
        Map<String, Object> result = new HashMap<String, Object>();

        if (files.length > 0) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                // 得到随机名称
                String randomName = FileUploadUtils.generateRandonFileName(fileName);
                // 根据日期得到目录
                String randomDir = FileUploadUtils.generateRandomDir();
                // 图片存储父目录
                String imgurl_parent = "img" + randomDir;
                File parentDir = new File(request.getServletContext().getRealPath(imgurl_parent));
                // 验证目录是否存在，如果不存在，创建出来
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                String imgurl = imgurl_parent + "/" + randomName;
                allImgUrl += imgurl + "|";
                File diskFile = new File(parentDir + "/" + randomName);
                try {
                    file.transferTo(diskFile);
                } catch (IOException e) {
                    result.put("states", 5000);
                    result.put("message", "服务器错误");
                    return result;
                }
            }
        }
        result.put("states", 2000);
        result.put("message", "发布成功");
        return result;
    }
}
