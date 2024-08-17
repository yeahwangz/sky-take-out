package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /**
     * 新增地址
     * @param addressBook
     */
    void add(AddressBook addressBook);

    /**
     * 查询当前登录用户的所有地址信息
     */
    @Select("select * from sky_take_out.address_book where user_id = #{currentId}")
    List<AddressBook> get(Long currentId);

    /**
     * 查询默认地址
     * @param currentId
     */
    @Select("select * from sky_take_out.address_book where user_id = #{currentId} and is_default = 1")
    AddressBook getDefault(Long currentId);

    /**
     * 根据id修改地址
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 根据id删除地址
     * @param id
     */
    @Delete("delete from sky_take_out.address_book where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 设置默认地址
     * @param id
     */
    @Update("update sky_take_out.address_book set is_default = 1 where id = #{id}")
    void setDefault(Integer id);
}
