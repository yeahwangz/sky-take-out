package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressBookDTO implements Serializable {
    private String cityCode;

    private String cityName;

    private String consignee;

    private String detail;

    private String districtCode;

    private String districtName;

    private Integer id;

    private Integer isDefault;

    private String label;

    private String phone;

    private String provinceCode;

    private String provinceName;

    private String sex;

    private Integer userId;
}
