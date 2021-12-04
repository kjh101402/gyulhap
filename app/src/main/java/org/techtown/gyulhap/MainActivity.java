package org.techtown.gyulhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView gameRuleImage;
    private boolean rule_visible = false;
    private Button singlePlayButton;
    private Button cpuPlayButton;
    private Button ruleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        gameRuleImage =findViewById(R.id.ruleImage);
        singlePlayButton = findViewById(R.id.singleplay);
        cpuPlayButton = findViewById(R.id.vsCPU);
        ruleButton = findViewById(R.id.ruleButton);
        setListner();
    }

    // 게임 설명 이미지를 보여주는 함수
    private void showImage(){
        if(rule_visible){
            gameRuleImage.setVisibility(View.INVISIBLE);
            rule_visible = false;
        }
        else{
            gameRuleImage.setVisibility(View.VISIBLE);
            rule_visible = true;
        }
    }

    //리스너 만들고 설정하는 함수
    public void setListner(){
        View.OnClickListener Listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.singleplay:
                        Intent intent1 = new Intent(getApplicationContext(), SinglePlay.class);
                        startActivity(intent1);
                        break;
                    case R.id.vsCPU:
                        Intent intent2 = new Intent(getApplicationContext(), CpuSetting.class);
                        startActivity(intent2);
                        break;
                    case R.id.ruleButton:
                        showImage();
                        break;
                }
            }
        };

        singlePlayButton.setOnClickListener(Listner);
        cpuPlayButton.setOnClickListener(Listner);
        ruleButton.setOnClickListener(Listner);
    }
}