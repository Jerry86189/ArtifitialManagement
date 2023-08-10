package com.jerry86189.artifitialmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry86189.artifitialmanagement.dto.LoginResponse;
import com.jerry86189.artifitialmanagement.entity.User;
import com.jerry86189.artifitialmanagement.exceptions.DeleteFailed;
import com.jerry86189.artifitialmanagement.exceptions.InsertFailed;
import com.jerry86189.artifitialmanagement.exceptions.NotFound;
import com.jerry86189.artifitialmanagement.exceptions.UpdateFailed;
import com.jerry86189.artifitialmanagement.mapper.UserMapper;
import com.jerry86189.artifitialmanagement.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description: TODO
 * date: 2023/06/10 10:26
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final static String SECRET_KEY = "RT02HrJ6lXY+ZUbWv5i/Xdejq5hmetnrPNsIHWd1Eoo=";

    /**
     * 用户登录.
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回登录成功后的相关信息，包含用户信息和token
     */
    @Override
    @Transactional
    public LoginResponse login(String username, String password) {
        System.out.println("enter UserServiceImpl login");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new NotFound("Your account" + username + "log in failed");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(generateToken(user));
        loginResponse.setUser(user);

        return loginResponse;
    }

    /**
     * 生成JWT令牌
     *
     * @param user 用户信息
     * @return JWT令牌
     */
    private String generateToken(User user) {
        if (user == null || user.getRole() == null) {
            return null;
        }
        String jws = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().getValue())
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

    /**
     * 注册普通用户.
     *
     * @param newUser 普通用户信息
     * @return 返回注册成功的用户信息
     */
    @Override
    @Transactional
    public User registerNorm(@NotNull User newUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("username", newUser.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new InsertFailed("This username has been registered");
        }

        int insert = userMapper.insert(newUser);
        if (insert != 1) {
            throw new InsertFailed("Register failed");
        }

        return newUser;
    }

    /**
     * 注册管理员用户.
     *
     * @param newUser            新的管理员信息
     * @param otherAdminUsername 已存在的管理员用户名
     * @param otherAdminPassword 已存在的管理员密码
     * @return 返回注册成功的管理员信息
     */
    @Override
    @Transactional
    public User registerAdmin(User newUser, String otherAdminUsername, String otherAdminPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("username", otherAdminUsername).eq("password", otherAdminPassword).eq("role", "ADMIN");
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new InsertFailed("Your verify password or username is wrong");
        }

        QueryWrapper<User> queryWrapper1 = new QueryWrapper<User>().eq("username", newUser.getUsername()).eq("password", newUser.getPassword());
        User user1 = userMapper.selectOne(queryWrapper1);
        if (user1 != null) {
            throw new InsertFailed("This username has been registered");
        }

        int insert = userMapper.insert(newUser);
        if (insert != 1) {
            throw new InsertFailed("Register failed");
        }

        return newUser;
    }

    /**
     * 更新自身用户信息.
     *
     * @param id             用户ID
     * @param verifyPassword 验证的密码
     * @param newUsername    新的用户名
     * @param newPassword    新的密码
     * @return 返回更新后的用户信息
     */
    @Override
    @Transactional
    public User updateSelfUserInfo(Long id, String verifyPassword, String newUsername, String newPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", id).eq("password", verifyPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UpdateFailed("Your verify password is wrong");
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>().eq("user_id", id).set("username", newUsername).set("password", newPassword);
        int update = userMapper.update(null, updateWrapper);

        if (update!= 1) {
            throw new UpdateFailed("Update failed");
        }
        return user;
    }

    /**
     * 根据ID删除用户.
     *
     * @param id             用户ID
     * @param verifyPassword 验证的密码
     * @return 无返回值
     */
    @Override
    @Transactional
    public Void deleteUserById(Long id, String verifyPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", id).eq("password", verifyPassword);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("id: " + id);
        System.out.println("verifyPassword: " + verifyPassword);
        System.out.println(user);
        if (user == null) {
            throw new InsertFailed("Your verify password is wrong or the account has been deleted");
        }

        int delete = userMapper.deleteById(id);
        if (delete != 1) {
            throw new InsertFailed("Delete failed");
        }
        return null;
    }

    /**
     * 分页获取所有普通用户信息.
     *
     * @param pageNum  页码
     * @param pageSize 每页的大小
     * @return 返回用户列表
     */
    @Override
    public List<User> getAllNormUsers(int pageNum, int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("role", "NORM");
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);

        if (userPage == null || userPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (userPage.getRecords().isEmpty()) {
            throw new NotFound("No users found");
        }

        return userPage.getRecords();
    }

    /**
     * 根据ID获取用户信息.
     *
     * @param id 用户ID
     * @return 返回用户信息
     */
    @Override
    public User getUserById(Long id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", id);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new NotFound("Query failed");
        }
        return user;
    }

    /**
     * 根据用户名分页查询用户信息.
     *
     * @param username 用户名
     * @param pageNum  页码
     * @param pageSize 每页的大小
     * @return 返回普通用户列表
     */
    @Override
    public List<User> getNormUsersByUsername(String username, int pageNum, int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().like("username", username).eq("role", "NORM");
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);

        if (userPage == null || userPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (userPage.getRecords().isEmpty()) {
            throw new NotFound("No users found");
        }

        return userPage.getRecords();
    }

    /**
     * 更新普通用户信息.
     *
     * @param user 用户信息
     * @return 无返回值
     */
    @Override
    @Transactional
    public Void updateNormUser(@NotNull User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("username", user.getUsername());
        User user1 = userMapper.selectOne(queryWrapper);
        if (user1 != null) {
            throw new UpdateFailed("This username has been registered");
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>().eq("user_id", user.getId()).set("username", user.getUsername()).set("password", user.getPassword());
        int update = userMapper.update(null, updateWrapper);
        if (update!= 1) {
            throw new UpdateFailed("Update failed");
        }
        return null;
    }

    /**
     * 根据ID列表批量删除用户.
     *
     * @param ids 用户ID列表
     * @return 返回删除数量
     */
    @Override
    @Transactional
    public int deleteUsersByIdList(List<Long> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    /**
     * 获取所有普通用户的数量。
     *
     * @return 返回数据库中所有普通用户的数量。
     */
    @Override
    public int getCountOfAllNormUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("role", "NORM");
        return userMapper.selectCount(queryWrapper);
    }

    /**
     * 根据用户名获取普通用户的数量。
     *
     * @param username 需要查询的用户名。
     * @return 返回数据库中与给定用户名匹配的普通用户的数量。
     */
    @Override
    public int getCountOfNormUsersByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().like("username", username).eq("role", "NORM");
        return userMapper.selectCount(queryWrapper);
    }
}
