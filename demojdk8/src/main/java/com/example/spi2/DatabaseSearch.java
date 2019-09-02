package com.example.spi2;

import java.util.List;

/**
 * 数据库搜索实现
 */
public class DatabaseSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}