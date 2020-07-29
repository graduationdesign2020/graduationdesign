// pages/procedure/procedure.js
//获取应用实例
const app = getApp();
import {PostRequest} from "../../utils/ajax";

Page({

  data: {
    states: [
      {state: "任务书", submit:0, end_time:"07-01 00:00"},
      {state: "开题报告", submit:1, end_time:"07-10 00:00"},
      {state: "中期检查", submit:2, end_time:"07-13 00:00"},
      {state: "论文定稿", submit:3, end_time:"07-18 00:00"},
      {state: "论文最终稿", submit:4, end_time:"07-18 00:00"}
    ],
    auth:false,
  },

  onLoad: function() {
    var that = this;
    PostRequest('/checkSelfProcess', {}, that.setStates);
  },

  setStates: function(data){
    console.log(data)
    this.setData({states: data});
  }
})