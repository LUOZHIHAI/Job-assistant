// pages/join/join.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    className:'',
    keyName:''
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
  classNameInput: function (e) {
    this.setData({
      className: e.detail.value
    })
  },
  keyNameInput: function (e) {
    this.setData({
      keyName: e.detail.value
    })
  },
  orderMeeting: function (options) {
    var className = this.data.className;
    console.log(className)
    var keyName = this.data.keyName;
    console.log(keyName)
    wx.request({
      url: 'http://localhost:8080/class/join',
      //定义传到后台的数据
      data: {
        className: this.data.className,
        secretKey: this.data.keyName,
        mid: wx.getStorageSync('studentId')
      },
      method: 'get',//定义传到后台接受的是post方法还是get方法
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if(res.data == "ok"){
          wx.navigateTo({
            url: '../success/success',
          })
        }
        else{
          wx.navigateTo({
            url: '../error/error',
          })
        }
      },
      fail: function (res) {
        console.log("调用API失败");
      }
    })
  }
})