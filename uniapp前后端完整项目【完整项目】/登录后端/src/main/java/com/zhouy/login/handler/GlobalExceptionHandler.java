//package com.zhouy.login.handler;
//
//import com.zhouy.login.error.ErrorResponse;
//import com.zhouy.login.model.Result;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//@ResponseBody
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = NullPointerException.class)
//    public Result NullPointerExceptionHandler(Exception e){
//        ErrorResponse errorResponse=new ErrorResponse(e.getClass().getName(),e.getMessage());
//        return Result.fail(errorResponse,201);
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public Result ExceptionHandler(Exception e){
//        ErrorResponse errorResponse=new ErrorResponse(e.getClass().getName(),e.getMessage());
//        return Result.fail(errorResponse,202);
//    }
//
//
//
//}
