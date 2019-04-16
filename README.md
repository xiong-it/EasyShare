# EasyShare
一个Android社会化分享脚手架，集成微信，qq，微博分享。

# 作用 
利用抽象工厂，工厂方法，桥接模式搭配Java泛型封装的一个简单易扩展的分享组件，无法代替ShareSDK等商业化SDK使用，仅供开发交流探讨，不建议线上项目直接使用，可供开发者参考扩展。   

# 架构
![](https://raw.githubusercontent.com/xiong-it/EasyShare/master/EasyShare_UML.png)  
 * IShareContent：分享的内容接口定义，具有share行为，通过桥接模式将share行为交给ISharePlatform执行  
 * ISharePlatform：承接分享内容的目标平台，实际分享行为的对象接口  
 * IShareModel：分享内容的属性封装，比如标题，图片地址，链接等  
 * IShareDataFactory：根据IShareModel生产分享ISharePlatform平台所需的数据，其实现类抽象工厂AbsShareDataFactory定义了生产微信，朋友圈，QQ，微博4个产品族所需的数据函数 

# 结构 
![](https://raw.githubusercontent.com/xiong-it/EasyShare/master/EasyShare_Struct.png)  
 * callback-回调接口定义 
 * factory-工厂类，顶级抽象工厂定义生产的所有平台的分享数据函数，拆分维度：以分享分平台为产品族，以分享内容类型为产品线。  
 * model-模型类，顶级接口为IShareModel，分享数据所需要的数据模型，在此处新增需要分享的数据模型。    
 * platform-分享目标平台，ISharePlatform是目标平台接口，所有衍生平台需实现该接口， 当接入新的分享平台时，需要在此处进行扩展。  
 * type-分享数据类型的封装，分享的入口，顶级接口IShareContent，持有Share行为。   
 * ui-界面相关类，比如IShareDialog.java,分享选择弹窗的接口类，需在项目中使用的弹窗中实现该接口。  
 * wxapi-包名必须是项目包名，此处只做演示参考用。  
 
# 集成步骤
 1. clone该仓库  
 2. 参考DemoActivity  
  
# 分享代码举例  
以位图分享举例（场景：二维码图片分享）
```
// 1. 分享数据Model组装
IShareModel model = new BitmapShareModel() {
    @Override
    public Bitmap shareBitmap() {
            return getShareBitmap();
    }
};

// 2. 实例化分享数据类型
IShareContent bitmapShare = ShareContentFactory.newShareContent(platform, model);

// 3. 将对应数据分享出去
bitmapShare.share(this.activity, new OnShareResultCallback() {
    @Override
    public void onShareCancel(ISharePlatform platform, IShareContent type) {
        // 分享取消
    }

    @Override
    public void onShareFailed(ISharePlatform platform, IShareContent type, int errCode, String errMsg) {
        // 分享失败
    }

    @Override
    public void onShareSuccess(ISharePlatform platform, IShareContent type) {
        // 分享成功
     }
});
```  
  
# 当你需要新增分享类型
 1. 扩展IShareModel接口，新增实现类  
 2. 扩展IShareContent接口，新增实现类  
 3. 扩展AbsShareDataFactory抽象工厂产品线，新增子类生产对应数据进行分享 
  
# 当你需要新增分享平台 
 1. 引入对应平台依赖libs   
 2. 扩展ISharePlatform接口，新增一个实现类   
 3. 扩展AbsShareDataFactory抽象类产品族（新增抽象函数）及其子类实现     
 4. 修改IShareDialog子类实现，新增点击事件   
