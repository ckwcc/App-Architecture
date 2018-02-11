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
 
# 4.丰富的工具类
  使用AndroidUtilCode框架，该第三方框架满足日常app开发的各种工具类，当然也可以将这个框架中的各种工具类copy下来，放在自己的项目中
  
# 5.使用统一的风格
  (一)在基类中，使用统一的toolbar，便于头部管理，可以在继承的Activity中轻松地设置。
  
  (二)同时在BaseActivity和BaseFragment中实现沉浸式状态栏，满足不同安卓版本的需求。
  
  (三)MVP模式使用统一的Presenter和View。
  
  (四)使用统一的bottomSheetDialogFragment,通过实现基类，轻松实现自定义从底部弹出的Dialog。
  
# 6.数据展示
  (一)集成第三方框架，轻松实现各种酷炫的刷新头，刷新尾。可以配合FloatingActionButton使用。
  
  (二)几行代码搞定Banner轮播图。
  
# 7.权限申请
  几行代码搞定各种权限申请，适配 Android M
  
# 8.实现各种小功能
  (一)一键实现主题换肤功能，切换正常模式和夜间模式
  
  (二)自定义电话界面显示
  
  (三)PhotoView的简单使用，个性化图片展示
  
  (四)对普通图片，例如拍照的，添加图片处理功能，例如图片模糊，加滤镜,图片特效等
  
  (五)使用调用camera2 拍照

  
# 9.视频播放
  自定义实现视频播放功能：目前有三种视频播放方式：基于ijkplayer的视频播放器、gsy视频播放器、原生视频播放器
