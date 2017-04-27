package cn.sxt.controller;

import cn.sxt.entity.Group;
import cn.sxt.entity.User;
import cn.sxt.exception.service.ServiceException;
import cn.sxt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by beichunming on 2017/4/22.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 异步访问方法.AJAX访问.
     * 分页查询用户数据.
     * 请求参数 :
     * page, rows
     */
    @RequestMapping(value = "/getUserByPage2",produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getUserByPage2(
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="rows", defaultValue="5") int rows,
            HttpServletRequest request,
            HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("userInfo");
        try{
            Map<String, Object> result = this.userService.getUserListByPage(page, rows, user);

            // 返回查询的结果. 将结果通过响应输出流,向客户端输出.
            return new  ObjectMapper().writeValueAsString(result);

//            // 异步访问.不能直接转发到视图,不能重定向到视图.
//            // 将查询结果转化成字符串,格式为JSON字符串.通过输出流,写到客户端.
//            // SpringMVC中,建议使用jackson. 类似GSON. springmvc框架集成了jackson的开发能力.
//            ObjectMapper mapper = new ObjectMapper();
//            // 将任意一个合法的java对象,转化成字符串,格式为json格式.
//            // 解析java对象中的property. property作为json对象的属性名.对应get方法的返回值作为属性值.
//            // 如果对象是Map集合.key是属性名.value是属性值. {key:value,key:value}
//            // 如果对象是collection集合.返回JSON字符串是一个数组[{},{}]
//            String json = mapper.writeValueAsString(result);
//            System.out.println("result==============="+result);
//
//            response.setContentType("application/json;charset=UTF-8");
//
//            response.getWriter().println(json);
//            response.getWriter().flush();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestParam("TXT_NAME_01")String loginname,@RequestParam("PWD_NAME_01")String password, HttpServletRequest request){
        System.out.println(loginname+"-----"+password);
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cn/sxt/spring/application/applicationContext-*.xml");
//        UserService userService = (UserService) context.getBean("userService");
        try {
            User user = this.userService.getUserByNameAndPwd(loginname,password);
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",user);
            System.out.println(user);
        }   catch (Exception e){
            return "/user/login.jsp";
        }
        return "/main.jsp";
    }

    @RequestMapping(value ="/logout",method = {RequestMethod.GET})
    public String logout(HttpServletRequest request){
        //获取session中的所有属性
        Enumeration em = request.getSession().getAttributeNames();
        HttpSession session = request.getSession();
        //循环遍历移除
        while (em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        session.invalidate();
        return "/user/login.jsp";
    }

    @RequestMapping(value = "/preRegister")
    public  String preRegister(HttpServletRequest request){
        try {
            List<Group> groupList = this.userService.getAllGroupInfo();
            request.setAttribute("groupList",groupList);
            System.out.println(groupList);

        } catch (ServiceException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "/user/register.jsp";
    }
    @RequestMapping(value = "/doRegister",method = {RequestMethod.POST})
    public  String doRegister(User user,String rePassword){
        try {
            this.userService.addUser(user);
            System.out.println(user);

        } catch (ServiceException e){
            e.printStackTrace();
            return "/index.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/user/login.jsp";
    }

    @RequestMapping(value = "/getUsersByPage",method ={RequestMethod.GET,RequestMethod.POST})
    public  String getUsersByPage(Integer page, Integer rows, HttpServletRequest request){
        try {
            User user =  (User)request.getSession().getAttribute("userInfo");
            Map<String,Object> map  = this.userService.getUserListByPage(page,rows,user);
            System.out.println(map);
            request.setAttribute("items",map);
        }   catch (Exception e){
            e.printStackTrace();
        }
        return "/userView.jsp";
    }

    /**
     *  上传文件. 客户端上传txt文本文件. 文本中每行数据是一个用户对象.
     * 文件内容的解析,应该交给服务对象实现.
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/upload")
    public String fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile){
        System.out.println("处理文件上传 - " + uploadFile);
        // 调用service中的方法.解析文件.数据入库.
      try {
           this.userService.parseFileToUser(uploadFile);
      }   catch (ServiceException e){
          e.printStackTrace();
          return "/error/error.jsp";
      }   catch (Exception e){
          e.printStackTrace();
          return "/error/error.jsp";
      }
        return "forward:/user/getUsersByPage.action";
    }
    /**
     * 页面跳转方法. 跳转到文件上传页面
     */
    @RequestMapping("/toUploadUsers")
    public String toUploadUsers(){

        return "/uploadFile.jsp";
    }

    /**
     * 到出用户数据.
     */
    @RequestMapping("/downloadUsers")
    public void downloadUsers(
            @RequestParam(value="page", defaultValue="1")int page,
            @RequestParam(value="rows", defaultValue="3")int rows,
            HttpServletRequest request,
            HttpServletResponse response){
        try{
            User loginUser = (User) request.getSession().getAttribute("userInfo");
            this.userService.downloadUsers(page, rows, loginUser, response);
        }catch(ServiceException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
