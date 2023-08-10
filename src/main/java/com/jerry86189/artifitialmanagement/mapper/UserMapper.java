package com.jerry86189.artifitialmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry86189.artifitialmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Description: TODO
 * date: 2023/06/10 10:30
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
