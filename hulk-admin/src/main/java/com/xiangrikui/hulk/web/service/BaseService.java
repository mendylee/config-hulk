package com.xiangrikui.hulk.web.service;

import org.springframework.data.domain.Page;

import com.xiangrikui.hulk.web.common.HulkResponse;

/**
 * 创建时间：2017年5月16日
 * <p>修改时间：2017年5月16日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class BaseService<T> {

    public HulkResponse<T> builderResponse(Page<T> record){
        HulkResponse<T> hulkResponse = new HulkResponse<T>();
        hulkResponse.setCount(record.getTotalElements());
        hulkResponse.setResult(record.getContent());
        hulkResponse.setRel(true);
        hulkResponse.setMsg("获取成功");
        return hulkResponse;
    }
}
