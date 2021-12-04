package org.techtown.gyulhap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class CpuSetting extends AppCompatActivity {

    private ArrayList<String> roundList;
    private Spinner roundSpin;
    private ArrayList<String> roundList2, roundList3;
    private Spinner roundSpin2, roundSpin3;
    private int setDifficult;
    private int setRound;
    private int setTurnCount;
    private int setBonusCount;
    private boolean playerTurn;
    private RadioGroup diffGroup;
    private RadioGroup firstGroup;
    private Button goBack;
    private Button gameStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu_setting);

        setRound = 10;
        setDifficult = 65;
        playerTurn = true;

        initialize();

        setListner();
    }

    // 뷰들 초기화
    public void initialize(){
        diffGroup = findViewById(R.id.diffGroup);
        firstGroup = findViewById(R.id.firstTurn);
        goBack = findViewById(R.id.goBack);
        gameStart = findViewById(R.id.gameStart);
        roundSpin = findViewById(R.id.roundSpin);
        roundSpin2 = findViewById(R.id.roundSpin2);
        roundSpin3 = findViewById(R.id.roundSpin3);
        roundList = new ArrayList<>();
        roundList2 = new ArrayList<>();
        roundList3 = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            roundList.add(String.valueOf(i));
        }
        for(int j = 5; j < 21; j++){
            roundList2.add(String.valueOf(j));
        }
        for(int k = 3; k < 11; k++){
            roundList3.add(String.valueOf(k));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, roundList);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, roundList2);
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, roundList3);
        roundSpin.setAdapter(arrayAdapter);
        roundSpin.setSelection(9);
        roundSpin2.setAdapter(arrayAdapter2);
        roundSpin2.setSelection(5);
        roundSpin3.setAdapter(arrayAdapter3);
        roundSpin3.setSelection(0);
    }

    // 라운드수, 난이도, 선공을 설정하는 함수
    public void setting(){
        setRound = Integer.parseInt(roundSpin.getSelectedItem().toString());
        setTurnCount = Integer.parseInt(roundSpin2.getSelectedItem().toString());
        setBonusCount = Integer.parseInt(roundSpin3.getSelectedItem().toString());
        switch (diffGroup.getCheckedRadioButtonId()){
            case R.id.hasu:
                setDifficult = 40;
                break;
            case R.id.middle:
                setDifficult = 65;
                break;
            case R.id.gosu:
                setDifficult = 90;
                break;
        }
        switch (firstGroup.getCheckedRadioButtonId()){
            case R.id.pButton:
                playerTurn = true;
                break;
            case R.id.cButton:
                playerTurn = false;
                break;
            case R.id.rButton:
                Random random = new Random();
                playerTurn = random.nextBoolean();
                break;
        }
    }

    // 리스너 설정 함수 메인으로 가거나 설정된 값을 가지고 게임을 시작함
    public void setListner() {
        View.OnClickListener Listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.gameStart:
                        setting();
                        Intent start = new Intent(getApplicationContext(), VersusCPU.class);
                        start.putExtra("Diff", setDifficult);
                        start.putExtra("Round", setRound);
                        start.putExtra("FirstTurn", playerTurn);
                        start.putExtra("SetCount", setTurnCount);
                        start.putExtra("SetBonus", setBonusCount);
                        startActivity(start);
                        finish();
                        break;
                    case R.id.goBack:
                        Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                        goToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToMain);
                        finish();
                        break;
                }

            }
        };

        goBack.setOnClickListener(Listner);
        gameStart.setOnClickListener(Listner);
    }
}
