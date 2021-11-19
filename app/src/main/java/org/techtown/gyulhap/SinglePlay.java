package org.techtown.gyulhap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);
    }
}
