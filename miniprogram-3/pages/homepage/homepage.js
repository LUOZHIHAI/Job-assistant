// pages/homepage/homepage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

    class: [
      {cid:1,title:"测试班级"},
      ], 
    str: []

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var thiss = this;
    wx.request({
      url: 'http://localhost:8080/class/getAllClass',
      //定义传到后台的数据
      data: {
        //从全局变量data中获取数据
        mid: wx.getStorageSync('studentId')
      },
      method: 'get',//定义传到后台接受的是post方法还是get方法
      header: {
        'content-type': 'application/json' // 默认值
      },
      
      success: function (res) {
        console.log("调用API成功");
        console.log(res.data[0].college + res.data[0].major + res.data[0].classNum + "班");
       
        var str = []

        for(var i = 0; i<res.data.length; i++ ){
          var s = {
            cid: res.data[i].cid,
            title: res.data[i].college + res.data[i].major + res.data[i].classNum + "班"
          }
          str.push(s)      
        }
        thiss.setData({
          class: str
        })
       
        if (res.data.msg == "ok") {

        }
        else {

        }
      },
      fail: function (res) {
        console.log("调用API失败");
      }
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
    this.onLoad()
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
  goToCreate(e){
    wx.navigateTo({
      url: '../create class/create class',
    })
  }
})