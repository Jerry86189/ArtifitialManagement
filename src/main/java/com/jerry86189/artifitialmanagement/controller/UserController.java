package com.jerry86189.artifitialmanagement.controller;

import com.jerry86189.artifitialmanagement.dto.*;
import com.jerry86189.artifitialmanagement.entity.*;
import com.jerry86189.artifitialmanagement.enumpack.*;
import com.jerry86189.artifitialmanagement.service.AsyncService;
import com.jerry86189.artifitialmanagement.service.OperateMsgService;
import com.jerry86189.artifitialmanagement.service.UserService;
import com.jerry86189.artifitialmanagement.service.impl.OperateMsgServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: UserController
 * Description: TODO
 * date: 2023/06/09 23:10
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @NotNull LoginRequest loginRequest) {
        LoginResponse login = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        // Create an Authentication object
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                AuthorityUtils.createAuthorityList(login.getUser().getRole().getValue())
        );
        // Set the Authentication object in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(auth);

        return ResponseEntity.ok().body(login);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @NotNull RegisterRequest registerRequest) {
        System.out.println("register");
        String role = registerRequest.getNewUser().getRole().getValue();
        User user;
        if (role.equals(Role.ADMIN.getValue())) {
            user = userService.registerAdmin(registerRequest.getNewUser(), registerRequest.getOtherAdminUsername(), registerRequest.getOtherAdminPassword());
            return ResponseEntity.ok().body(user);
        }

        user = userService.registerNorm(registerRequest.getNewUser());
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/both/update_self_account")
    public ResponseEntity<User> updateSelfAccount(@RequestBody @NotNull UpdateSelfRequest updateSelfAccountRequest) {
        User user = userService.updateSelfUserInfo(
                updateSelfAccountRequest.getId(),
                updateSelfAccountRequest.getVerifyPassword(),
                updateSelfAccountRequest.getNewUsername(),
                updateSelfAccountRequest.getNewPassword());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/both/delete_self_account")
    public ResponseEntity<Void> deleteSelfAccount(@RequestBody @NotNull DeleteSelfRequest deleteSelfAccountRequest) {
        System.out.println("id: " + deleteSelfAccountRequest.getId());
        System.out.println("password: " + deleteSelfAccountRequest.getVerifyPassword());
        userService.deleteUserById(deleteSelfAccountRequest.getId(), deleteSelfAccountRequest.getVerifyPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/get_all_norm_account")
    public ResponseEntity<GetUsersResponse> getUsersByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<User> users = userService.getAllNormUsers(pageNum, pageSize);
        int totalCount = userService.getCountOfAllNormUsers();

        GetUsersResponse getUsersResponse = new GetUsersResponse();
        getUsersResponse.setUsers(users);
        getUsersResponse.setCurrentPage(pageNum);
        getUsersResponse.setPageSize(pageSize);
        getUsersResponse.setTotalCount(totalCount);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            totalPages++;
        }
        getUsersResponse.setTotalPages(totalPages);
        return ResponseEntity.ok().body(getUsersResponse);
    }

    @GetMapping("/admin/get_by_id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/get_by_username/{username}")
    public ResponseEntity<GetUsersResponse> getUserByUsername(@PathVariable String username, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<User> users = userService.getNormUsersByUsername(username, pageNum, pageSize);
        int totalCount = userService.getCountOfNormUsersByUsername(username);

        GetUsersResponse usersResponse = new GetUsersResponse();
        usersResponse.setUsers(users);
        usersResponse.setCurrentPage(pageNum);
        usersResponse.setPageSize(pageSize);
        usersResponse.setTotalCount(totalCount);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            totalPages++;
        }
        usersResponse.setTotalPages(totalPages);

        return ResponseEntity.ok().body(usersResponse);
    }

    @PostMapping("/admin/update_norm_account")
    public ResponseEntity<Void> updateNormAccount(@RequestBody @NotNull User user) {
        userService.updateNormUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/delete_norm_account_ids")
    public ResponseEntity<Void> deleteNormAccount(@RequestBody @NotNull List<Long> ids) {
        userService.deleteUsersByIdList(ids);
        return ResponseEntity.ok().build();
    }
}
