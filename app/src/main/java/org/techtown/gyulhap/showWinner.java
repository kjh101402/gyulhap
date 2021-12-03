package org.techtown.gyulhap;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class showWinner extends AppCompatActivity {

    TextView pst;
    TextView cst;
    TextView message;
    TextView winDraw;
    Button jMain;
    Button regame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_winner);

        initialize();

        Intent intent = getIntent();
        int playerScore = intent.getIntExtra("PlayerScore", 0);
        int cpuScore = intent.getIntExtra("CpuScore", 0);
        pst.setText(String.valueOf(playerScore));
        cst.setText(String.valueOf(cpuScore));
        SetListner();

        if (playerScore > cpuScore) {
            winDraw.setText("WIN");
            message.setText("축하합니다. 당신의 승리입니다.");
        } else if (playerScore == cpuScore) {
            winDraw.setText("DRAW");
            message.setText("비겼습니다.");
        } else {
            winDraw.setText("LOSE");
            message.setText("저런. 당신의 패배입니다.");
        }
    }

    public void initialize(){
        pst = findViewById(R.id.PST);
        cst = findViewById(R.id.CST);
        message = findViewById(R.id.message);
        winDraw = findViewById(R.id.windraw);
        jMain = findViewById(R.id.goMain);
        regame = findViewById(R.id.reGame);
    }

    public void SetListner() {
        View.OnClickListener Listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.goMain:
                        Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                        goToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToMain);
                        finish();
                        break;
                    case R.id.reGame:
                        Intent replay = new Intent(getApplicationContext(), CpuSetting.class);
                        startActivity(replay);
                        finish();
                        break;
                }

            }
        };
        regame.setOnClickListener(Listner);
        jMain.setOnClickListener(Listner);
    }
}
