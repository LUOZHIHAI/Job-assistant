var app = getApp()
Page({
  data: {
    navbar: ['班级作业', '班级公告'],
    currentTab: 0,
    titles:[
      { title: "高数", Utime: "10月3日"},
      { title: "离散数学课后习题", Utime: "10月4日" },
      { title: "线性代数第三章", Utime: "10月5日" },
      { title: "操作系统实验", Utime: "10月6日" }
    ],


   
    message:"今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。",

    Rtime:"2019-10-11"
  
  },


  swichNav: function (e) { 
    console.log(e);
     var that = this;
      if (this.data.currentTab === e.target.dataset.current)
        { 
          return false; 
        }
      else 
        {
           that.setData({
              currentTab: e.target.dataset.current,
               }) 
        } 
  },
  
    /**   * 导航页面显示2）   */  
    swiperChange: function (e) {
       console.log(e);
        this.setData({
           currentTab: e.detail.current, 
           }) 
    }

})
