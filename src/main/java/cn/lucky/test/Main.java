package cn.lucky.test;


import cn.lucky.dao.IUserDAO;
import cn.lucky.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {



    @Test//分页
    public void test05(){
        String path="mybatis-config.xml";

        InputStream is=null;

        try {

            is=Resources.getResourceAsStream(path);

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();

            SqlSessionFactory factory=builder.build(is);

            SqlSession session=factory.openSession();

            String userName="";
            Integer role=null;
            Integer from=5;
            Integer size=0;

            List<User> list = session.getMapper(IUserDAO.class).getUserListByPage(userName,role,from,size);
            System.out.println(list.size());
            for (User item:list) {
                System.out.println(item.getUserName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * choose - when
     * 犹如switch标签
     * when = case块
     * otherwise = default
     * 实现多选单条件查询
     */
    @Test
    public void test04(){
        String path="mybatis-config.xml";

        InputStream is=null;

        try {

            is=Resources.getResourceAsStream(path);

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();

            SqlSessionFactory factory=builder.build(is);

            SqlSession session=factory.openSession();

            String userName="";
            String code="";
            Integer role=null;

            List<User> userListByCondis = session.getMapper(IUserDAO.class).getUserListByCondis(userName, role, code);

            for (User item:userListByCondis) {
                System.out.println(item.getUserName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //foreach 入参Map类型 根据角色Id查询用户列表
    @Test
    public void test03(){
        String path="mybatis-config.xml";
        InputStream is=null;
        try {
            is=Resources.getResourceAsStream(path);

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();

            SqlSessionFactory factory=builder.build(is);

            SqlSession session=factory.openSession();

            Map<String,Object> roleMap=new HashMap<String,Object>();
            List<Integer> list=new ArrayList<Integer>();
            list.add(2);
            list.add(3);
            roleMap.put("one",1);
            roleMap.put("tow",1);

            roleMap.put("roleIds",list);

            List<User> userListByRoleId = session.getMapper(IUserDAO.class).getUserListByRoleIds(roleMap);
            for (User item:userListByRoleId) {
                System.out.println(item.getUserName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //foreach 入参数组类型 根据角色Id查询用户列表
    @Test
    public void test02(){
        String path="mybatis-config.xml";
        InputStream is=null;
        try {
            is=Resources.getResourceAsStream(path);

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();

            SqlSessionFactory factory=builder.build(is);

            SqlSession session=factory.openSession();

            Integer[] roleIds={2,3};
            List<User> userListByRoleId = session.getMapper(IUserDAO.class).getUserListByRoleId(roleIds);
            System.out.println(userListByRoleId.size());
            for (User item:userListByRoleId) {
                System.out.println(item.getUserName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //if   根据用户名称和角色ID多条件查询用户列表
    @Test
    public void test01(){
        String path="mybatis-config.xml";
        InputStream is=null;
        try {
            is= Resources.getResourceAsStream(path);

            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();

            SqlSessionFactory factory=builder.build(is);

            SqlSession session=factory.openSession();

            String userName="";
            Integer roleId=1;
            List<User> userList = session.getMapper(IUserDAO.class).getUserList(userName, roleId);
            for (User item:userList) {
                System.out.println(item.getUserName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
