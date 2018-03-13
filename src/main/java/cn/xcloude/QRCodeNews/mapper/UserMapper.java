package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.entity.UserExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loginByName(User record);

    User isMobileRight(String userMobile);
}