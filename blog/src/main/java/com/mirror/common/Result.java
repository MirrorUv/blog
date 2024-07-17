package com.mirror.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果集封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    private long code;
    private String message;
    private T data;


}
