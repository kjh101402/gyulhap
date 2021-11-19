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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        gameRuleImage =findViewById(R.id.ruleImage);
        
        //싱글플레이 버튼 클릭시 액티비티 전환
        Button singlePlayButton = (Button) findViewById(R.id.singleplay);
        singlePlayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SinglePlay.class);
                startActivity(intent);
            }
        });
    }

    //게임방법 클릭시 게임방법 이미지 띄우고 없앨 수 있다.
    public void ruleButtonClicked(View v){
        changeImage();
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

    public void singlePlayButtonClicked(View v){
        setContentView(R.layout.single_play);
    }
}