package cn.sxt.service.impl;

import cn.sxt.entity.Group;
import cn.sxt.entity.User;
import cn.sxt.exception.service.ServiceException;
import cn.sxt.service.UserService;
import cn.sxt.spring.mapper.GroupMapper;
import cn.sxt.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beichunming on 2017/4/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupMapper groupMapper;

    public User getUserByNameAndPwd(String loginName, String password) throws ServiceException{
        if (loginName==null||password==null){
            return null;
        }
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPswd(password);
        User resultUser = this.userMapper.getUserByNameAndPwd(user);

        return resultUser;
    }


    public List<Group> getAllGroupInfo() throws ServiceException{
        return this.groupMapper.getAllGroup();
    }

    @Override
    public void addUser(User user) throws ServiceException{
        this.userMapper.insertUser(user);
    }

    @Override
    public Map<String, Object> getUserListByPage(Integer index,Integer pageSize,User user) throws ServiceException{
        Map<String,Object> params = new HashMap<String,Object>();
        if(index == null)
            index = 1;
        if(pageSize == null)
            pageSize = 3;
        params.put("start", pageSize*(index-1));
        params.put("pageSize", pageSize);
        params.put("user",user);
        System.out.println("pageSize*index============"+pageSize*(index-1));
        List<User> list = this.userMapper.getUserByPage(params);

        int totalCounts = this.userMapper.getUserCounts(user);
        int totalPages = totalCounts%pageSize==0?totalCounts/pageSize:totalCounts/pageSize+1;

        Map<String, Object> mapUsers = new HashMap<String, Object>();
        mapUsers.put("userList", list);
        mapUsers.put("totalCounts", new Integer(totalCounts));
        mapUsers.put("totalPages", new Integer(totalPages));
        mapUsers.put("currentPage",index);
        mapUsers.put("pageSize",pageSize);

        return mapUsers;
    }

    @Override
    public void parseFileToUser(MultipartFile uploadUsersFile) throws ServiceException {
        // 判断文件类型
        String contentType = uploadUsersFile.getContentType();
        if (!contentType.equals("text/plain")){
            throw new  ServiceException();// 文件类型错误
        }
        String fileName = uploadUsersFile.getOriginalFilename();
        if (fileName.indexOf(",")==-1||fileName.endsWith(".txt")||fileName.endsWith(".text")){
            // 文件类型正确
            // 解析文件内容
            try {
                InputStream input = uploadUsersFile.getInputStream();
                InputStreamReader ir = new InputStreamReader(input, "UTF-8");
                BufferedReader reader = new BufferedReader(ir);
                String line = "";
                while ((line = reader.readLine())!=null){
                    User user = new User();
                    String[] temp = line.split(";");
                    if (temp.length!=5){
                        throw new ServiceException();// 数据格式错误
                    }
                    // 封装要保存的数据对象
                    user.setName(temp[0]);
                    user.setLoginName(temp[1]);
                    user.setLoginPswd(temp[2]);
                    user.setRemark(temp[3]);
                    user.setGroupId(Integer.parseInt(temp[4]));
                    // 数据入库
                    this.userMapper.insertUser(user);
                }
            }   catch (IOException e){
                e.printStackTrace();
            }
        }else {
            throw new  ServiceException();// 文件类型错误
        }
    }

    @Override
    public void downloadUsers(int page, int rows, User loginUser, HttpServletResponse response) throws ServiceException {
        // 维护查询参数
        Map<String, Object> params = new HashMap<>();

        params.put("start", rows*(page-1));
        params.put("pageSize", rows);
        params.put("user",loginUser);

        // 分页查询数据
        List<User> dataList = this.userMapper.getUserByPage(params);


        // 创建缓存字符串的数组
        String[] temp = new String[dataList.size()];
        for(int i = 0; i < dataList.size(); i++){
            User user = dataList.get(i);
            StringBuilder builder = new StringBuilder("");
            builder.append(user.getName());
            builder.append(";");
            builder.append(user.getLoginName());
            builder.append(";");
            builder.append(user.getLoginPswd());
            builder.append(";");
            builder.append(user.getRemark());
            builder.append(";");
            builder.append(user.getGroupId());

            temp[i] = builder.toString();
            System.out.println(builder.toString());
        }

        try{
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attchment;filename=users.txt");
            // 使用字节输出流,输出数据. 字符输出流,输出数据有乱码. 字符流是tomcat创建的.默认使用字符集 ISO-8859-1
            OutputStream output = response.getOutputStream();

            for(String s : temp){
                output.write(s.getBytes());
                output.write("\r\n".getBytes());
            }

            output.flush();
        }catch(IOException e){

            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public GroupMapper getGroupMapper() {
        return groupMapper;
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }
}
