package com.glass.user.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class MessageTest {

    public static void main(String[] args) {
        DateTime dateTime = DateUtil.endOfMonth(new Date());
        System.out.println(dateTime);
    }
}
