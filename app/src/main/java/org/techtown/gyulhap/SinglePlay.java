package org.techtown.gyulhap;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SinglePlay extends AppCompatActivity {
    int[] pictures = {R.drawable.black_blue_circle, R.drawable.black_blue_square, R.drawable.black_blue_triangle,
            R.drawable.black_green_circle, R.drawable.black_green_square, R.drawable.black_green_triangle,
            R.drawable.black_red_circle, R.drawable.black_red_square, R.drawable.black_red_triangle,
            R.drawable.gray_blue_circle, R.drawable.gray_blue_square, R.drawable.gray_blue_triangle,
            R.drawable.gray_green_circle, R.drawable.gray_green_square, R.drawable.gray_green_triangle,
            R.drawable.gray_red_circle, R.drawable.gray_red_square, R.drawable.gray_red_triangle,
            R.drawable.white_blue_circle, R.drawable.white_blue_square, R.drawable.white_blue_triangle,
            R.drawable.white_green_circle, R.drawable.white_green_square, R.drawable.white_green_triangle,
            R.drawable.white_red_circle, R.drawable.white_red_square, R.drawable.white_red_triangle};

    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;
    ImageButton imageButton6;
    ImageButton imageButton7;
    ImageButton imageButton8;
    ImageButton imageButton9;

    Gyulhap oneGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);

        oneGame = new Gyulhap();
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        imageButton6 = findViewById(R.id.imageButton6);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        imageButton9 = findViewById(R.id.imageButton9);
        ArrayList<ImageButton> buttonList = new ArrayList<>();
        buttonList.add(imageButton1);
        buttonList.add(imageButton2);
        buttonList.add(imageButton3);
        buttonList.add(imageButton4);
        buttonList.add(imageButton5);
        buttonList.add(imageButton6);
        buttonList.add(imageButton7);
        buttonList.add(imageButton8);
        buttonList.add(imageButton9);
        setButtonPictures(buttonList);


    }

    public void setButtonPictures(ArrayList<ImageButton> buttons){
        int count = 0, picNum;
        for(ImageButton irr: buttons){
            irr.setEnabled(false);
            picNum = ((9 * oneGame.background.get(count)) + (3 * oneGame.color.get(count)) + (oneGame.shape.get(count)));
            irr.setImageResource(pictures[picNum]);
            count++;
        }
    }

    public void showAnswer(View v){
        String answers = "";
        for(Set<Integer> irr: oneGame.getAnswers()){
            answers += (irr.toString() + " ");
        }
        Toast.makeText(getApplicationContext(), answers, Toast.LENGTH_SHORT).show();
    }
}


class Gyulhap{
    final ArrayList<Integer> background;
    final ArrayList<Integer> color;
    final ArrayList<Integer> shape;
    private ArrayList<Set<Integer>> answers = new ArrayList<>();

    public ArrayList<Set<Integer>> getAnswers(){
        return answers;
    }

    //합 목록 출력 함수
    public void printAnswer() {
        for(Set<Integer> irr : answers) {
            System.out.println(irr.toString());
        }
        System.out.printf("합의 개수 : %d\n", answers.size());
    }

    //생성자
    public Gyulhap() {
        ArrayList<ArrayList<Integer>> attrs = makeRandomList();
        this.background = attrs.get(0);
        this.color = attrs.get(1);
        this.shape = attrs.get(2);
        this.answers = makeAnswers(this.background, this.color, this.shape);

    }

    //속성 생성 함수
    private ArrayList<ArrayList<Integer>> makeRandomList(){
        ArrayList<ArrayList<Integer>> attr = new ArrayList<>();
        ArrayList<Integer> back = new ArrayList<>();;
        ArrayList<Integer> col = new ArrayList<>();;
        ArrayList<Integer> shp = new ArrayList<>();;
        Random rd = new Random();
        int count = 0;

        while(count < 9) {
            if(count == 0) {
                back.add(rd.nextInt(3));
                shp.add(rd.nextInt(3));
                col.add(rd.nextInt(3));
                count++;
            }
            else {
                int b = rd.nextInt(3), s = rd.nextInt(3), c = rd.nextInt(3);
                boolean duplicate = false;
                for(int check = 0; check < count; check++) {
                    if(back.get(check) == b && col.get(check) == c && shp.get(check) == s) {
                        duplicate = true;
                        break;
                    }
                }
                if(!duplicate) {
                    back.add(b);
                    shp.add(s);
                    col.add(c);
                    count++;
                }
            }
        }

        attr.add(back);
        attr.add(col);
        attr.add(shp);
        return attr;
    }

    //만들어진 속성을 바탕으로 합을 찾아내는 함수
    private ArrayList<Set<Integer>> makeAnswers(ArrayList<Integer> back, ArrayList<Integer> col, ArrayList<Integer> shp){
        ArrayList<Set<Integer>> answers = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = i + 1; j < 9; j++) {
                for(int k = j + 1; k < 9; k++) {
                    if((back.get(i) + back.get(j) + back.get(k)) % 3 == 0) {
                        if((col.get(i) + col.get(j) + col.get(k)) % 3 == 0) {
                            if((shp.get(i) + shp.get(j) + shp.get(k)) % 3 == 0) {
                                Set<Integer> answer = new TreeSet<>();
                                answer.add(i+1);
                                answer.add(j+1);
                                answer.add(k+1);
                                answers.add(answer);
                            }
                        }
                    }
                }
            }
        }
        return answers;
    }
}