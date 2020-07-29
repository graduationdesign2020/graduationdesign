const urlhead = "http://localhost:8888"

export const PostRequest = (url, postdata, callback) => {
  const app = getApp()
  console.log(postdata)
  if(app.globalData.get_cookie){
    console.log('get cookie')
    console.log(wx.getStorageSync('cookies'))
    wx.request({
      url: urlhead+url,
      data: postdata,
      header: {"Cookie": wx.getStorageSync('cookies')},
      success(res) {
        console.log(res)
        console.log(res.data)
        callback(res.data);
      },
      method:"POST"
    });
  }
  else{
    if(!app.cookieCallbacks){
      app.cookieCallbacks = new Array()
    }
    console.log(app.cookieCallbacks)
    app.cookieCallbacks.push({
      url: url,
      data: postdata,
      callback: callback,
    })
  }
}