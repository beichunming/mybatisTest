package cn.sxt.service;

import cn.sxt.entity.Group;
import cn.sxt.entity.User;
import cn.sxt.exception.service.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.Map;

/**
 * Created by beichunming on 2017/4/22.
 */
public interface UserService {
    //登录   ,破除自定义异常，业务异常
    User getUserByNameAndPwd(String loginName, String password) throws SerialException, ServiceException;

    public List<Group> getAllGroupInfo() throws ServiceException;

    public  void  addUser(User user) throws ServiceException;

    Map<String,Object> getUserListByPage(Integer index,Integer pageSize,User user) throws ServiceException;

    public void parseFileToUser(MultipartFile uploadUsersFile)
            throws ServiceException;

    public void downloadUsers(int page, int rows, User loginUser,
                              HttpServletResponse response) throws ServiceException;
}
