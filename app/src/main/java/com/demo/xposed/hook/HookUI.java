package com.demo.xposed.hook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.Iterator;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookUI {
    private static final String TAG = HookUI.class.getSimpleName();
    public static void hook(final XC_LoadPackage.LoadPackageParam loadPackageParam){
        XposedHelpers.findAndHookMethod("android.app.Activity", loadPackageParam.classLoader, "startActivity", Intent.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                super.beforeHookedMethod(param);
                if (param.args[0] instanceof Intent) {
                    Intent intent = (Intent) param.args[0];
                    Bundle extras = intent.getExtras();
                    if (extras == null) return;
                    Iterator<String> iterator = extras.keySet().iterator();
                    StringBuilder result = new StringBuilder();
                    result.append(param.thisObject.getClass().getName());
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        result.append("key:").append(next).append(",value:").append(extras.get(next)).append("\n");
                    }


                    Log.e(TAG, "Activity:" + result.toString());
                }
            }

        });

        //打印出adapter的类名（BaseAdapter为抽象类不能hook）
        XposedHelpers.findAndHookMethod("android.widget.ListView", loadPackageParam.classLoader, "setAdapter", ListAdapter.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.e(TAG,"adapter类名："+param.args[0].getClass().getName());
            }
        });

        //对话列表ListView数据变化
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.conversation.h", loadPackageParam.classLoader, "notifyDataSetChanged", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.e(TAG,"conversation数据变更");
                Object arg = param.args[10];
            }
        });

        //窗口对话ListView数据变化
        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.a.a", loadPackageParam.classLoader, "notifyDataSetChanged", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.e(TAG,"chatting数据变更");
                Object arg = param.args[10];
            }
        });

    }
}
