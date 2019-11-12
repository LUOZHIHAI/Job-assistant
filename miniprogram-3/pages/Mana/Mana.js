// pages/Mana/Mana.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {


    Eclass:[
      { title: "测试班级1", cid: 1 },
      { title: "测试班级2", cid: 2 },
      { title: "测试班级3", cid: 3 },
    ],

    ccid: 0,

    Jclass: [
      { title: "测试班级1", cid: 1, checked: false},
      { title: "测试班级2", cid: 2, checked: false},
      { title: "测试班级3", cid: 3, checked: false},
      { title: "测试班级4", cid: 4, checked: false},
    ],

    checked:[],

    isAdministrators:true,
    isHidden: true,
    titleMsg: " ",
    inputHidden: false,
    cancleBtn: false,
    inputPlaceHolder: ""

  },


  onMyEvent: function (e) {
    var that = this;
    var strclass;
    console.log("e.detail :", e.detail)
    var arr = that.data.Eclass
    for (let i = 0; i < arr.length; i++) {
      console.log(arr[i].cid)
      console.log(that.data.ccid)
      if(arr[i].cid == that.data.ccid){
        arr[i].title = e.detail
        console.log(arr[i].title)
      }
    }

    // for (let i = 0; i < arr.length; i++) {
    //   console.log(arr[i].title)
    // }
        that.setData({
          isHidden: true,
          Eclass: arr
          // inputHidden: false
        })
  },
  showCompomentDialog: function (e) {
    console.log(e.currentTarget.id);
    var that = this;
    that.setData({
      ccid: e.currentTarget.id,
      isHidden: false,
      titleMsg: "请修改班级名称",
      //inputPlaceHolder: "请输入想要发送的内容",
      inputHidden: true,
      // cancleBtn: true,
    })
    //console.log(this.data.ccid);
  },

  del1(e) {
    console.log(e.currentTarget.id)
    var that = this
    var arr = []
    var a = 0
    for (let i = 0; i < that.data.Eclass.length; i++) {
      if (that.data.Eclass[i].title != e.currentTarget.id) {
        arr[a] = that.data.Eclass[i]
        a++
       }
    }

    that.setData({
      Eclass: arr
    })

  },

  del2(){
    var that = this
    var arr = []
    var a = 0
    for (let i = 0; i < that.data.Jclass.length; i++) {
      for (let j = 0; j < that.data.checked.length;j++)
        if (that.data.Jclass[i].cid == that.data.checked[j]) {
          break;
        } else if (j == that.data.checked.length - 1){
          arr[a] = that.data.Jclass[i]
          a++
        }
    }

    that.setData({
      Jclass: arr
    })

  },


  checkboxChange: function (e) {
    var that = this
    // console.log(e.detail.value)
    that.setData({
      checked: e.detail.value
    })
    //console.log(this.data.checked)
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


  // ListTouch触摸开始
  ListTouchStart(e) {
    this.setData({
      ListTouchStart: e.touches[0].pageX
    })
  },

  // ListTouch计算方向
  ListTouchMove(e) {
    this.setData({
      ListTouchDirection: e.touches[0].pageX - this.data.ListTouchStart > 0 ? 'right' : 'left'
    })
  },

  // ListTouch计算滚动
  ListTouchEnd(e) {
    if (this.data.ListTouchDirection == 'left') {
      this.setData({
        modalName: e.currentTarget.dataset.target
      })
    } else {
      this.setData({
        modalName: null
      })
    }
    this.setData({
      ListTouchDirection: null
    })
  },




})