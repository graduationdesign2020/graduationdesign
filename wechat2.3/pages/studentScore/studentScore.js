// pages/studentScore/studentScore.js
var app = getApp();
import {PostRequest} from "../../utils/ajax";

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
    activeNames: [],
  },

  onLoad: function (options) {
    var that = this;
    PostRequest('/getGrades', {}, that.getGrades);
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