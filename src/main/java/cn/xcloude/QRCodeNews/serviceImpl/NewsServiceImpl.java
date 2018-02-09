package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.mapper.NewsMapper;
import cn.xcloude.QRCodeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:11
 */

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
}
