package org.techtown.gyulhap;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class VersusCPU extends AppCompatActivity {

    private int MILLISINFUTURE;
    private final int countdownInterval = 1000;
    private int gyulCountSeconds;

    private int count = 10;
    private TextView countTxt;
    private CountDownTimer countDownTimer;

    private final int[] pictures = {R.drawable.black_blue_circle, R.drawable.black_blue_square, R.drawable.black_blue_triangle,
            R.drawable.black_green_circle, R.drawable.black_green_square, R.drawable.black_green_triangle,
            R.drawable.black_red_circle, R.drawable.black_red_square, R.drawable.black_red_triangle,
            R.drawable.gray_blue_circle, R.drawable.gray_blue_square, R.drawable.gray_blue_triangle,
            R.drawable.gray_green_circle, R.drawable.gray_green_square, R.drawable.gray_green_triangle,
            R.drawable.gray_red_circle, R.drawable.gray_red_square, R.drawable.gray_red_triangle,
            R.drawable.white_blue_circle, R.drawable.white_blue_square, R.drawable.white_blue_triangle,
            R.drawable.white_green_circle, R.drawable.white_green_square, R.drawable.white_green_triangle,
            R.drawable.white_red_circle, R.drawable.white_red_square, R.drawable.white_red_triangle};

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageButton imageButton5;
    private ImageButton imageButton6;
    private ImageButton imageButton7;
    private ImageButton imageButton8;
    private ImageButton imageButton9;
    private Button hapButton;
    private Button gyulButton;
    private Button showAnswerButton;
    private TextView playerAnswerText;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView answer5;
    private TextView answer6;
    private TextView answer7;
    private TextView answer8;
    private TextView answer9;
    private TextView answer10;
    private TextView answer11;
    private TextView answer12;
    private TextView answer13;
    private TextView answer14;
    private TextView answer15;
    private TextView playerScoreText;
    private TextView cpuScoreText;
    private TextView roundCountText;

    private TextView nowTurn;
    private TextView cpuName;


    private int pictureButtonCount;
    private TreeSet<Integer> playerAnswer = new TreeSet<>();
    private ArrayList<ImageButton> buttonList = new ArrayList<>();
    private ArrayList<TextView> correctAnswer = new ArrayList<>();
    private int correctCount;
    private int round;

    private Gyulhap pictureForGame;
    private Cpu cpuPlayer;
    private int playerScore;
    private int cpuScore;
    private boolean playerTurn;
    private boolean gyulTime;
    private boolean hapButtonClicked;

    private int settingDif;
    private int settingRound;
    private boolean settingFirst;
    private int settingCount;
    private int settingBonus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_cpu);

        settings();
        round = 0;
        playerScore = 0;
        cpuScore = 0;
        cpuPlayer = new Cpu(settingDif);
        playerTurn = settingFirst;
        gyulTime = false;
        hapButtonClicked = false;

        initializeView();
        SetListner();

        newGame();
        allAnswerClear();

        countDownTimer();
        countDownTimer.start();
        if (playerTurn == true) {
            nowTurn.setText("Player");
        } else {
            cpuPlayer.randomCpuPlay();
            nowTurn.setText("CPU");

        }
        setButton();

    }
    
    // ????????? ?????????
    private void settings(){
        Intent settingIntent = getIntent();
        settingDif = settingIntent.getIntExtra("Diff", 65);
        settingFirst = settingIntent.getBooleanExtra("FirstTurn", true);
        settingRound = settingIntent.getIntExtra("Round", 10);
        settingCount = settingIntent.getIntExtra("SetCount", 10);
        settingBonus = settingIntent.getIntExtra("SetBonus", 3);

        MILLISINFUTURE = settingCount * 1000;
        gyulCountSeconds = settingBonus * 1000;
    }

    //??? ?????????
    public void turnChange() {
        if (playerTurn == true && gyulTime == true) {
            setButton();
            gyulTime = false;
            countDownTimer.cancel();
            gyulCountDownTimer();
            countDownTimer.start();
        } else if (playerTurn == true && gyulTime == false) {
            playerTurn = false;
            setButton();
            cpuPlayer.randomCpuPlay();
        } else if (playerTurn == false && gyulTime == false) {
            playerTurn = true;
            setButton();
            countDownTimer.cancel();
            countDownTimer();
            countDownTimer.start();
        } else if (playerTurn == false && gyulTime == true) {
            setButton();
            cpuPlayer.randomCpuPlay();
            gyulTime = false;
        }

        if (playerTurn == true) {
            nowTurn.setText("Player");
        } else {
            nowTurn.setText("CPU");
        }
        playerScoreText.setText(String.valueOf(playerScore));
        cpuScoreText.setText(String.valueOf(cpuScore));
    }

    // ?????? ?????? ?????? ??????
    public void setButton() {
        setPictureButtonAllFalse();
        if (playerTurn == true && gyulTime == true) {
            hapButton.setEnabled(false);
            gyulButton.setEnabled(true);
        } else if (playerTurn == true && gyulTime == false) {
            hapButton.setEnabled(true);
            gyulButton.setEnabled(true);
        } else if (playerTurn == false && gyulTime == false) {
            hapButton.setEnabled(false);
            gyulButton.setEnabled(false);
        } else if (playerTurn == false && gyulTime == true) {
            hapButton.setEnabled(false);
            gyulButton.setEnabled(false);
        }
    }


    // ?????? 10??? ??? ?????????
    public void countDownTimer() {
        count = settingCount;
        countDownTimer = new CountDownTimer(MILLISINFUTURE, countdownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTxt.setText(String.valueOf(count));
                count--;
            }

            @Override
            public void onFinish() {
                threePictureClicked();
                setPictureButtonAllFalse();
            }
        };
    }

    // ??? ???????????? ????????? 3???
    public void gyulCountDownTimer() {
        count = settingBonus;
        countDownTimer = new CountDownTimer(gyulCountSeconds, countdownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTxt.setText(String.valueOf(count));
                count--;
            }

            @Override
            public void onFinish() {
                setPictureButtonAllFalse();
                turnChange();

            }
        };
    }

    // ?????????
    public void newGame() {
        round++;
        pictureForGame = new Gyulhap();
        setButtonPictures(buttonList);
        pictureButtonCount = 0;
        correctCount = 0;
        playerAnswerText.setText(null);
        playerScoreText.setText(String.valueOf(playerScore));
        cpuScoreText.setText(String.valueOf(cpuScore));
        roundCountText.setText(String.valueOf(round));
        allAnswerClear();

    }

    //??? ???????????? ?????? ?????? ??? ????????? ??????
    public void correctAnswer() {
        correctAnswer.get(correctCount).setText(playerAnswer.toString());
        correctCount++;
        gyulTime = true;
        countDownTimer.cancel();
        gyulCountDownTimer();
        countDownTimer.start();
    }

    // ?????? 3??? ????????? ??? ?????? ??? ????????? ??? ?????? ???????????? ??? ??????
    public void threePictureClicked() {
        if (hapButtonClicked == true) {
            int checkValue = pictureForGame.checkHap(playerAnswer);
            if (playerTurn == true) {
                playerScore += checkValue;
                playerScoreText.setText(String.valueOf(playerScore));
            } else {
                cpuScore += checkValue;
                cpuScoreText.setText(String.valueOf(cpuScore));
            }

            if (checkValue == 1) {
                //Toast.makeText(getApplicationContext(), "?????? ????????? 1???", Toast.LENGTH_SHORT).show();
                correctAnswer();
            } else {
                //Toast.makeText(getApplicationContext(), "??????????????? ???????????? 1???", Toast.LENGTH_SHORT).show();
            }
        }
        turnChange();
    }

    // ?????? ??? ?????? ?????? ????????????, ????????? ?????????
    public void setPictureButtonAllFalse() {
        for (ImageButton button : buttonList) {
            button.setEnabled(false);
        }
        hapButtonClicked = false;
        pictureButtonCount = 0;
        playerAnswer.clear();
        playerAnswerText.setText(null);
    }

    // ????????? ????????? ??? ????????? ?????????
    public void allAnswerClear() {
        for (TextView irr : correctAnswer) {
            irr.setText(null);
        }
    }

    // ?????? ?????????
    public void initializeView() {
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        imageButton6 = findViewById(R.id.imageButton6);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        imageButton9 = findViewById(R.id.imageButton9);
        buttonList.add(imageButton1);
        buttonList.add(imageButton2);
        buttonList.add(imageButton3);
        buttonList.add(imageButton4);
        buttonList.add(imageButton5);
        buttonList.add(imageButton6);
        buttonList.add(imageButton7);
        buttonList.add(imageButton8);
        buttonList.add(imageButton9);

        playerAnswerText = findViewById(R.id.playerAnswerText);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer5 = findViewById(R.id.answer5);
        answer6 = findViewById(R.id.answer6);
        answer7 = findViewById(R.id.answer7);
        answer8 = findViewById(R.id.answer8);
        answer9 = findViewById(R.id.answer9);
        answer10 = findViewById(R.id.answer10);
        answer11 = findViewById(R.id.answer11);
        answer12 = findViewById(R.id.answer12);
        answer13 = findViewById(R.id.answer13);
        answer14 = findViewById(R.id.answer14);
        answer15 = findViewById(R.id.answer15);
        correctAnswer.add(answer1);
        correctAnswer.add(answer2);
        correctAnswer.add(answer3);
        correctAnswer.add(answer4);
        correctAnswer.add(answer5);
        correctAnswer.add(answer6);
        correctAnswer.add(answer7);
        correctAnswer.add(answer8);
        correctAnswer.add(answer9);
        correctAnswer.add(answer10);
        correctAnswer.add(answer11);
        correctAnswer.add(answer12);
        correctAnswer.add(answer13);
        correctAnswer.add(answer14);
        correctAnswer.add(answer15);
        playerScoreText = findViewById(R.id.playerScoreText);
        cpuScoreText = findViewById(R.id.cpuScoreText);
        roundCountText = findViewById(R.id.roundCountText);
        countTxt = findViewById(R.id.countdownText);
        nowTurn = findViewById(R.id.nowturn);

        hapButton = findViewById(R.id.hap);
        gyulButton = findViewById(R.id.gyul);
        showAnswerButton = findViewById(R.id.showAnswer);
    }

    // ??? ????????? ????????? ?????? ?????? ??????
    public void setButtonPictures(ArrayList<ImageButton> buttons) {
        int count = 0, picNum;
        for (ImageButton irr : buttons) {
            irr.setEnabled(false);
            picNum = ((9 * pictureForGame.background.get(count)) + (3 * pictureForGame.color.get(count)) + (pictureForGame.shape.get(count)));
            irr.setImageResource(pictures[picNum]);
            count++;
        }
    }

    // ?????? ????????? ?????? ???????????? ???????????? ??????
    public void showGameResult() {
        countDownTimer.cancel();
        countDownTimer = null;
        Intent intent = new Intent(this, showWinner.class);
        intent.putExtra("PlayerScore", playerScore);
        intent.putExtra("CpuScore", cpuScore);
        startActivity(intent);
        finish();
    }

    // ?????? ???????????? ?????? ??????
    public void SetListner() {
        View.OnClickListener Listner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.showAnswer:
                        String answers = "";
                        for (Set<Integer> irr : pictureForGame.getAnswers()) {
                            answers += (irr.toString() + " ");
                        }
                        Toast.makeText(getApplicationContext(), answers, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.gyul:
                        if (pictureForGame.checkGyul()) {

                            if (playerTurn == true) {
                                playerScore += 3;
                            } else {
                                cpuScore += 3;
                            }
                            //Toast.makeText(getApplicationContext(), "?????? ????????? 3???", Toast.LENGTH_SHORT).show();
                            playerScoreText.setText(String.valueOf(playerScore));
                            if (round == settingRound) {
                                showGameResult();
                                finish();
                                break;
                            }
                            newGame();

                        } else {
                            if (playerTurn == true) {
                                playerScore -= 1;
                            } else {
                                cpuScore -= 1;
                            }
                            //Toast.makeText(getApplicationContext(), "??????????????? ???????????? 1???", Toast.LENGTH_SHORT).show();
                            playerScoreText.setText(String.valueOf(playerScore));
                        }
                        turnChange();
                        break;

                    case R.id.hap:
                        hapButton.setEnabled(false);
                        gyulButton.setEnabled(false);
                        hapButtonClicked = true;
                        for (ImageButton irr : buttonList) {
                            irr.setEnabled(true);
                        }
                        break;

                    case R.id.imageButton1:
                        imageButton1.setEnabled(false);
                        playerAnswer.add(1);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton2:
                        imageButton2.setEnabled(false);
                        playerAnswer.add(2);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton3:
                        imageButton3.setEnabled(false);
                        playerAnswer.add(3);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton4:
                        imageButton4.setEnabled(false);
                        playerAnswer.add(4);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton5:
                        imageButton5.setEnabled(false);
                        playerAnswer.add(5);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton6:
                        imageButton6.setEnabled(false);
                        playerAnswer.add(6);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton7:
                        imageButton7.setEnabled(false);
                        playerAnswer.add(7);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton8:
                        imageButton8.setEnabled(false);
                        playerAnswer.add(8);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                    case R.id.imageButton9:
                        imageButton9.setEnabled(false);
                        playerAnswer.add(9);
                        playerAnswerText.setText(playerAnswer.toString());
                        pictureButtonCount++;
                        if (pictureButtonCount == 3) {
                            threePictureClicked();
                        }
                        break;
                }
            }

        };

        imageButton1.setOnClickListener(Listner);
        imageButton2.setOnClickListener(Listner);
        imageButton3.setOnClickListener(Listner);
        imageButton4.setOnClickListener(Listner);
        imageButton5.setOnClickListener(Listner);
        imageButton6.setOnClickListener(Listner);
        imageButton7.setOnClickListener(Listner);
        imageButton8.setOnClickListener(Listner);
        imageButton9.setOnClickListener(Listner);
        hapButton.setOnClickListener(Listner);
        gyulButton.setOnClickListener(Listner);
        showAnswerButton.setOnClickListener(Listner);
    }

    // ????????? ????????? ?????????
    class Cpu {
        private Random random;
        private final int difficulty;
        private int wrongRate = 0;
        private final int answerHapCount;
        private final int answerGyulCount;

        // ????????? ???????????? ????????? ??????
        public Cpu(int difficulty) {
            this.difficulty = difficulty;
            random = new Random();
            answerHapCount = 3 * 1000;
            answerGyulCount = 2 * 1000;
            cpuName = findViewById(R.id.cpuText);
            switch (this.difficulty){
                case 40:
                    cpuName.setText("??????CPU");
                    wrongRate = 80;
                    break;
                case 65:
                    cpuName.setText("??????CPU");
                    wrongRate = 90;
                    break;
                case 90:
                    cpuName.setText("??????CPU");
                    wrongRate = 40;
                    break;
            }

        }

        // ?????? ???????????? ????????? ??????????????? ??????
        private void randomCpuPlay() {
            int callAnswer = Math.abs(random.nextInt() % 100);
            if (callAnswer < difficulty) {  //????????? ??????
                if (gyulTime == false) {
                    if(difficulty == 90){   // ????????? ?????? ?????? ????????? ????????? ??????????????? ??????
                        if(pictureForGame.getAnswers().size() == 2 && callAnswer < wrongRate){
                            countDownTimer.cancel();
                            countDownTimer();
                            countDownTimer.start();
                        }
                        else{
                            countDownTimer.cancel();
                            cpuAnswerCountDown();
                            countDownTimer.start();
                        }
                    }else {     // ????????? ????????? ????????? ?????? ?????? ??????
                        countDownTimer.cancel();
                        cpuAnswerCountDown();
                        countDownTimer.start();
                    }
                } else {    // ????????? ????????? ?????? ????????? ??????
                    if (pictureForGame.checkGyul()) {
                        countDownTimer.cancel();
                        cpuGyulAnswerCountDown();
                        countDownTimer.start();
                    } else {
                        countDownTimer.cancel();
                        gyulCountDownTimer();
                        countDownTimer.start();
                    }
                }
            }
            else if((difficulty != 90) && (callAnswer >= wrongRate)){   // ????????? ????????? ???????????? ???????????? ????????? ??????
                if(gyulTime == false) {
                    countDownTimer.cancel();
                    cpuWrongHapAnswerCountDown();
                    countDownTimer.start();
                }
                else {
                    countDownTimer.cancel();
                    cpuWrongGyulAnswerCountDown();
                    countDownTimer.start();
                }
            }
            else {  // ??? ????????? ?????? ???????????? ?????? ?????? ??? ?????????
                if (gyulTime == false) {
                    countDownTimer.cancel();
                    countDownTimer();
                    countDownTimer.start();
                } else {
                    countDownTimer.cancel();
                    gyulCountDownTimer();
                    countDownTimer.start();

                }
            }
        }

        // ????????? ????????? ??????????????? ?????? ????????? ?????? 3?????? ?????? ??????
        private void cpuAnswerCountDown() {
            count = settingCount;
            countDownTimer = new CountDownTimer(answerHapCount, countdownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countTxt.setText(String.valueOf(count));
                    count--;

                }

                @Override
                public void onFinish() {
                    cpuHapPlay();
                }
            };
        }

        // ??? ?????? ????????? ??????????????? 2?????? ??? 
        private void cpuGyulAnswerCountDown() {
            count = settingBonus;
            countDownTimer = new CountDownTimer(answerGyulCount, countdownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countTxt.setText(String.valueOf(count));
                    count--;
                }
                @Override
                public void onFinish() {
                    cpuGyulPlay();
                }
            };
        }

        // ?????? ??????????????? ??? ??????
        private void cpuGyulPlay() {
            if (pictureForGame.checkGyul() == true) {
                gyulButton.callOnClick();
            } else {
                turnChange();
            }
        }

        // ?????? ?????? ????????? ?????? ?????? ????????? ??????????????? ?????? 3?????? ??????
        private void cpuWrongHapAnswerCountDown() {
            count = settingCount;
            countDownTimer = new CountDownTimer(answerHapCount, countdownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countTxt.setText(String.valueOf(count));
                    count--;
                }

                @Override
                public void onFinish() {
                    cpuWrongPlay();
                }
            };
        }

        // ??? ????????? ?????? ?????? ????????? ???????????????
        private void cpuWrongGyulAnswerCountDown() {
            count = settingBonus;
            countDownTimer = new CountDownTimer(answerGyulCount, countdownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countTxt.setText(String.valueOf(count));
                    count--;
                }
                @Override
                public void onFinish() {
                    cpuWrongPlay();
                }
            };
        }

        // ?????? ?????? ????????? ?????? ?????? ????????? ?????? ????????? ?????? ????????? ?????? ????????? ????????? ?????? 3?????? ????????? ?????? ??????
        private void cpuWrongPlay(){
            if(pictureForGame.checkGyul()){
                hapButton.callOnClick();
                imageButton1.callOnClick();
                imageButton1.callOnClick();
                imageButton1.callOnClick();
            }
            else{
                gyulButton.callOnClick();
            }
        }

        // ?????? 10?????? ?????? ????????? ????????? ?????? ?????? ?????? ?????? ?????? ??????, ????????? ?????? ??????
        private void cpuHapPlay() {
            if (pictureForGame.checkGyul() == false) {
                Set<Integer> cpuAnswer = pictureForGame.getAnswers().get(0);
                hapButton.callOnClick();
                for (Integer irr : cpuAnswer) {
                    switch (irr) {
                        case 1:
                            imageButton1.callOnClick();
                            break;
                        case 2:
                            imageButton2.callOnClick();
                            break;
                        case 3:
                            imageButton3.callOnClick();
                            break;
                        case 4:
                            imageButton4.callOnClick();
                            break;
                        case 5:
                            imageButton5.callOnClick();
                            break;
                        case 6:
                            imageButton6.callOnClick();
                            break;
                        case 7:
                            imageButton7.callOnClick();
                            break;
                        case 8:
                            imageButton8.callOnClick();
                            break;
                        case 9:
                            imageButton9.callOnClick();
                            break;
                    }
                }
            } else {
                gyulButton.callOnClick();
            }
        }
    }
}


