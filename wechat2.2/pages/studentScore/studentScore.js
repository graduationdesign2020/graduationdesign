// pages/studentScore/studentScore.js
var app = getApp();

Page({
  data: {
    grades: [{
      name: "NCT董思成",
      grade: {
        id: '1',
        teachergrade: "A",
        reviewgrade: "A",
        thesisgrade: "",
        totalgrade: ""
      }
    },
    {
      name: "NCT李马克",
      grade: {
        id: '2',
        teachergrade: "A",
        reviewgrade: "A",
        thesisgrade: "",
        totalgrade: ""
      }
    },
    {
      name: "NCT李帝努",
      grade: {
        id: '3',
        teachergrade: "A",
        reviewgrade: "A",
        thesisgrade: "",
        totalgrade: ""
      }
    }],
    userData: {},
    activeNames: [],
  },

  onLoad: function (options) {
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
            PostRequest('/teacherGetGrades', {id: this.data.userData.id}, that.getGrades);
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
        PostRequest('/teacherGetGrades', {id: this.data.userData.id}, that.getGrades);
      }
    }
  },

  getGrades: function(data){
    this.setData({grades: data});
  },

  onChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
})