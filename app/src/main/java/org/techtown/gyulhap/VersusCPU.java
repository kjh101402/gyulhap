package org.techtown.gyulhap;

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

    private static final int MILLISINFUTURE = 10 * 1000;
    private static final int countdownInterval = 1000;

    private int count = 10;
    private TextView countTxt;
    private CountDownTimer countDownTimer;

    private int[] pictures = {R.drawable.black_blue_circle, R.drawable.black_blue_square, R.drawable.black_blue_triangle,
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


    private int pictureButtonCount;
    private TreeSet<Integer> playerAnswer = new TreeSet<>();
    private ArrayList<ImageButton> buttonList = new ArrayList<>();
    private ArrayList<TextView> correctAnswer = new ArrayList<>();
    private int correctCount;
    private int round;

    private Gyulhap pictureForGame;
    private int playerScore;
    private int cpuScore;
    private boolean playerTurn;
    private boolean gyulTime;
    private boolean hapButtonClicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_cpu);

        countTxt = findViewById(R.id.countdownText);


        round = 0;
        playerScore = 0;
        cpuScore = 0;
        Random random = new Random();
        //playerTurn = random.nextBoolean();
        playerTurn = true;
        gyulTime = false;
        hapButtonClicked = false;

        initializeView();
        SetListner();
        newGame();
        allAnswerClear();

        countDownTimer();
        countDownTimer.start();


    }

    public void turnChange(boolean turn, boolean gyul) {
        if (turn == true && gyul == true) {
            gyulTime = false;
            hapButton.setEnabled(false);
            gyulButton.setEnabled(true);
        } else if (turn == true && gyul == false) {
            gyulTime = false;
            playerTurn = false;
            hapButton.setEnabled(false);
            gyulButton.setEnabled(false);

        } else if (turn == false && gyul == false) {
            playerTurn = true;
            hapButton.setEnabled(true);
            gyulButton.setEnabled(true);
        } else {
            gyulTime = false;
            hapButton.setEnabled(false);
            gyulButton.setEnabled(false);

        }
    }

    public void countDownTimer() {
        count = 10;


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
                //turnChange(playerTurn, gyulTime);
                countDownTimer.cancel();
                countDownTimer();
                countDownTimer.start();
            }
        };
    }


    public void gyulCountDownTimer() {
        count = 3;

        gyulButton.setEnabled(true);
        countDownTimer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTxt.setText(String.valueOf(count));
                count--;

            }

            @Override
            public void onFinish() {
                setPictureButtonAllFalse();
                turnChange(playerTurn, gyulTime);
                countDownTimer.cancel();
                countDownTimer();
                countDownTimer.start();
            }
        };
    }


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

    public void correctAnswer() {
        correctAnswer.get(correctCount).setText(playerAnswer.toString());
        correctCount++;
        gyulTime = true;
        countDownTimer.cancel();
        gyulCountDownTimer();
        countDownTimer.start();

    }

    public void threePictureClicked() {
        if (hapButtonClicked == true) {
            int checkValue = pictureForGame.checkHap(playerAnswer);
            if (playerTurn) {
                playerScore += checkValue;
                playerScoreText.setText(String.valueOf(playerScore));
            } else {
                cpuScore += checkValue;
                cpuScoreText.setText(String.valueOf(cpuScore));
            }

            if (checkValue == 1) {
                Toast.makeText(getApplicationContext(), "정답 플러스 1점", Toast.LENGTH_SHORT).show();
                correctAnswer();
                turnChange(playerTurn, gyulTime);
            } else {
                Toast.makeText(getApplicationContext(), "틀렸습니다 마이너스 1점", Toast.LENGTH_SHORT).show();
                turnChange(playerTurn, gyulTime);
            }
        } else {
            turnChange(playerTurn, gyulTime);
        }
        setPictureButtonAllFalse();


    }

    public void setPictureButtonAllFalse() {
        for (ImageButton button : buttonList) {
            button.setEnabled(false);
        }
        hapButtonClicked = false;
        pictureButtonCount = 0;
        playerAnswer.clear();
        playerAnswerText.setText(null);
    }

    public void allAnswerClear() {
        for (TextView irr : correctAnswer) {
            irr.setText(null);
        }
    }

    private void cpuTurn() {
        int difficulty = 90;
        Random random = new Random();
        int callAnswer = random.nextInt(100);

        if (callAnswer < difficulty) {

            if (pictureForGame.getAnswers().isEmpty() == false) {
                Set<Integer> cpuAnswer = pictureForGame.getAnswers().get(0);
                int checkValue = pictureForGame.checkHap(cpuAnswer);
                cpuScore += checkValue;
                cpuScoreText.setText(String.valueOf(cpuScore));
                turnChange(playerTurn, gyulTime);
                setPictureButtonAllFalse();
                /*hapButton.callOnClick();
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
                    }*/
            }
        } else {
            gyulButton.callOnClick();
        }
    }


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


        hapButton = findViewById(R.id.hap);
        gyulButton = findViewById(R.id.gyul);
        showAnswerButton = findViewById(R.id.showAnswer);
    }


    public void setButtonPictures(ArrayList<ImageButton> buttons) {
        int count = 0, picNum;
        for (ImageButton irr : buttons) {
            irr.setEnabled(false);
            picNum = ((9 * pictureForGame.background.get(count)) + (3 * pictureForGame.color.get(count)) + (pictureForGame.shape.get(count)));
            irr.setImageResource(pictures[picNum]);
            count++;
        }
    }

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
                            Toast.makeText(getApplicationContext(), "정답 플러스 3점", Toast.LENGTH_SHORT).show();
                            playerScoreText.setText(String.valueOf(playerScore));
                            newGame();
                        } else {
                            if (playerTurn == true) {
                                playerScore -= 1;
                            } else {
                                cpuScore -= 1;
                            }
                            Toast.makeText(getApplicationContext(), "틀렸습니다 마이너스 1점", Toast.LENGTH_SHORT).show();
                            playerScoreText.setText(String.valueOf(playerScore));
                        }
                        turnChange(playerTurn, gyulTime);
                        countDownTimer.cancel();
                        countDownTimer();
                        countDownTimer.start();
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

}
