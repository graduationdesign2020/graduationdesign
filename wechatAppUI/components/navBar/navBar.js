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
          if(this.properties.active == "home") return;
          else{
            wx.redirectTo({
              url: '../home/home',
            })
          }
        }
        case "myprofile": {
          if(this.properties.active == "myprofile") return;
          else{
            wx.redirectTo({
              url: '../myProfile/Center',
            })
          }
        }
        case "processList": {
          if(this.properties.active == "processList") return;
          else{
            wx.redirectTo({
              url: '../processList/processList',
            })
          }
        }
        case "process": {
          if(this.properties.active == "process") return;
          else{
            wx.redirectTo({
              url: '../process/procedure',
            })
          }
        }
      }
    }
  }
})
