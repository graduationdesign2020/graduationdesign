// pages/myScore/myScore.js
import {PostRequest} from "../../utils/ajax";
const app = getApp();

Page({
  data: {
    teachergrade: "",
    reviewgrade: "",
    thesisgrade: "",
    allgrade: "",
    userData: {},
  },
  
  onLoad: function(){
    if(app.globalData.login == 2){
      wx.redirectTo({
        url: '../register/index',
      })
    }else{
      if(app.globalData.login == 0){
        app.dataCallback = (data) => {
          if(data.msg == "FAIL"){
            wx.redirectTo({
              url: '../register/index',
            })
          }else{
            var that = this;
            this.setData({userData: data.userData})
            PostRequest('/studentGetGrades', {id: this.data.userData.id}, that.getGrade);
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
        PostRequest('/studentGetGrades', {id: this.data.userData.id}, that.getGrade);
      }
    }
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