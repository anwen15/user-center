package com.example.usercenter.mapper;

import com.example.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author nicefang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-10-30 21:06:35
* @Entity model.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




