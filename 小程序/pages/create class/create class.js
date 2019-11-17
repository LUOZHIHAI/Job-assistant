Page({

  /**
   * 页面的初始数据
   */
  data: {
    schoolName:'',
    collegeName:'',
    gradeName:'',
    majorName:'',
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
  
  schoolNameInput: function (e) {
    this.setData({
      schoolName: e.detail.value
    })
  },
  collegeNameInput: function (e) {
    this.setData({
      collegeName: e.detail.value
    })
  },
   gradeNameInput: function (e) {
    this.setData({
       gradeName: e.detail.value
    })
  },
  majorNameInput: function (e) {
    this.setData({
      majorName: e.detail.value
    })
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
    var schoolName = this.data.schoolName;
    console.log(schoolName)
    var collegeName = this.data.collegeName;
    console.log(collegeName)
    var gradeName = this.data.gradeName;
    console.log(gradeName)
    var majorName = this.data.majorName;
    console.log(majorName)
    var className = this.data.className;
    console.log(className)
    var keyName = this.data.keyName;
    console.log(keyName)
    wx.navigateTo({
      url: '../succeed1/succeed1',
    })
  }

  

})