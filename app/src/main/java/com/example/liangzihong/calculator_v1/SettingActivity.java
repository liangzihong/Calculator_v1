package com.example.liangzihong.calculator_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Liang Zihong on 2018/10/15.
 */

public class SettingActivity extends AppCompatActivity {

    private Button toNormal;
    private Button toRed;
    private Button toBlue;
    private Button toGreen;
    private Button toPurple;

    private Button toLight;
    private Button toDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.onActivityCreateTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);

        init();
    }


    public void init(){

        toNormal=(Button)findViewById(R.id.toNormal_button);
        toRed=(Button)findViewById(R.id.toRed_button);
        toBlue=(Button)findViewById(R.id.toBlue_button);
        toGreen=(Button)findViewById(R.id.toGreen_button);
        toPurple=(Button)findViewById(R.id.toPurple_button);
        toLight=(Button)findViewById(R.id.toLight_button);
        toDark=(Button)findViewById(R.id.toDark_button);

    }

    public void onClick(View v){
        Util.secondActivity=this;
        switch (v.getId()){
            case R.id.toNormal_button:
                Util.changeColorTheme(COLOR.NORMAL);
                break;
            case R.id.toBlue_button:
                Util.changeColorTheme(COLOR.BLUE);
                break;
            case R.id.toRed_button:
                Util.changeColorTheme(COLOR.RED);
                break;
            case R.id.toGreen_button:
                Util.changeColorTheme(COLOR.GREEN);
                break;
            case R.id.toPurple_button:
                Util.changeColorTheme(COLOR.PURPLE);
                break;
            case R.id.toLight_button:
                Util.changeLightTheme(MODE.LIGHT);
                break;
            case R.id.toDark_button:
                Util.changeLightTheme(MODE.DARK);
                break;
        }
    }

}
