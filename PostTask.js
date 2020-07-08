// pages/PostTask/PostTask.js

function formatTimeTamp(timeTamp){
  var time = new Date(timeTamp);
  var date = ((time.getFullYear())  + "-" +
              (time.getMonth() + 1) + "-" +
              (time.getDate())
             );
  return date;
}
Page({
  data: {
    currentDate: new Date().getTime(),
    currentDateString: formatTimeTamp(new Date().getTime()),
    minDate: new Date().getTime(),
    formatter(type, value) {
      if (type === 'year') {
        return `${value}年`;
      } else if (type === 'month') {
        return `${value}月`;
      }
      return value;
    },
    show: false,
    title: null,
    text: null,
  },

  onTitleChange(event){
    this.setData({
      title: event.detail,
    })
  },

  onChooseTime(event) {
    this.setData({
      currentDate: event.detail,
      currentDateString: formatTimeTamp(event.detail),
      show: false,
    });

  },

  showPopup() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },

  confirmText(event){
    this.setData({text: event.detail.value});
  },

  onSubmit() {

  },

  afterRead(event) {
    
  },
})