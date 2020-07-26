const urlhead = "http://localhost:8888"
const app = getApp()
export const PostRequest = (url, postdata, callback) => {
  wx.request({
    url: urlhead+url,
    data: postdata,
    header: {"Cookie": wx.getStorageSync('cookies')},
    success(res) {
      callback(res.data);
    },
    method:"POST"
  });
}