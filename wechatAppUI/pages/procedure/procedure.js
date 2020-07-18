// pages/procedure/procedure.js
//获取应用实例
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userData: {name: "小明", dept: "SE", auth: 1, id: 12345},
    states: [
      {state: "任务书", submit:0, end_time:"07-01 00:00"},
      {state: "开题报告", submit:1, end_time:"07-10 00:00"},
      {state: "中期检查", submit:2, end_time:"07-13 00:00"},
      {state: "论文定稿", submit:3, end_time:"07-18 00:00"},
      {state: "论文最终稿", submit:4}
    ]
  },

   /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function() {
    var that = this;
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
            this.setData({userData: data.userData})
            PostRequest('/checkSelfProcess', {stu_id: this.data.userData.id}, that.setStates);
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
        PostRequest('/checkSelfProcess', {stu_id: this.data.userData.id}, that.setStates);
      }
    }
  },

  setStates: function(data){
    this.setData({states: data});
  }
})