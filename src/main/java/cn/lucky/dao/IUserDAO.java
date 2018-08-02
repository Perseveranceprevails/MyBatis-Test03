package cn.lucky.dao;

import cn.lucky.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDAO {

    //if-where   根据用户名称和角色ID多条件查询用户列表
    // public List<User> getUserList(@Param("userName")String userName,@Param("userRole")Integer roleId);

    //if-trim 根据用户名称和角色ID多条件查询用户列表
    public List<User> getUserList(@Param("userName")String userName,@Param("userRole")Integer roleId);

    //if-set  根据用户Id修改用户信息
    public int mpdifyUserInfo(User user);

    //foreach 入参数组类型 根据角色Id查询用户列表
    public List<User> getUserListByRoleId(Integer[] roleIds);

    //foreach 入参Map类型 根据角色Id查询用户列表
    public List<User> getUserListByRoleIds(Map<String,Object> roleMap);

    /**
     * choose - when
     * 犹如switch标签
     * when = case块
     * otherwise = default
     * 实现单条间查询
     */
    public List<User> getUserListByCondis(@Param("userName")String username,
                                          @Param("userRole")Integer role,
                                          @Param("userCode")String code);

    /**
     * 分页
     */
    public List<User> getUserListByPage(@Param("userName")String name,
                                        @Param("userRole")Integer role,
                                        @Param("from")Integer from,
                                        @Param("pageSize")Integer size);
}
