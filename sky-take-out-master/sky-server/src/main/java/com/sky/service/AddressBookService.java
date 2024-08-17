package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    /**
     * 新增地址
     * @param addressBook
     */
    void add(AddressBook addressBook);

    /**
     * 查询当前登录用户的所有地址信息
     */
    List<AddressBook> get(Long currentId);

    /**
     * 查询默认地址
     * @param currentId
     * @return
     */
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
    void deleteById(Long id);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 设置默认地址
     * @param id
     */
    void setDefault(Integer id);
}
