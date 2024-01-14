package com.example.usercenter.once;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Importdata {
    public static void main(String[] args) {
        //Excel数据文件放在自己电脑上，能够找到的路径
        String fileName = "C:\\Users\\nicefang\\Downloads\\testExcel.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<Userinfo> userInfoList =
                EasyExcel.read(fileName).head(Userinfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
        Map<String, List<Userinfo>> listMap =
                userInfoList.stream()
                        .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
                        .collect(Collectors.groupingBy(Userinfo::getUsername));
        for (Map.Entry<String, List<Userinfo>> stringListEntry : listMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("username = " + stringListEntry.getKey());
                System.out.println("1");
            }
        }
        System.out.println("不重复昵称数 = " + listMap.keySet().size());
    }
}
