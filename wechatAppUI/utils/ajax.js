const urlhead = "http://localhost:8888"
export const PostRequest = (url, postdata, callback) => {
  wx.request({
    url: urlhead+url,
    data: postdata,
    success(res) {
      callback(res.data);
    }
  });
}