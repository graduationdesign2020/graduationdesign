// pages/studentScore/studentScore.js
var app = getApp();

Page({
  data: {
    grades: [{
      id: 1,
      name: "NCT董思成",
      teachergrade: "",
	    reviewgrade: 100,
	    thesisgrade: 100,
	    allgrade: 100,
    },
    {
      id: 2,
      name: "NCT李马克",
      teachergrade: 100,
	    reviewgrade: "",
	    thesisgrade: 100,
	    allgrade: 100,
    },
    {
      id: 7,
      name: "NCT李帝努",
      teachergrade: 100,
	    reviewgrade: 100,
	    thesisgrade: "",
	    allgrade: 100,
    }],
    userData: {},
    activeNames: [],
  },

  onLoad: function (options) {
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