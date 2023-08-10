package com.jerry86189.artifitialmanagement.service;

import com.jerry86189.artifitialmanagement.dto.GetUsersResponse;
import com.jerry86189.artifitialmanagement.dto.LoginResponse;
import com.jerry86189.artifitialmanagement.entity.User;

import java.util.List;

/**
 * ClassName: UserService
 * Description: TODO
 * date: 2023/06/09 23:15
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public interface UserService {
    /**
     * 用户登录.
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回登录成功后的相关信息，包含用户信息和token
     */
    LoginResponse login(String username, String password);

    /**
     * 注册普通用户.
     *
     * @param newUser 普通用户信息
     * @return 返回注册成功的用户信息
     */
    User registerNorm(User newUser);

    /**
     * 注册管理员用户.
     *
     * @param newUser 新的管理员信息
     * @param otherAdminUsername 已存在的管理员用户名
     * @param otherAdminPassword 已存在的管理员密码
     * @return 返回注册成功的管理员信息
     */
    User registerAdmin(User newUser, String otherAdminUsername, String otherAdminPassword);

    /**
     * 更新自身用户信息.
     *
     * @param id 用户ID
     * @param verifyPassword 验证的密码
     * @param newUsername 新的用户名
     * @param newPassword 新的密码
     * @return 返回更新后的用户信息
     */
    User updateSelfUserInfo(Long id, String verifyPassword, String newUsername, String newPassword);

    /**
     * 根据ID删除用户.
     *
     * @param id 用户ID
     * @param verifyPassword 验证的密码
     * @return 无返回值
     */
    Void deleteUserById(Long id, String verifyPassword);

    /**
     * 分页获取所有普通用户信息.
     *
     * @param pageNum 页码
     * @param pageSize 每页的大小
     * @return 返回用户列表
     */
    List<User> getAllNormUsers(int pageNum, int pageSize);

    /**
     * 根据ID获取用户信息.
     *
     * @param id 用户ID
     * @return 返回用户信息
     */
    User getUserById(Long id);

    /**
     * 根据用户名分页查询用户信息.
     *
     * @param username 用户名
     * @param pageNum 页码
     * @param pageSize 每页的大小
     * @return 返回普通用户列表
     */
    List<User> getNormUsersByUsername(String username, int pageNum, int pageSize);

    /**
     * 更新普通用户信息.
     *
     * @param user 用户信息
     * @return 无返回值
     */
    Void updateNormUser(User user);

    /**
     * 根据ID列表批量删除用户.
     *
     * @param ids 用户ID列表
     * @return 返回删除数量
     */
    int deleteUsersByIdList(List<Long> ids);

    /**
     * 获取所有普通用户的数量。
     *
     * @return 返回数据库中所有普通用户的数量。
     */
    int getCountOfAllNormUsers();

    /**
     * 根据用户名获取普通用户的数量。
     *
     * @param username 需要查询的用户名。
     * @return 返回数据库中与给定用户名匹配的普通用户的数量。
     */
    int getCountOfNormUsersByUsername(String username);
}
