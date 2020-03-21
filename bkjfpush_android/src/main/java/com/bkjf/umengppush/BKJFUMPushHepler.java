package com.bkjf.umengppush;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.bkjf.umengppush.push.BKJFUmengNotificationService;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.inapp.InAppMessageManager;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;


/**
 * 功能:
 * 作者: 张和彬
 * 日期: 2018/6/5
 * 备注:
 */
public class BKJFUMPushHepler {
    public static String TAG = BKJFUMPushHepler.class.getSimpleName();
    private static BKJFUMPushHepler instance = null;

    private BKJFUMPushHepler(){

    }

    public static BKJFUMPushHepler getInstance() {
        if (instance == null) {
            synchronized (BKJFUMPushHepler.class) {
                if (instance == null) {
                    instance = new BKJFUMPushHepler();
                }
            }
        }
        return instance;
    }

    public void init(final Context context,String packageName, String mipushAppId, String mipushAppKey,
             String meizupushAppId, String meizupushAppKey){
        InAppMessageManager.getInstance(context).setInAppMsgDebugMode(true);
        final PushAgent mPushAgent = PushAgent.getInstance(context);
        mPushAgent.setResourcePackageName(packageName);//自定义资源包名
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.i(TAG,"友盟推送注册成功,deviceToken:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG,"友盟推送注册失败,s:"+s+",s1:"+s1);
            }
        });

        if(MiPushRegistar.checkDevice(context)){
            Log.i(TAG,"注册小米推送通道");
            MiPushRegistar.register(context, mipushAppId, mipushAppKey);
        }else if(MzSystemUtils.isBrandMeizu()){
            Log.i(TAG,"注册魅族推送通道");
            MeizuRegister.register(context, meizupushAppId, meizupushAppKey);
        }else if(checkHuaweiDevice()){
            Log.i(TAG,"注册华为推送通道");
            HuaWeiRegister.register(context);
        }

        mPushAgent.setPushIntentServiceClass(BKJFUmengNotificationService.class);

    }

    private static boolean checkHuaweiDevice() {
        boolean var1 = false;
        String phoneBrand= Build.BRAND;
        if(phoneBrand.equalsIgnoreCase("huawei") || phoneBrand.equalsIgnoreCase("honor")) {
            var1 = true;
        }
        return var1;
    }

}
