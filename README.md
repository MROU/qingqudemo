# qingqudemo
模仿轻趣的首页的demo，主要解决推动上滑问题

**DEMO 演示：**

<img src="https://github.com/MROU/qingqudemo/blob/master/app/src/main/res/mipmap-hdpi/demo_gif.gif" width="50%" height="50%">

**简要描述：**

那天看到轻趣的首页实现起来感觉非常的有趣，它是要在一个fragment里面要实现viewpage，实现RecyclerView悬浮条，同时还要支持当滑动到顶部时RecyclerView
头部的滚动悬浮，如果只是前两部分可能和大部分应用首页都差不多，第三部分的难点主要在于需要动态设置头部高度，如果要实现RecyclerView头部滚动，我们只需要给
RecyclerView添加一个header即可，但是如果需要它在顶部变成悬浮，我这里用了一个取巧的方法，在包裹viewpager的fragment的布局里面添加一个布局rl_quewan
覆盖在viewpager上面，当RecyclerView的头部滚动到顶部时，将RecyclerView的头部隐藏，出现布局rl_quewan

**开源：**

1. Retrofit
1. Glide
1. BottomNavigationView
1. Badge

**关于我：**

喜欢用技术的方式去解决问题，深度认同MVP的创业模式，可以搭建android app，熟悉js，如最近的小程序，在生活方面，喜欢尝试不同技能，是个彻底的不正经热血青年。

微博：https://weibo.com/2415138442/profile

自频道：http://i.youku.com/startplan

个人网站：http://www.startplan.cn/

微信公众号：ncistedu


开发的插件： https://addons.mozilla.org/zh-CN/firefox/addon/photos-download/?src=userprofile



**声明:**

这里使用了轻趣的api，使用网络抓取获得，原作公司拥有所有权利。本程序仅供测试学习，用于其他用途所造成的纠纷与本人无关。

轻趣：https://www.qingqu.top/

**页面显示图：**

<img src="https://github.com/MROU/qingqudemo/blob/master/app/src/main/res/mipmap-hdpi/demo1.jpg" width="50%" height="50%">
<img src="https://github.com/MROU/qingqudemo/blob/master/app/src/main/res/mipmap-hdpi/demo2.jpg" width="50%" height="50%">
<img src="https://github.com/MROU/qingqudemo/blob/master/app/src/main/res/mipmap-hdpi/demo3.jpg" width="50%" height="50%">
<img src="https://github.com/MROU/qingqudemo/blob/master/app/src/main/res/mipmap-hdpi/demo4.jpg" width="50%" height="50%">

参考链接：
https://www.diycode.cc/topics/408
