const urlhead = "http://localhost:8888"
const searchurl = "http://localhost:8889"
const noticeurl = "http://localhost:8890"

export const PostRequest = (url, postdata, callback, failcallback = (res) => {}) => {
  Request(urlhead, url, postdata, callback)
}

export const SearchRequest = (url, postdata, callback, failcallback = (res) => {}) => {
  Request(searchurl, url, postdata, callback)
}

export const NoticeRequest = (url, postdata, callback, failcallback = (res) => {}) => {
  Request(noticeurl, url, postdata, callback)
}

export const Request = (head, url, postdata, callback, failcallback = (res)=>{}) => {
  if(wx.getStorageSync('jwt')){
    console.log(wx.getStorageSync('jwt'))
    wx.request({
      url: head+url,
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

export const PostRequestWithoutJwt = (url, postdata, callback, failcallback = (res)=>{}) => {
  wx.request({
    url: urlhead+url,
    data: postdata,
    method: "POST",
    success(res){
      if(res.statusCode == 200){
        callback(res.data)
      }
      else{
        failcallback(res)
      }
    },
    fail(res){
      failcallback(res)
    }
  })
}