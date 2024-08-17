package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.dto.AddressBookDTO;
import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import com.sky.vo.AddressBookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userAddressBookController")
@RequestMapping("/user/addressBook")
@Api(tags = "C端-地址簿接口")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     * @param addressBookDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增地址")
    public Result add(@RequestBody AddressBookDTO addressBookDTO){
        log.info("新增地址：{}",addressBookDTO);
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO,addressBook);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.add(addressBook);
        return Result.success();
    }

    /**
     * 查询当前登录用户的所有地址信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询当前登录用户的所有地址信息")
    public Result<List<AddressBook>> get(){
        log.info("查询当前登录用户的所有地址信息");
        Long currentId = BaseContext.getCurrentId();
        List<AddressBook> addressBooks = addressBookService.get(currentId);
        return Result.success(addressBooks);
    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    public Result<AddressBook> getDefault(){
        log.info("查询默认地址");
        Long currentId = BaseContext.getCurrentId();
        return Result.success(addressBookService.getDefault(currentId));
    }

    /**
     * 根据id修改地址
     * @param addressBookDTO
     * @return
     */
    @PutMapping
    @ApiOperation("根据id修改地址")
    public Result update(@RequestBody AddressBookDTO addressBookDTO){
        log.info("根据id修改地址：{}",addressBookDTO);
        AddressBook addressBook = new AddressBook();
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 根据id删除地址
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据id删除地址")
    public Result deleteById(@RequestParam Long id){
        log.info("根据id删除地址：{}",id);
        addressBookService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> getById(@PathVariable Long id){
        log.info("根据id查询地址：{}",id);
        return Result.success(addressBookService.getById(id));
    }

    /**
     * 设置默认地址
     * @param addressBookDTO
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(@RequestBody AddressBookDTO addressBookDTO){
        log.info("设置默认地址：{}",addressBookDTO);
        addressBookService.setDefault(addressBookDTO.getId());
        return Result.success();
    }
}
