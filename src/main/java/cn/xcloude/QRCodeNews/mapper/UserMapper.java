package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.entity.UserExample;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}