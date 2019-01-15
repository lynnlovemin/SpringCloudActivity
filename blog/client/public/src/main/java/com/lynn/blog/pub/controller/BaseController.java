package com.lynn.blog.pub.controller;

import com.lynn.blog.common.result.SingleResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public abstract class BaseController {

    @ExceptionHandler
    public SingleResult doError(Exception exception) {
        if(StringUtils.isBlank(exception.getMessage())){
            return SingleResult.buildFailure();
        }
        return SingleResult.buildFailure(exception.getMessage());
    }

    protected void validate(BindingResult result){
        if(result.hasFieldErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }
}
