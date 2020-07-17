// components/navBar.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    active: String,
    auth: Boolean
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    onChange(e) {
      switch (e.detail) {
        case "home": {
          if(this.properties.active == 0) return;
          else{
            wx.redirectTo({
              url: '../home/home',
            })
          }
        }
        case "myprofile": {
          if(this.properties.active == 2) return;
          else{
            wx.redirectTo({
              url: '../myProfile/Center',
            })
          }
        }
        case "studentFinished": {
          if(this.properties.active == 1) return;
          else{
            wx.redirectTo({
              url: '../processList/processList',
            })
          }
        }
        case "procedure": {
          if(this.properties.active == 1) return;
          else{
            wx.redirectTo({
              url: '../procedure/procedure',
            })
          }
        }
      }
    }
  }
})
