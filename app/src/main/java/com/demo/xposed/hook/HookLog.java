package com.demo.xposed.hook;

import android.os.Looper;
import android.os.Process;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * hook微信的日志信息（自造异常）
 */
public class HookLog {

    private static final String TAG = HookLog.class.getSimpleName();
         public static void hook(final XC_LoadPackage.LoadPackageParam loadPackageParam){

             Class<?> aClass1 = null;
             try {
                 aClass1 = loadPackageParam.classLoader.loadClass("com.tencent.mm.sdk.platformtools.y");
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
             if (aClass1 != null){

                    XposedHelpers.findAndHookMethod(aClass1, "v", String.class, String.class,  Object[].class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            StringBuffer sb = new StringBuffer();
                            if (param.args[2] !=null) {
                                Object[] par = (Object[]) param.args[2];
                                for (int i = 0; i < par.length; i++) {
                                    if (par[i] != null) {
                                        sb.append(par[i].toString() + ",");
                                    }
                                }
                            }
                            String format = param.args[1] +",参数："+sb.toString();
                            Log.e(TAG,"tagV:"+param.args[0]+",context:******"+format+"******>>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+ Looper.getMainLooper().getThread().getId()+"<<<<<<");
                            try {

                                Log.e(TAG,"tagV:"+param.args[10]);//自造异常
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            super.beforeHookedMethod(param);

                        }
                    });

                    XposedHelpers.findAndHookMethod(aClass1, "i", String.class, String.class,
                            Object[].class,new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    StringBuffer sb = new StringBuffer();
                                    if (param.args[2] !=null) {
                                        Object[] par = (Object[]) param.args[2];
                                        for (int i = 0; i < par.length; i++) {
                                            if (par[i] != null) {
                                                sb.append(par[i].toString() + ",");
                                            }
                                        }
                                    }
                                    String format = param.args[1] +",参数："+sb.toString();
                                    Log.e(TAG,"tagI:"+param.args[0]+",context:******"+format+"******>>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+Looper.getMainLooper().getThread().getId()+"<<<<<<");
                                    try {

                                        Log.e(TAG,"tagI:"+param.args[10]);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    super.beforeHookedMethod(param);

                                }
                            });



                    XposedHelpers.findAndHookMethod(aClass1, "d", String.class, String.class,  Object[].class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            StringBuffer sb = new StringBuffer();
                            if (param.args[2] !=null) {
                                Object[] par = (Object[]) param.args[2];
                                for (int i = 0; i < par.length; i++) {
                                    if (par[i] != null) {
                                        sb.append(par[i].toString() + ",");
                                    }
                                }
                            }
                            String format = param.args[1] +",参数："+sb.toString();
                            Log.e(TAG,"tagD:"+param.args[0]+",context:******"+format+"******>>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+Looper.getMainLooper().getThread().getId()+"<<<<<<");Log.d(TAG,"tag:"+param.args[0]+"****context*****:"+format+">>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+Looper.getMainLooper().getThread().getId()+"<<<<<<");
                            try {

                                Log.e(TAG,"tagD:"+param.args[10]);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            super.beforeHookedMethod(param);

                        }
                    });


                    XposedHelpers.findAndHookMethod(aClass1, "w", String.class, String.class,  Object[].class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            StringBuffer sb = new StringBuffer();
                            if (param.args[2] !=null) {
                                Object[] par = (Object[]) param.args[2];
                                for (int i = 0; i < par.length; i++) {
                                    if (par[i] != null) {
                                        sb.append(par[i].toString() + ",");
                                    }
                                }
                            }
                            String format = param.args[1] +",参数："+sb.toString();
                            Log.e(TAG,"tagW:"+param.args[0]+",context:******"+format+"******>>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+Looper.getMainLooper().getThread().getId()+"<<<<<<");
                            try {

                                Log.e(TAG,"tagW:"+param.args[10]);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            super.beforeHookedMethod(param);

                        }
                    });


                    XposedHelpers.findAndHookMethod(aClass1, "e", String.class, String.class,  Object[].class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            StringBuffer sb = new StringBuffer();
                            if (param.args[2] !=null) {
                                Object[] par = (Object[]) param.args[2];
                                for (int i = 0; i < par.length; i++) {
                                    if (par[i] != null) {
                                        sb.append(par[i].toString() + ",");
                                    }
                                }
                            }
                            String format = param.args[1] +",参数："+sb.toString();
                            Log.e(TAG,"tagE:"+param.args[0]+",context:******"+format+"******>>>>>>>>>tid:"+ Process.myTid()+",pid:"+Process.myPid()+",currentThreadId:"+Thread.currentThread().getId()+",MainThreadId:"+Looper.getMainLooper().getThread().getId()+"<<<<<<");
                            try {

                                Log.e(TAG,"tagE:"+param.args[10]);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            super.beforeHookedMethod(param);

                        }
                    });
                }

        }
}
