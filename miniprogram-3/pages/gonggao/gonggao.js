var app = getApp()
Page({
  data: {

    cid:"",

    navbar: ['班级作业', '班级公告'],
    currentTab: 0,
    titles:[
      {tid:"1", title: "高数", Utime: "10月3日",state:"已完成",color:"green"},
      { tid: "2", title: "离散数学课后习题", Utime: "10月4日", state: "已完成", color: "green"},
      { tid: "3", title: "线性代数第三章", Utime: "10月5日", state: "未完成", color: "red"},
      { tid: "4", title: "操作系统实验", Utime: "10月6日", state: "未完成", color: "red"}
    ],


   
    message:"今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。今晚断网前把作业交齐,过期不候。",

    Rtime:"2019-10-11",
    state: false,
    hang:["red",false],
    com:["green",true]
  
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
    },



     onLoad: function (options) {
       this.setData({
         cid: options.cid
       })
       //console.log(this.data.cid)

    var thiss = this;
    wx.request({
      url: 'http://localhost:8080/class/getTaskNotice',
      //定义传到后台的数据
      data: {
        //从全局变量data中获取数据
        mid: wx.getStorageSync('studentId'),
        cid:this.data.cid
      },
      method: 'get',//定义传到后台接受的是post方法还是get方法
      header: {
        'content-type': 'application/json' // 默认值
      },

      success: function (res) {
        console.log("调用API成功");
        console.log(res.data[1][0].perState);

        var str = []

        for (var i = 0; i < res.data[1].length; i++) {
          var s = {
            tid: res.data[1][i].tid,
            title: res.data[1][i].title,
            Utime:res.data[1][i].time,
            state: res.data[1][i].perState == 0 ? "未完成" : "已完成",
            color: res.data[1][i].perState == 0 ? "red" : "green"
          }
          str.push(s)
        }
        thiss.setData({
          titles: str,
          message: res.data[0][0].content,
          Rtime: res.data[0][0].time
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

})
