package com.cris.framework.base.bean;

import com.cris.framework.util.commons.CollectionUtil;

import java.util.List;

/**
 * Created by owen on 2017/8/14.
 */
public class Param {

    private List<FormParam> formParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(formParamList);
    }

}
