# App-Architecture(持续更新中)
# 由目前一些Android 流行框架搭建而成的App框架

# 1.基于MVP+Retrofit+Rxjava+Dagger2
  借鉴谷歌官方android-architecture-todo-mvp-dagger这个demo的mvp与dagger写法，不用需要向每一个Activity执行注入的初始化的重复操作，直接在整个app中定义好Activity的注入管理


# 2.使用NavigationTabBar显示底部导航栏
  灵活的使用NavigationTabBar，可以自定义显示底部导航栏的样式，自由地更改整体、每个item的背景色，灵活的设置item的title和badge的字体、颜色以及是否显示以及显示模式

# 3.使用Butterknife绑定控件
  彻底告别findviewbyid
