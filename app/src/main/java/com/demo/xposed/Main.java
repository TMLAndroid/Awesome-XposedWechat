package com.demo.xposed;

import android.app.Application;
import android.content.Context;

import com.demo.xposed.hook.HookDB;
import com.demo.xposed.hook.HookLog;
import com.demo.xposed.hook.HookUI;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author tmlong
 * 注意：支持的微信版本为6.7.3
 */

public class Main implements IXposedHookLoadPackage {
    private static final String TAG = Main.class.getSimpleName();


    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {


        if (loadPackageParam.packageName.equals("com.tencent.mm") && loadPackageParam.processName.equals("com.tencent.mm")) {
            XposedHelpers.findAndHookMethod("android.content.ContextWrapper", loadPackageParam.classLoader, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    super.afterHookedMethod(methodHookParam);
                    if ( (methodHookParam.thisObject instanceof Application)) {
                    HookLog.hook(loadPackageParam);
                    HookUI.hook(loadPackageParam);
                    HookDB.getInstance().hook(loadPackageParam);
            }
        }
    });

        }
    }



}

