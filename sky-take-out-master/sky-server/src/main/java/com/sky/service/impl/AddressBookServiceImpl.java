package com.sky.service.impl;

import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类业务层
 */
@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     * @param addressBook
     */
    @Transactional
    public void add(AddressBook addressBook) {
        addressBookMapper.add(addressBook);
    }

    /**
     * 查询当前登录用户的所有地址信息
     */
    public List<AddressBook> get(Long currentId) {
       return addressBookMapper.get(currentId);
    }

    /**
     * 查询默认地址
     * @param currentId
     * @return
     */
    public AddressBook getDefault(Long currentId) {
        return addressBookMapper.getDefault(currentId);
    }

    /**
     * 根据id修改地址
     * @param addressBook
     */
    @Transactional
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id删除地址
     * @param id
     */
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    /**
     * 设置默认地址
     * @param id
     */
    public void setDefault(Integer id) {
        addressBookMapper.setDefault(id);
    }
}
