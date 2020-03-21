依赖配置：implementation com.bkjf:bkjfpush_android:1.0.2-SNAPSHOT;

如需使用umpush
Application  添加如下内容
UMConfigure.init(this, "5b179d87f29d986e1700003a", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "180c92b3b660c4ed849f9dca34b88476");
//PushSDK初始化(如使用推送SDK，必须调用此方法)
initUpush("2882303761517807389", "0MFdlVgsXptzXVlw80ifNg==","","");

private void initUpush(String mipushAppId, String mipushAppKey,
                           String meizupushAppId, String meizupushAppKey) {
        BKJFUMPushHepler.getInstance().init(this, BuildConfig.APPLICATION_ID, mipushAppId, mipushAppKey, meizupushAppId, meizupushAppKey );
    }

xml 配置文件
<meta-data
            android:name="UMENG_ABPPKEY"
            android:value="key(对应key)" />

        <meta-data
            android:name="UMENG_MESSAGE_SECRET"
            android:value="message_secret(对应message_secret)" />


如需修改通知栏样式，如图标文字等，请修改 BKJFUmengNotificationService 及 BKJFUMNotificationService

打包时，需要把jniLibs 里面的so 文件复制到主工程下面，否则主工程打出来的包里没有对应的so 文件，会导致注册失败
# IMPush
