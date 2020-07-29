// pages/myScore/myScore.js
import {PostRequest} from "../../utils/ajax";
const app = getApp();

Page({
  data: {
    teachergrade: "",
    reviewgrade: "",
    thesisgrade: "",
    allgrade: "",
  },
  
  onLoad: function(){
    var that = this;
    this.setData({userData: app.globalData.userData});
    PostRequest('/getSelfGrade', {}, that.getGrade);
  },

  getGrade: function(data) {
    this.setData({
    teachergrade: data.teachergrade,
    reviewgrade: data.reviewgrade,
    thesisgrade: data.thesisgrade,
    allgrade: data.allgrade,
    })
  },
})