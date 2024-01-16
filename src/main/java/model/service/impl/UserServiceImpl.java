package model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.domain.User;
import model.service.UserService;
import model.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author nicefang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-01-15 00:12:13
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




