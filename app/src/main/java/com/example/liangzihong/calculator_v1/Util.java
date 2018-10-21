package com.example.liangzihong.calculator_v1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Liang Zihong on 2018/10/15.
 */

enum COLOR{
    NORMAL,RED,BLUE,GREEN,PURPLE,Cyan,GRAY
}

enum MODE{
    LIGHT,DARK
}

public class Util {

    public static MODE mode = MODE.LIGHT;   //是否是日间模式
    public static COLOR color=COLOR.GRAY;
    public static boolean NeedToChange=false;

    public static AppCompatActivity firstActivity;
    public static AppCompatActivity secondActivity;


    //思想是：直接改变   secondActivity的颜色
    //但是当由  setting回到mainActivity时，mainActivity会发生 onStart，此时在对mainActivity进行颜色改变

    //改变颜色
    public static void changeColorTheme( COLOR changeColor){


        //只有输入颜色不一样时
        if( color!=changeColor){
            color = changeColor;
            changeTheme(secondActivity);
            NeedToChange=true;
        }
    }


    //改变日间夜间模式
    public static void changeLightTheme(MODE changeMode){
        if(mode!=changeMode){
            mode= changeMode;
            changeTheme(secondActivity);
            NeedToChange=true;
        }
    }


    //被调用时，会强制关掉 第二个activity，然后再重启
    public static void changeTheme(Activity activity){
        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

    //重启前要调用的函数 或 onStart
    public static void onActivityCreateTheme(Activity activity)
    {

        switch (color)
        {
            case NORMAL:
                if(mode==MODE.LIGHT){
                    activity.setTheme(R.style.AppTheme_Light);
                }

                else{
                    activity.setTheme(R.style.AppTheme_Dark);
                }

                break;
            case RED:
                if(mode==MODE.LIGHT){
                    activity.setTheme(R.style.AppTheme_Light_Red);
                }

                else{
                    activity.setTheme(R.style.AppTheme_Dark_Red);
                }
                break;

            case Cyan:
                if(mode==MODE.LIGHT){
                    activity.setTheme(R.style.AppTheme_Light_Cyan);
                }

                else{
                    activity.setTheme(R.style.AppTheme_Dark_Cyan);
                }
                break;

            case GREEN:
                if(mode==MODE.LIGHT){
                    activity.setTheme(R.style.AppTheme_Light_Green);
                }

                else{
                    activity.setTheme(R.style.AppTheme_Dark_Green);
                }
                break;

            case GRAY:
                if(mode==MODE.LIGHT){
                    activity.setTheme(R.style.AppTheme_Light_Gray);
                }

                else{
                    activity.setTheme(R.style.AppTheme_Dark_Gray);
                }
                break;
        }
        //如果是mainActivity，则把Need'T'oChange变回false
        if(activity.getClass() == MainActivity.class && NeedToChange==true)
            NeedToChange=false;

    }


}
