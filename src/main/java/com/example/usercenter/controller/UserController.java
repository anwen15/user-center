package com.example.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usercenter.common.BaseResponse;
import com.example.usercenter.common.EoorCode;
import com.example.usercenter.common.ResultUtils;
import com.example.usercenter.excepttion.BusinessException;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.model.request.UserLoginRequest;
import com.example.usercenter.model.request.UserRegisterRequest;
import com.example.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.usercenter.contant.UserContant.ADMIN_ROLE;
import static com.example.usercenter.contant.UserContant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(EoorCode.PARAMS_ERROR,"网络连接无响应");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userpassword = userRegisterRequest.getUserPassword();
        String checkpassword = userRegisterRequest.getCheckPassword();
        String planetCode=userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyEmpty(checkpassword, userAccount, userpassword,planetCode)) {
            return ResultUtils.error(EoorCode.PARAMS_ERROR,"需要参数为空");
        }
        long result= userService.userRegister(userAccount, userpassword, checkpassword,planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(EoorCode.NULL_ERROR,"网络连接无响应");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userpassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyEmpty(userAccount, userpassword)) {
            throw new BusinessException(EoorCode.NULL_ERROR,"账号或密码不能为空");
        }

        User user = userService.dologin(userAccount, userpassword, request);
        return ResultUtils.success(user);
    }
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(EoorCode.SYSTEM_ERROR,"网络连接无响应");
        }

        int userlogout = userService.userlogout(request);
        return ResultUtils.success(userlogout);
    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentuser=(User)attribute;
        if (currentuser == null) {
            throw new BusinessException(EoorCode.NULL_ERROR,"当前用户信息为空");
        }
        Long id = currentuser.getId();
        // TODO: 5/11/2023 校验用户是否合法 
        User byId = userService.getById(id);
        User saftyUser = userService.getSaftyUser(byId);
        return ResultUtils.success(saftyUser);

    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String username,HttpServletRequest request) {
        //管理员功能
        if (!isAdmin(request)) {
            throw new BusinessException(EoorCode.PARAMS_ERROR,"权限不足");
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> collect = userService.list(queryWrapper).stream().map(user -> userService.getSaftyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw  new BusinessException(EoorCode.NULL_ERROR,"标签列表为空");
        }
        List<User> userList = userService.searchuserbytags(tagNameList);
        return ResultUtils.success(userList);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody long id, HttpServletRequest request) {
        //管理员功能
        if (!isAdmin(request)) {
            throw new BusinessException(EoorCode.NO_AUTH, "网络连接无响应");
        }
        if (id <= 0) {
            throw new BusinessException(EoorCode.PARAMS_ERROR, "无用户");
        }
        boolean removeById = userService.removeById(id);
        return ResultUtils.success(removeById);
    }

    /**
     * 验证是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        //管理员功能
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) attribute;
        return user!=null&&user.getUserRole()==ADMIN_ROLE;
    }
}
