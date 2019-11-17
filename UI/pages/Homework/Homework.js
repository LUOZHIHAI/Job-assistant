// pages/Homework/Homework.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseName:'',
    className:'',
    titleName:'',
    contentName:'',
    remarkName:'',
    endingtimeNme:''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
   * 生命周期函数--监听页面隐藏
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

  classNameInput: function (e) {
    this.setData({
      className: e.detail.value
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
  endingtimeNameInput: function (e) {
    this.setData({
      endingtimeName: e.detail.value
    })
  },
  orderMeeting: function (options) {
    var courseName = this.data.courseName;
    console.log(courseName)
    var className = this.data.className;
    console.log(className)
    var titleName = this.data.titleName;
    console.log(titleName)
    var contentName = this.data.contentName;
    console.log(contentName)
    var remarkName = this.data.remarkName;
    console.log(remarkName)
    var endingtimeName = this.data.endingtimeName;
    console.log(endingtimeName)
   
   wx.navigateTo({
     url: '../succeed/succeed',
   })
  }
})