# NewsApp-Kotlin
A news app using Kotlin with BiliBili style

本应用采用kotlin语言编写，模仿了《腾讯新闻》及《哔哩哔哩》客户端。

打开app，进入MainActivity。

![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010921368.png)

首先，app对状态栏和导航栏（虚拟按键）做了沉浸式优化。

自上而下看，

首先是一个AppBarLayout，内含用户头像、搜索框和两个装在toolbar中的按钮。

当点击搜索框时，会弹出SearchActivity，你可以点击这里进行新闻搜索。

| ![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010927534.png) | ![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010927932.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

接下来是一行TabLayout，当新闻种类超出屏幕范围时，它可以横向滚动查看其他种类的新闻。

![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010925446.png)

接下来是一个news_fragment，包含SwipeRefreshLayout-**RecyclerView**结构以实现可下拉刷新的滑动新闻列表，可以点击列表项弹出ContentActivity以查看新闻。

![Screenshot_20220331_041717](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010925109.png) 

![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010925182.png)

ContentActivity中加入了home按钮（左上角箭头）可以在递归刷新闻过深时（要按很多次返回键）直接finish该Activity。

接着是一个自定义底部导航栏bottomNav，该导航栏会在启动时直接加载至fragment_home，可通过点击其他item切换至其他fragment。

| ![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010928597.png) | ![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010928540.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

因作业主要内容是实现一个新闻列表，所以其他两个fragment还没怎么做，目前能点一点按钮。

 

注：因京东API每日获取新闻不超过1000条，每次启动APP会获取70条新闻，所以若一天中打开了15次及以上，我写了一个catch将其捕获后Toast。

![](https://cdn.jsdelivr.net/gh/WanpengXu/myPicGo/img/202204010929922.jpg)
