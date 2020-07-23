package com.example.demo.utils;

public class getMsg {

    public String getSubmit(int submit){
        String submitname;
        switch (submit){
            case 0: {
                submitname = "未开始";
                break;
            }
            case 1: {
                submitname = "未提交论文";
                break;
            }
            case 2: {
                submitname = "学生暂存";
                break;
            }
            case 3: {
                submitname = "教师审核中";
                break;
            }
            case 4: {
                submitname = "教师退回修改";
                break;
            }
            case 5: {
                submitname = "已审核";
                break;
            }
            default:{
                submitname = "Wrong";
                break;
            }

        }
        return submitname;
    }

    public String getState(int state){
        String statename;
        switch (state){
            case 0: {
                statename = "任务书";
                break;
            }
            case 1: {
                statename = "开题报告";
                break;
            }
            case 2: {
                statename = "中期检查";
                break;
            }
            case 3: {
                statename = "论文定稿";
                break;
            }
            case 4: {
                statename = "论文最终稿";
                break;
            }
            default:{
                statename = "Wrong";
                break;
            }

        }
        return statename;
    }
}
