/**
 * HookDB    2019-05-24
 * Copyright (c) 2019 YiYeHuDong Co.ltd. All right reserved.
 **/
package com.demo.xposed.hook;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 *@desc
 *@quthor tangminglong
 *@since 2019-05-24
 */
public class HookDB {

    private static final String TAG = HookDB.class.getSimpleName();
    private static HookDB instance;
    public static HookDB getInstance() {
        if (instance == null){
            synchronized (HookDB.class){
                if (instance == null){
                    instance= new HookDB();
                }
            }
        }
        return instance;
    }

    public void hook(XC_LoadPackage.LoadPackageParam loadPackageParam){
        try {
            Class<?> aClass = loadPackageParam.classLoader.loadClass("com.tencent.wcdb.database.SQLiteDatabase");
            XposedHelpers.findAndHookMethod(aClass, "insert", String.class, String.class, ContentValues.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.e(TAG,"InsertDBLOG");
                    Object arg = param.args[10];
                }
            });

            XposedHelpers.findAndHookMethod(aClass, "update", String.class, ContentValues.class, String.class, String[].class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.e(TAG,"UpdateDBLOG");
                    Object arg = param.args[10];
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}