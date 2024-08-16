package com.sky.mapper;

import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * 根据菜品id查询对应套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量插入口味数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据id查询套餐菜品
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getSetmealDishById(Long id);

    /**
     * 根据套餐id删除菜品
     * @param id
     */
    @Delete("delete from sky_take_out.setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);

    /**
     * 根据套餐ids删除菜品
     * @param ids
     */
    void deleteBySetmealIds(List<Long> ids);
}
