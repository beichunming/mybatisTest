package cn.sxt.spring.mapper;

import cn.sxt.entity.Group;
import cn.sxt.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by beichunming on 2017/4/22.
 */
public interface UserMapper {
    User getUserByNameAndPwd(User user);
    Group getGroupByGid(User user);
    void insertUser(User user);
    List<User> getUserByPage(Map<String,Object> map);
    int getUserCounts(User user);
}
