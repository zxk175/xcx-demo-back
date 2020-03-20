package com.zxk175.xcx.common.exception;

import com.zxk175.xcx.common.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxk175
 * @since 2020-03-20 09:52
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response<?> defaultErrorHandler(Exception ex) {
        String message = "";
        if (ex.getCause() != null) {
            message = "-" + ex.getCause().getMessage();
        }

        return Response.success(message);
    }

}