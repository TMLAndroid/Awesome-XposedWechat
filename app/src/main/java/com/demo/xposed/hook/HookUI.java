package com.demo.xposed.hook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookUI {
    private static final String TAG = HookUI.class.getSimpleName();
    public static void hook(XC_LoadPackage.LoadPackageParam loadPackageParam){
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

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
    }
}
