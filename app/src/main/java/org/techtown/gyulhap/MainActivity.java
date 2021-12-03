package org.techtown.gyulhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView gameRuleImage;
    boolean rule_visible = false;
    Button singlePlayButton;
    Button cpuPlayButton;
    Button ruleButton;

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


    private void changeImage(){
        if(rule_visible){
            gameRuleImage.setVisibility(View.INVISIBLE);
            rule_visible = false;
        }
        else{
            gameRuleImage.setVisibility(View.VISIBLE);
            rule_visible = true;
        }
    }

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
                        changeImage();
                        break;
                }
            }
        };

        singlePlayButton.setOnClickListener(Listner);
        cpuPlayButton.setOnClickListener(Listner);
        ruleButton.setOnClickListener(Listner);
    }
}