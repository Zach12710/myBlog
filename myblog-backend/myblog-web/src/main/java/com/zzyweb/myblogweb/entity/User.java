package com.zzyweb.myblogweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiyi
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private Integer gender;
}
