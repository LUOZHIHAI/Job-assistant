//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
    
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },

 onArticleClick: function () {
   console.log(this.data.userInfo);
   wx.setStorageSync('userInfo', this.data.userInfo);
   wx.request({
     url: 'http://localhost:8080/user/getName',
     //定义传到后台的数据
     data: {
       //从全局变量data中获取数据
       name: this.data.userInfo.nickName,
     },
     method: 'get',//定义传到后台接受的是post方法还是get方法
     header: {
       'content-type': 'application/json' // 默认值
     },
     success: function (res) {
       console.log("调用API成功");
       console.log(res.data.mid);
       wx.setStorageSync('studentId', res.data.mid)
       if (res.data.msg == "ok") {
         wx.showToast({
           title: '登陆成功',
         })
       }
       else {
          
       }
     },
     fail: function (res) {
       console.log("调用API失败");
     }
   })
  }

})
