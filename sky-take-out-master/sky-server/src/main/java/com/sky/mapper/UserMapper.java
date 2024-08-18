package com.sky.mapper;

import com.sky.entity.Orders;
import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from sky_take_out.user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param byOpenid
     */
    void insert(User byOpenid);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @Select("select * from sky_take_out.user where id = #{userId}")
    User getById(Long userId);
}
