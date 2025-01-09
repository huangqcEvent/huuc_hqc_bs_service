package com.huuc.result;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页实体")
public class PageResult<T> implements Serializable {

    private long total; //总记录数

    private List<T> list; //当前页数据集合

}
