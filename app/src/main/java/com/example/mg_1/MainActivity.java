package com.example.mg_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {
    private Handler mHandler=new Handler() {};
    private int x=0;
    private int[][] maze;//迷宫矩阵
    private int[][] maze1;//是否访问
    private stack street;
    private stack s;//邻墙入栈
    private int bj;
    private GridView gv;
    private int h,z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init(51, 51);
        get();
        gv = (GridView) findViewById(R.id.gv);
        h=0;
        z=0;



        final ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 51; i++)
            for (int j = 0; j < 51; j++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                if(i==0&&j==0)
                    map.put("ItemImg", R.drawable.greed);
                else if(i==50&&j==50)
                    map.put("ItemImg", R.drawable.red);
                else if (maze[i][j] == 0)
                    map.put("ItemImg", R.drawable.wall);
                else
                    map.put("ItemImg", R.drawable.road);
                lst.add(map);
            }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, lst, R.layout.small_part, new String[]{"ItemImg"}, new int[]{R.id.imageView});
        gv.setAdapter(simpleAdapter);


        final Button bt1=(Button)findViewById(R.id.bt1);
        Button bt2=(Button)findViewById(R.id.bt2);
        Button bt3=(Button)findViewById(R.id.bt3);
        Button bt4=(Button)findViewById(R.id.bt4);
        final Button bt5=(Button)findViewById(R.id.bt5);
        final Button bt6=(Button)findViewById(R.id.bt6);


//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(h>0&&maze[h-1][z]==1){
//
//                    updateSingle(h*51+z,R.drawable.road);
//                    updateSingle(h*51-51+z,R.drawable.greed);
//                    h--;
//                    if(h==50&&z==50)
//                        showExitDialog01();
//                }
//
//
//            }
//        });
        bt1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    x=0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (x==0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(h>0&&maze[h-1][z]==1){

                                            updateSingle(h*51+z,R.drawable.road);
                                            updateSingle(h*51-51+z,R.drawable.greed);
                                            h--;
                                            if(h==50&&z==50)
                                                showExitDialog01();
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                }else if(event.getAction() == MotionEvent.ACTION_UP){

                    x=1;
                }
                return false;
            }
        });


//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(z>0&&maze[h][z-1]==1){
//                    updateSingle(h*51+z,R.drawable.road);
//                    updateSingle(h*51-1+z,R.drawable.greed);
//                    z--;
//                    if(h==50&&z==50)
//                        showExitDialog01();
//
//                }
//
//            }
//        });

        bt2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    x=0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (x==0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (z > 0 && maze[h][z - 1] == 1) {
                                            updateSingle(h * 51 + z, R.drawable.road);
                                            updateSingle(h * 51 - 1 + z, R.drawable.greed);
                                            z--;
                                            if (h == 50 && z == 50)
                                                showExitDialog01();

                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                }else if(event.getAction() == MotionEvent.ACTION_UP){

                    x=1;
                }
                return false;
            }
        });


//        bt3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(z!=50&&maze[h][z+1]==1){
//
//                    updateSingle(h*51+z,R.drawable.road);
//                    updateSingle(h*51+1+z,R.drawable.greed);
//                    z++;
//                    if(h==50&&z==50)
//                        showExitDialog01();
//                }
//            }
//        });
        bt3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    x=0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (x==0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(z!=50&&maze[h][z+1]==1){

                                            updateSingle(h*51+z,R.drawable.road);
                                            updateSingle(h*51+1+z,R.drawable.greed);
                                            z++;
                                            if(h==50&&z==50)
                                                showExitDialog01();
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    x=1;
                }
                return false;
            }
        });


//
//        bt4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(h!=50&&maze[h+1][z]==1){
//                    updateSingle(h*51+z,R.drawable.road);
//                    updateSingle(h*51+51+z,R.drawable.greed);
//                    h++;
//                    if(h==50&&z==50)
//                        showExitDialog01();
//                }
//
//            }
//        });
        bt4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    x=0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (x==0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(h!=50&&maze[h+1][z]==1){
                                            updateSingle(h*51+z,R.drawable.road);
                                            updateSingle(h*51+51+z,R.drawable.greed);
                                            h++;
                                            if(h==50&&z==50)
                                                showExitDialog01();
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    x=1;
                }
                return false;
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreate(null);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt6.getText().toString().equals("寻路外挂")){
                    init2(51,51);
                    DFS_bug(h,z);
                    for(int ll=0;ll<street.k;ll++){
                        updateSingle(street.m[ll][0]*51+street.m[ll][1],R.drawable.red);
                    }
                    bt6.setText("取消外挂");
                    bt6.setBackgroundResource(R.drawable.greed);

                }
                else{
                    for(int ll=0;ll<street.k;ll++){
                        updateSingle(street.m[ll][0]*51+street.m[ll][1],R.drawable.road);
                    }
                    updateSingle(2600,R.drawable.red);

                    bt6.setText("寻路外挂");
                    bt6.setBackgroundResource(R.drawable.red);

                }
            }
        });
    }

    public void get() {
        int m = 0, n = 0;
        maze1[0][0] = 1;
        s.push(0, 1);
        s.push(1, 0);
        maze1[0][1] = 1;
        maze1[1][0] = 1;
        while (s.k > 0) {
            Random rand = new Random();
            int rd = rand.nextInt(s.k);
            if (s.m[rd][0] % 2 == 0) {
                if (maze1[s.m[rd][0]][s.m[rd][1] - 1] == 1 && maze1[s.m[rd][0]][s.m[rd][1] + 1] == 1) {
                    s.pop(rd);
                    maze1[s.m[rd][0]][s.m[rd][1]] = 1;
                } else {
                    if (maze1[s.m[rd][0]][s.m[rd][1] - 1] == 1) {
                        maze[s.m[rd][0]][s.m[rd][1]] = 1;
                        maze1[s.m[rd][0]][s.m[rd][1] + 1] = 1;
                        int s0 = s.m[rd][0], s1 = s.m[rd][1] + 1;
                        if (s0 - 1 >= 0)
                            if (maze1[s0 - 1][s1] == 0) {
                                s.push(s0 - 1, s1);
                                maze1[s0 - 1][s1] = 1;
                            }
                        if (s1 - 1 >= 0)
                            if (maze1[s0][s1 - 1] == 0) {
                                s.push(s0, s1 - 1);
                                maze1[s0][s1 - 1] = 1;
                            }
                        if (s0 + 1 < 51)
                            if (maze1[s0 + 1][s1] == 0) {
                                s.push(s0 + 1, s1);
                                maze1[s0 + 1][s1] = 1;
                            }
                        if (s1 + 1 <51)
                            if (maze1[s0][s1 + 1] == 0) {
                                s.push(s0, s1 + 1);
                                maze1[s0][s1 + 1] = 1;
                            }
                        s.pop(rd);
                    } else {
                        maze[s.m[rd][0]][s.m[rd][1]] = 1;
                        maze1[s.m[rd][0]][s.m[rd][1] - 1] = 1;
                        int s0 = s.m[rd][0], s1 = s.m[rd][1] - 1;
                        if (s0 - 1 >= 0)
                            if (maze1[s0 - 1][s1] == 0) {
                                s.push(s0 - 1, s1);
                                maze1[s0 - 1][s1] = 1;
                            }
                        if (s1 - 1 >= 0)
                            if (maze1[s0][s1 - 1] == 0) {
                                s.push(s0, s1 - 1);
                                maze1[s0][s1 - 1] = 1;
                            }
                        if (s0 + 1 < 51)
                            if (maze1[s0 + 1][s1] == 0) {
                                s.push(s0 + 1, s1);
                                maze1[s0 + 1][s1] = 1;
                            }
                        if (s1 + 1 < 51)
                            if (maze1[s0][s1 + 1] == 0) {
                                s.push(s0, s1 + 1);
                                maze1[s0][s1 + 1] = 1;
                            }
                        s.pop(rd);
                    }
                }

            } else {
                if (maze1[s.m[rd][0] - 1][s.m[rd][1]] == 1 && maze1[s.m[rd][0] + 1][s.m[rd][1]] == 1) {
                    s.pop(rd);
                    maze1[s.m[rd][0]][s.m[rd][1]] = 1;
                } else {
                    if (maze1[s.m[rd][0] - 1][s.m[rd][1]] == 1) {
                        maze[s.m[rd][0]][s.m[rd][1]] = 1;
                        maze1[s.m[rd][0] + 1][s.m[rd][1]] = 1;
                        int s0 = s.m[rd][0] + 1, s1 = s.m[rd][1];
                        if (s0 - 1 >= 0)
                            if (maze1[s0 - 1][s1] == 0) {
                                s.push(s0 - 1, s1);
                                maze1[s0 - 1][s1] = 1;
                            }
                        if (s1 - 1 >= 0)
                            if (maze1[s0][s1 - 1] == 0) {
                                s.push(s0, s1 - 1);
                                maze1[s0][s1 - 1] = 1;
                            }
                        if (s0 + 1 < 51)
                            if (maze1[s0 + 1][s1] == 0) {
                                s.push(s0 + 1, s1);
                                maze1[s0 + 1][s1] = 1;
                            }
                        if (s1 + 1 < 51)
                            if (maze1[s0][s1 + 1] == 0) {
                                s.push(s0, s1 + 1);
                                maze1[s0][s1 + 1] = 1;
                            }
                        s.pop(rd);
                    } else {
                        maze[s.m[rd][0]][s.m[rd][1]] = 1;
                        maze1[s.m[rd][0] - 1][s.m[rd][1]] = 1;
                        int s0 = s.m[rd][0] - 1, s1 = s.m[rd][1];
                        if (s0 - 1 >= 0)
                            if (maze1[s0 - 1][s1] == 0) {
                                s.push(s0 - 1, s1);
                                maze1[s0 - 1][s1] = 1;
                            }
                        if (s1 - 1 >= 0)
                            if (maze1[s0][s1 - 1] == 0) {
                                s.push(s0, s1 - 1);
                                maze1[s0][s1 - 1] = 1;
                            }
                        if (s0 + 1 < 51)
                            if (maze1[s0 + 1][s1] == 0) {
                                s.push(s0 + 1, s1);
                                maze1[s0 + 1][s1] = 1;
                            }
                        if (s1 + 1 <51)
                            if (maze1[s0][s1 + 1] == 0) {
                                s.push(s0, s1 + 1);
                                maze1[s0][s1 + 1] = 1;
                            }
                        s.pop(rd);
                    }
                }

            }

        }
    }
    public void init ( int i, int j){
            s = new stack();
            maze = new int[i][j];
            maze1 = new int[i][j];


        for (int i1 = 0; i1 < i; i1++)
                for (int j1 = 0; j1 < j; j1++) {
                    maze[i1][j1] = 0;//迷宫矩阵
                    maze1[i1][j1] = 0;


                }

            for (int i2 = 0; i2*2 < i ; i2++)
                for (int j2 = 0; j2*2 < j; j2++)
                    maze[i2 * 2 ][j2 * 2 ] = 1;
        }
    private void showExitDialog01(){
        new AlertDialog.Builder(this)
                .setTitle("恭喜！！！")
                .setMessage("大吉大利，今晚吃鸡~")
                .setPositiveButton("确定", null)
                .show();
    }


    public void DFS_bug(int i,int j){
        if(i==50&&j==50)
            bj=1;
        if(bj==1)
            return;
        maze1[i][j]=1;
        if(j<50){
            if(maze[i][j+1]==1&&maze1[i][j+1]==0){
                street.push(i,j+1);
                DFS_bug(i,j+1);
            }
        }
        if(bj==1)
            return;
        if(i<50){
            if(maze[i+1][j]==1&&maze1[i+1][j]==0){
                street.push(i+1,j);
                DFS_bug(i+1,j);
            }
        }
        if(bj==1)
            return;
        if(j>0){
            if(maze[i][j-1]==1&&maze1[i][j-1]==0){
                street.push(i,j-1);
                DFS_bug(i,j-1);
            }
        }
        if(bj==1)
            return;
        if(i>0){
            if(maze[i-1][j]==1&&maze1[i-1][j]==0){
                street.push(i-1,j);
                DFS_bug(i-1,j);
            }
        }
        if(bj==1)
            return;
        street.pop(street.k-1);
    };
    private void updateSingle(int position,int img) {

            View view = gv.getChildAt(position - 0);
            ImageView ivvv = (ImageView) view.findViewById(R.id.imageView);
            ivvv.setImageResource(img);

    }
    public void init2(int i,int j){
        bj=0;
        street=new stack();

        for (int i1 = 0; i1 < 51; i1++)
            for (int j1 = 0; j1 < 51; j1++) {
                maze1[i1][j1] = 0;

            }
    }


}



























