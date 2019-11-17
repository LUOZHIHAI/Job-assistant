var app = getApp()
Page({
  data: {
    navbar: ['班级作业', '班级公告'],
    currentTab: 0,
  },

  // 导航切换监听
  navbarTap: function (e) {
    console.debug(e);
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
  },

})
