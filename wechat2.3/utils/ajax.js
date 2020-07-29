const urlhead = "http://localhost:8888"

export const PostRequest = (url, postdata, callback, failcallback = (res)=>{}) => {
  const app = getApp()
  if(wx.getStorageSync('jwt')){
    wx.request({
      url: urlhead+url,
      data: postdata,
      header: {"Authorization": wx.getStorageSync('jwt')},
      success(res) {
        callback(res.data)
      },
      fail(res){
        switch(res.statusCode){
          case 401:
            wx.redirectTo({
              url: '/pages/inputId/inputId',
            })
            break
          case 403:
            failcallback(res)
            break
        }
      },
      method:"POST"
    });
  }
  else{
    wx.redirectTo({
      url: '/pages/inputId/inputId',
    })
  }
}