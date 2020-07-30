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
    PostRequest('/getSelfGrade', {}, that.getGrade);
  },

  getGrade: function(data) {
    this.setData({
    teachergrade: data.teachergrade?data.teachergrade:"",
    reviewgrade: data.reviewgrade?data.reviewgrade:"",
    thesisgrade: data.thesisgrade?data.thesisgrade:"",
    allgrade: data.allgrade?data.allgrade:"",
    })
  },
})