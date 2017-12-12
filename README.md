# App-Architecture(持续更新中)
# 由目前一些Android 流行框架搭建而成的App框架

# 1.基于MVP+Retrofit+Rxjava+Dagger2
  （一）借鉴谷歌官方android-architecture-todo-mvp-dagger这个demo的mvp与dagger写法，不用需要向每一个Activity执行注入的初始化的重复操作，直接在整个app中定义好Activity的注入管理，
  
  谷歌官方MVP+Dagger的demo：https://github.com/googlesamples/android-architecture/tree/todo-mvp-dagger/
  
  （二）通过封装好的网络请求工具类，发起网络请求更加简单，通过在具体的presenter的构造函数中注入即可使用。

# 2.使用NavigationTabBar显示底部导航栏
  灵活的使用NavigationTabBar，可以自定义显示底部导航栏的样式，自由地更改整体、每个item的背景色，灵活的设置item的title和badge的字体、颜色以及是否显示以及显示模式

# 3.使用Butterknife绑定控件
  彻底告别findviewbyid，使用注解就可以完成View的查找，简化了代码的书写
 
# 4.使用AndroidUtilCode框架
  该第三方框架满足日常app开发的各种工具类，当然也可以将这个框架中的各种工具类copy下来，放在自己的项目中
