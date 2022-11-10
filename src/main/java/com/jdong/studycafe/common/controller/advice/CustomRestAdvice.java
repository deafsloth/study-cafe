package com.jdong.studycafe.common.controller.advice;

import com.jdong.studycafe.common.domain.ErrorResponse;
import com.jdong.studycafe.common.enumerate.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
//        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
//        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
//        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
//        String code = "";
//        String description = "";
//        String detail = "";
//
//        //에러가 있다면
//        if(bindingResult.hasErrors()){
//            //DTO에 설정한 meaasge값을 가져온다
//            detail = bindingResult.getFieldError().getDefaultMessage();
//
//            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
//            String bindResultCode = bindingResult.getFieldError().getCode();
//
//            switch (bindResultCode){
//                case "NotNull":
//                    code = ErrorCode.NOT_NULL.getCode();
//                    description = ErrorCode.NOT_NULL.getDescription();
//                    break;
//                case "Min":
//                    code = ErrorCode.MIN_VALUE.getCode();
//                    description = ErrorCode.MIN_VALUE.getDescription();
//                    break;
//            }
//        }
//
//        return new ErrorResponse(code, description, detail);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.error(e);
        Map<String, String> errorMap = new HashMap<>();
        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error(e);
        Map<String, String> errorMap = new HashMap<>();
        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handlerFKException(Exception e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", "" + System.currentTimeMillis());
        errorMap.put("msg", "constrain fails");
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler({
            NoSuchElementException.class,
            EmptyResultDataAccessException.class
    })
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception e) {
        log.error(e);
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", "" + System.currentTimeMillis());
        errorMap.put("msg", "No Such Element Exception");
        return ResponseEntity.badRequest().body(errorMap);
    }


}
