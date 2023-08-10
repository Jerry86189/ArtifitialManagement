package com.jerry86189.artifitialmanagement.controller;

import com.jerry86189.artifitialmanagement.exceptions.DeleteFailed;
import com.jerry86189.artifitialmanagement.exceptions.InsertFailed;
import com.jerry86189.artifitialmanagement.exceptions.NotFound;
import com.jerry86189.artifitialmanagement.exceptions.UpdateFailed;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

/**
 * ClassName: GlobalExceptionController
 * Description: TODO
 * date: 2023/06/11 00:44
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    /**
     * 处理 DeleteFailed 异常
     *
     * @param ex 自定义的DeleteFailed异常
     * @return 哈希MAP形式的错误信息
     */
    @ExceptionHandler(DeleteFailed.class)
    public final @NotNull ResponseEntity<Object> handleDeleteFailed(@NotNull DeleteFailed ex) {
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("error", "Delete operation failed");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理 InsertFailed 异常
     *
     * @param ex 自定义的InsertFailed异常
     * @return 哈希MAP形式的错误信息
     */
    @ExceptionHandler(InsertFailed.class)
    public final @NotNull ResponseEntity<Object> handleInsertFailed(@NotNull InsertFailed ex) {
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("error", "Insert operation failed");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理 NotFound 异常
     *
     * @param ex 自定义的NotFound异常
     * @return 哈希MAP形式的错误信息
     */
    @ExceptionHandler(NotFound.class)
    public final @NotNull ResponseEntity<Object> handleNotFound(@NotNull NotFound ex) {
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("error", "Resource not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * 处理 UpdateFailed 异常
     *
     * @param ex 自定义的UpdateFailed异常
     * @return 哈希MAP形式的错误信息
     */
    @ExceptionHandler(UpdateFailed.class)
    public final @NotNull ResponseEntity<Object> handleUpdateFailed(@NotNull UpdateFailed ex) {
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("error", "Update operation failed");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
