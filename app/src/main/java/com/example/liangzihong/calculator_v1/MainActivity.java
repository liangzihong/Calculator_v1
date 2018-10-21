package com.example.liangzihong.calculator_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import MODEL.Calculate_Model;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView upTv;
    private TextView downTv;


    private String upStr="";
    private String downStr="";
    boolean calculated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.onActivityCreateTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_activity);
        init();
    }

    /**
     * 用来更改夜间模式和背景颜色的
     */
    @Override
    protected void onStart(){
        super.onStart();
        if(Util.NeedToChange) {
            Util.changeTheme(this);
            Util.NeedToChange=false;
        }
    }


    //初始化
    public void init(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upTv=(TextView)findViewById(R.id.show_view_first);
        downTv=(TextView)findViewById(R.id.show_view_second);
    }



    //设置菜单的setting按钮
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    //对菜单项的事件响应
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent=new Intent(this,SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    public void onClick(View v){

        switch(v.getId()){
            case R.id.one_button:
                downStr= downStr+"1";
                break;

            case R.id.two_button:
                downStr= downStr+"2";
                break;

            case R.id.three_button:
                downStr= downStr+"3";
                break;

            case R.id.four_button:
                downStr= downStr+"4";
                break;

            case R.id.five_button:
                downStr= downStr+"5";
                break;

            case R.id.six_button:
                downStr= downStr+"6";
                break;

            case R.id.seven_button:
                downStr= downStr+"7";
                break;

            case R.id.eight_button:
                downStr= downStr+"8";
                break;


            case R.id.nine_button:
                downStr= downStr+"9";
                break;

            case R.id.zero_button:
                downStr= downStr+"0";
                break;

            case R.id.sin_button:
                downStr= downStr+"sin";
                break;

            case R.id.cos_button:
                downStr= downStr+"cos";
                break;

            case R.id.tan_button:
                downStr= downStr+"tan0";
                break;

            case R.id.leftBracket_button:
                downStr= downStr+"(";
                break;

            case R.id.rightBracket_button:
                downStr= downStr+")";
                break;

            case R.id.dot_button:
                downStr= downStr+".";
                break;

            case R.id.delete_button:
                downStr=downStr.substring(0,downStr.length()-1);
                break;

            case R.id.plus_button:
                downStr= downStr+"+";
                break;

            case R.id.minus_button:
                downStr= downStr+"-";
                break;

            case R.id.multiply_button:
                downStr= downStr+"*";
                break;

            case R.id.divide_button:
                downStr= downStr+"/";
                break;

            case R.id.equal_button:
                String ans=Calculate_Model.calculate(downStr);
                upStr=downStr;
                downStr=ans+"";
                calculated=true;
                break;

        }
        upTv.setText(upStr);
        downTv.setText(downStr);

        if(calculated==true) {
            upStr = "";
            downStr = "";
            calculated=false;
        }
    }

}
