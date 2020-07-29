const urlhead = "http://localhost:8888"

export const PostRequest = (url, postdata, callback, failcallback = (res)=>{}) => {
  const app = getApp()
  if(wx.getStorageSync('jwt')){
    wx.request({
      url: urlhead+url,
      data: postdata,
      header: {"Authorization": wx.getStorageSync('jwt')},
      success(res) {
        switch(res.statusCode){
          case 401:
            var pages = getCurrentPages()
	          var currentPage = pages[pages.length - 1]
            var url =  currentPage.route 
	          if(url == "pages/inputId/inputId"){
              return;
            }
            else{
              wx.redirectTo({
                url: '/pages/inputId/inputId',
              })
            }
            break
          case 403:
            failcallback(res)
            break
          case 200:
            callback(res.data)
            break
        }
      },
      fail(res){
        failcallback()
      }
      method:"POST"
    });
  }
  else{
    wx.redirectTo({
      url: '/pages/inputId/inputId',
    })
  }
}