const urlhead = "http://54.234.98.178:8301"
const searchurl = urlhead+"/search"
const noticeurl = urlhead+"/notice"
const normalurl = urlhead+"/core"

export const PostRequest = (url, postdata, callback, failcallback = (res) => {}) => {
  Request(normalurl, url, postdata, callback)
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
      header: {"Authorization":"Bearer "+wx.getStorageSync('jwt'),
      'Content-Type': "application/json"
    },
      success(res) {
        switch(res.statusCode){
          case 200:
            callback(res.data)
            break
          case 401:
          case 403:
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
          default:
            failcallback()
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
