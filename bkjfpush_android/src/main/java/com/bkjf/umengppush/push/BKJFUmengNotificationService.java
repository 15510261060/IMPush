package com.bkjf.umengppush.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.umeng.message.UmengMessageService;
import com.umeng.message.lib.R;

import org.android.agoo.common.AgooConstants;

public class BKJFUmengNotificationService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        Log.i("BKJFUmengNotificationService", "onMessage");
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        Intent intent1 = new Intent();
        intent1.setClass(context, BKJFUMNotificationService.class);
        intent1.putExtra("UmengMsg", message);
        intent1.putExtra("icon", R.drawable.bkjf_umeng_push_notification_default_small_icon);
        context.startService(intent1);
    }
}
