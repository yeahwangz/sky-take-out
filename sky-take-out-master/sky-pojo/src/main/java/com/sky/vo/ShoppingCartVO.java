package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartVO implements Serializable {
    private Integer id;

    private String name;

    private String image;

    private Integer userId;

    private Number amount;

    private String createTime;

    private String dishFlavor;

    private Integer dishId;

    private Integer number;

    private Integer setmealId;
}
