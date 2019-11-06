// pages/Homework/Homework.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseName:'',
    titleName:'',
    contentName:'',
    remarkName:'',
    cid:'',
    date: '2018-12-25',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var data = wx.getStorageSync('cid');
    this.setData({
      cid:data
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },


  /**
   * 
   * 生命周期函数--监听
   * 页面隐藏
   */

  onHide: function () {


  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  courseNameInput: function (e) {
    this.setData({
      courseName: e.detail.value
    })
  },

  remarkNameInput: function (e) {
    this.setData({
      remarkName: e.detail.value
    })
  },
  titleNameInput: function (e) {
    this.setData({
      titleName: e.detail.value
    })
  },
  contentNameInput: function (e) {
    this.setData({
      contentName: e.detail.value
    })
  },

  orderMeeting: function (options) {
    var courseName = this.data.courseName;
    console.log(courseName)
    var titleName = this.data.titleName;
    console.log(titleName)
    var contentName = this.data.contentName;
    console.log(contentName)
    var remarkName = this.data.remarkName;
    console.log(remarkName)
    wx.request({
      url: 'http://localhost:8080/task/newTask',
      data: {
        cid: this.data.cid,
        content: this.data.contentName,
        remarks: this.data.remarkName,
        subject: this.data.courseName,
        time: this.data.date
      },
      method: 'get',//定义传到后台接受的是post方法还是get方法
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log("调用API成功");
      },
      fail: function (res) {
        console.log("调用API失败");
      }
    })
   
   wx.navigateTo({
     url: '../success/success',
   })
  },
  DateChange(e) {
    this.setData({
      date: e.detail.value
    })
    console.log(this.data.date);
  },
})