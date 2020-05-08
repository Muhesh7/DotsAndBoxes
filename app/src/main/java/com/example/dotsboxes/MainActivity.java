package com.example.dotsboxes;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView mGridView;
    TextView mPlay1;
    TextView mPlay2Score;
    TextView mPlay2;
    TextView mPlay3;
    TextView mPlay4;
    SoundPool mSoundPool;
    TextView mPlay3Score;
    TextView mPlay4Score;
    Button Undo;
    ArrayList<Integer> Elements = new ArrayList<Integer>();
    ArrayList<Integer> Dot = new ArrayList<Integer>();
    ArrayList<Integer> pos = new ArrayList<Integer>();
    ArrayList<Integer> box = new ArrayList<Integer>();
    ArrayList<Integer> boxpos = new ArrayList<Integer>();
    ArrayList<Integer> Winpos=new ArrayList<Integer>();
    String Winner ="";
    String Mode;
    ImageView currentView;
    ImageView BoxView;
    ImageView BoxView2;
    ImageView OneBox;
    ImageView CompBox;
    ImageView CompBox2;
    ArrayList<Integer> CompPos=new ArrayList<Integer>();
    int CompCurrentPos;
    int CurrentPos;
    int mLoopCount;
    int CurrentBox;
    int UndoCounter;
    int TurnTracker2;
    int IterationCount;
    ImageView compImage;
    int SoundID1;
    int SoundID2;
    int BoxSound;
    int SoundCounter=0;
    int P1=0;
    int P2=0;
    int P3=0;
    int P4=0;
    int Col;
    int Row;
    int Playable=2;
    String Players;
    int countC;
    int countR=1;
    int turn=1;
    int turnTracker;
    ArrayList<Integer> ver=new ArrayList<Integer>();
    ArrayList<Integer> hor=new ArrayList<Integer>();
    public void onClick(View view) {
        if(pos.isEmpty())
        {
            Toast.makeText(this,"Start playing",Toast.LENGTH_SHORT).show();}
        else if(Playable!=0) {
            if (UndoCounter == 0) {
                UndoCounter++;
                if (ver.contains(CurrentPos))
                    currentView.setImageResource(R.drawable.blackver);
                else if (hor.contains(CurrentPos))
                    currentView.setImageResource(R.drawable.blackhor);
                turn = turnTracker;
                pos.remove(pos.size() - 1);
                if (boxpos.contains(CurrentBox) && Winpos.contains(CurrentPos)) {
                    Log.d("boxundo", Integer.toString(CurrentPos));
                    BoxView.setImageResource(R.drawable.whitebox);
                    boxpos.remove(boxpos.size() - 1);
                    Winpos.remove(Winpos.size() - 1);
                    switch (turn) {
                        case 1:
                            --P1;
                            break;
                        case 2:
                            --P2;
                            break;
                        case 3:
                            --P3;
                            break;
                        case 4:
                            --P4;
                            break;
                    }
                    if (mLoopCount == 2) {
                        BoxView2.setImageResource(R.drawable.whitebox);
                        boxpos.remove(boxpos.size() - 1);
                        Winpos.remove(Winpos.size() - 1);
                        switch (turn) {
                            case 1:
                                --P1;
                                break;
                            case 2:
                                --P2;
                                break;
                            case 3:
                                --P3;
                                break;
                            case 4:
                                --P4;
                                break;
                        }
                    }
                    Disp();
                }
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(200);
                }

            }
            else
                Toast.makeText(this,"Select your line",Toast.LENGTH_SHORT).show();
        }else{

            if ((!boxpos.isEmpty()&&boxpos.contains(boxpos.get(boxpos.size()-1)) )&& (Winpos.contains(Winpos.get(Winpos.size()-1))&&!Winpos.isEmpty())) {
                BoxView = (ImageView) mGridView.getChildAt(boxpos.get(boxpos.size() - 1));
                BoxView.setImageResource(R.drawable.whitebox);
                currentView =(ImageView) mGridView.getChildAt(pos.get(pos.size()-1));
                if (ver.contains(pos.get(pos.size()-1)))
                {currentView.setImageResource(R.drawable.blackver);}
                else if (hor.contains(pos.get(pos.size()-1)))
                {currentView.setImageResource(R.drawable.blackhor);}
                boxpos.remove(boxpos.size() - 1);
                Winpos.remove(Winpos.size() - 1);
                pos.remove(pos.size()-1);
                if(turnTracker==1) --P1;
                else --P2;
                if (mLoopCount == 2) {
                    BoxView2.setImageResource(R.drawable.whitebox);
                    boxpos.remove(boxpos.size() - 1);
                    Winpos.remove(Winpos.size() - 1);
                    --P1;
                }
                if(IterationCount==2){
                    CompBox2.setImageResource(R.drawable.whitebox);
                    boxpos.remove(boxpos.size() - 1);
                    Winpos.remove(Winpos.size() - 1);
                    --P2;
                    currentView =(ImageView) mGridView.getChildAt(pos.get(pos.size()-1));
                    if (ver.contains(pos.get(pos.size()-1)))
                    {currentView.setImageResource(R.drawable.blackver);}
                    else if (hor.contains(pos.get(pos.size()-1)))
                    {currentView.setImageResource(R.drawable.blackhor);}
                    pos.remove(pos.size()-1);
                }
            }else{ CurrentPos=pos.get(pos.size()-2);
                CompCurrentPos=pos.get(pos.size()-1);
                CurrentBox=boxpos.size()-1;
                currentView =(ImageView) mGridView.getChildAt(pos.get(pos.size()-2));
                compImage=(ImageView) mGridView.getChildAt(pos.get(pos.size()-1));
                if (ver.contains(pos.get(pos.size()-2)))
                {currentView.setImageResource(R.drawable.blackver);}
                else if (hor.contains(pos.get(pos.size()-2)))
                {currentView.setImageResource(R.drawable.blackhor);}
                if (ver.contains(pos.get(pos.size()-1)))
                { compImage.setImageResource(R.drawable.blackver);}
                else if (hor.contains(pos.get(pos.size()-1)))
                {compImage.setImageResource(R.drawable.blackhor);}
                pos.remove(pos.size()-1);
                pos.remove(pos.size()-1);}
         Disp();
        }
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(200);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay2=(TextView)findViewById(R.id.textView2);
        mPlay1=(TextView)findViewById(R.id.textView3);
        mPlay2Score =(TextView)findViewById(R.id.textView4);
        mPlay3=(TextView)findViewById(R.id.textView8);
        mPlay3Score=(TextView)findViewById(R.id.textView9);
        mPlay4=(TextView)findViewById(R.id.textView10);
        mPlay4Score=(TextView)findViewById(R.id.textView11);
        Undo=(Button)findViewById(R.id.button);
        mGridView=(GridView)findViewById(R.id.Grid);
        Retrive();
        GridFix();
      playerDisp();
        mSoundPool=new SoundPool(3, AudioManager.STREAM_MUSIC,0);
      SoundID1=mSoundPool.load(getApplicationContext(),R.raw.click,0);
      SoundID2=mSoundPool.load(getApplicationContext(),R.raw.click,0);
      BoxSound=mSoundPool.load(getApplicationContext(),R.raw.box,0);
        alignment();
        if(Playable!=0) {
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (((position % 2 != 0 && countR % 2 != 0) || (position % 2 != 0 && countR % 2 == 0)) && !pos.contains(position)) {
                        UndoCounter = 0;
                        SoundCounter++;
                        if(SoundCounter%2==0)
                        mSoundPool.play(SoundID1,1.5f,1.5f,0,0,0.3f);
                        else
                            mSoundPool.play(SoundID2,1.5f,1.5f,0,0,0.3f);
                        turnTracker = turn;
                        CurrentPos = position;
                        Log.d("turnTracker", Integer.toString(SoundCounter));
                        currentView = (ImageView) view;
                        if (Playable == 2) {

                            switch (turn) {
                                case 1:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.redhor);
                                    else
                                        currentView.setImageResource(R.drawable.redver);
                                    turn = 2;
                                    break;
                                case 2:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.bluehor);
                                    else
                                        currentView.setImageResource(R.drawable.bluever);
                                    turn = 1;
                                    break;
                            }
                        currentView.animate().rotationY(180).setDuration(900);
                        } else if (Playable == 3) {

                            switch (turn) {
                                case 1:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.redhor);
                                    else
                                        currentView.setImageResource(R.drawable.redver);
                                    turn = 2;
                                    break;
                                case 2:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.bluehor);
                                    else
                                        currentView.setImageResource(R.drawable.bluever);
                                    turn = 3;
                                    break;
                                case 3:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.greenhor);
                                    else
                                        currentView.setImageResource(R.drawable.greenver);
                                    turn = 1;
                                    break;
                            }
                            currentView.animate().rotationY(180).setDuration(900);
                        } else if (Playable == 4) {

                            switch (turn) {
                                case 1:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.redhor);
                                    else
                                        currentView.setImageResource(R.drawable.redver);
                                    turn = 2;
                                    break;
                                case 2:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.bluehor);
                                    else
                                        currentView.setImageResource(R.drawable.bluever);
                                    turn = 3;
                                    break;
                                case 3:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.greenhor);
                                    else
                                        currentView.setImageResource(R.drawable.greenver);
                                    turn = 4;
                                    break;
                                case 4:
                                    if (position % 2 != 0 && countR % 2 != 0)
                                        currentView.setImageResource(R.drawable.yellowhor);
                                    else
                                        currentView.setImageResource(R.drawable.yellowver);
                                    turn = 1;
                                    break;
                            }
                            currentView.animate().rotationY(180).setDuration(900);
                        }
                        pos.add(position);
                    }
                    mLoopCount = 0;
                    for (int Box : box) {
                        if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                            mLoopCount++;
                            Log.d("turnbeg", Integer.toString(turn));
                            mSoundPool.play(BoxSound,1.5f,1.5f,0,0,0.7f);
                            TurnTracker2 = turn;
                            BoxView = (ImageView) parent.getChildAt(Box);
                            if (Playable == 2) {
                                switch (turn) {
                                    case 1:
                                        BoxView.setImageResource(R.drawable.bluebox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P2;
                                        break;
                                    case 2:
                                        BoxView.setImageResource(R.drawable.redbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P1;
                                        break;
                                }

                            } else if (Playable == 3) {
                                switch (turn) {
                                    case 3:
                                        BoxView.setImageResource(R.drawable.bluebox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P2;
                                        break;
                                    case 2:
                                        BoxView.setImageResource(R.drawable.redbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P1;
                                        break;
                                    case 1:
                                        BoxView.setImageResource(R.drawable.greenbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P3;
                                        break;
                                }

                            } else if (Playable == 4) {
                                switch (turn) {
                                    case 3:
                                        BoxView.setImageResource(R.drawable.bluebox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P2;
                                        break;
                                    case 4:
                                        BoxView.setImageResource(R.drawable.greenbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P3;
                                        break;
                                    case 2:
                                        BoxView.setImageResource(R.drawable.redbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P1;
                                        break;
                                    case 1:
                                        BoxView.setImageResource(R.drawable.yellowbox);
                                        BoxView.animate().rotationXBy(-180f).setDuration(1000);
                                        ++P4;
                                        break;
                                }

                            }
                            boxpos.add(Box);
                            Winpos.add(position);
                            CurrentBox = Box;
                        }
                    }
                    if (mLoopCount != 0) {
                        turn = turnTracker;
                        if (mLoopCount == 2)
                            BoxView2 = (ImageView) parent.getChildAt(boxpos.get(boxpos.size() - 2));
                    }
                    Disp();
                    WinCond();
                }
            });
        }else if(Playable==0){

                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (((position % 2 != 0 && countR % 2 != 0) || (position % 2 != 0 && countR % 2 == 0)) && !pos.contains(position)) {
                            UndoCounter = 0;
                            //mSoundPool.play(SoundID1,1.5f,1.5f,1,0,0.2f);
                            turnTracker = 1;
                            CurrentPos = position;
                            Log.d("turnTracker", Integer.toString(turnTracker));
                            currentView = (ImageView) view;
                            if(turn==1) {
                                if (position % 2 != 0 && countR % 2 != 0)
                                    currentView.setImageResource(R.drawable.redhor);
                                else
                                    currentView.setImageResource(R.drawable.redver);
                                pos.add(CurrentPos);
                                turn = 0;
                            }
                            mLoopCount=0;
                            if (turn == 0) {
                                for (int Box : box) {
                                    if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                        mLoopCount++;
                                        BoxView = (ImageView) parent.getChildAt(Box);
                                        BoxView.setImageResource(R.drawable.redbox);
                                        BoxView.animate().rotationYBy(-180f).setDuration(1000);
                                        OneBox=BoxView;
                                        ++P1;
                                        boxpos.add(Box);
                                        Winpos.add(CurrentPos);

                                    }
                                    if (mLoopCount != 0) {
                                        turn = 1;
                                        if (mLoopCount == 2)
                                            BoxView2 = (ImageView) parent.getChildAt(boxpos.get(boxpos.size() - 2));
                                    }
                                }
                            }
                            if(turn==0) {
                                int n=0;
                                if(Mode.equals("Hard")) {
                                    Boolean b=true;
                                    for (int Box : box) {
                                        if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box - Col) && !boxpos.contains(Box)) {
                                            n=Box+Col;
                                            b=false;
                                        }
                                        else if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                            n=Box-Col;
                                            b=false;
                                        }
                                        else if (pos.contains(Box + 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                            n=Box-1;
                                            b=false;
                                        }
                                        else if (pos.contains(Box - 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                            n=Box+1;
                                            b=false;
                                        }
                                    }
                                    if(b){ Random random = new Random();
                                        n = random.nextInt(Row * Col);
                                        while ((box.contains(n) || Dot.contains(n)) || pos.contains(n)) {
                                            n = random.nextInt(Row * Col);
                                        }}
                                }
                                else if(Mode.equals("Easy")) {
                                    Random random = new Random();
                                    n = random.nextInt(Row * Col);
                                    while ((box.contains(n) || Dot.contains(n)) || pos.contains(n)) {
                                        n = random.nextInt(Row * Col);
                                    }
                                }
                               compImage = (ImageView) parent.getChildAt(n * 1);
                                if (ver.contains(n))
                                    compImage.setImageResource(R.drawable.bluever);
                                else if (hor.contains(n))
                                    compImage.setImageResource(R.drawable.bluehor);
                                Log.d("work", Integer.toString(n));
                                CompPos.add(n);
                                CompCurrentPos=n;
                                pos.add(CompCurrentPos);
                                turn = 1;
                            }
                            IterationCount=0;
                            if (turn == 1) {
                                for (int Box : box) {
                                    if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                        IterationCount++;
                                        BoxView = (ImageView) parent.getChildAt(Box);
                                        BoxView.setImageResource(R.drawable.bluebox);
                                        BoxView.animate().rotationYBy(-180f).setDuration(1000);
                                        ++P2;
                                        boxpos.add(Box);
                                        Winpos.add(position);
                                        CompBox=BoxView;
                                        turnTracker=0;

                                    }
                                }if(IterationCount>0){  Random random = new Random();
                                    int n = random.nextInt(Row * Col);
                                    while ((box.contains(n) || Dot.contains(n)) || pos.contains(n)) {
                                        n = random.nextInt(Row * Col);
                                    }
                                     CompBox2 = (ImageView) parent.getChildAt(n * 1);
                                    if (ver.contains(n))
                                        CompBox2.setImageResource(R.drawable.bluever);
                                    else if (hor.contains(n))
                                        CompBox2.setImageResource(R.drawable.bluehor);
                                    Log.d("work", Integer.toString(n));
                                    pos.add(n);
                                    turn = 1;
                                    for (int Box : box) {
                                        if (pos.contains(Box + 1) && pos.contains(Box - 1) && pos.contains(Box - Col) && pos.contains(Box + Col) && !boxpos.contains(Box)) {
                                            IterationCount++;
                                            BoxView = (ImageView) parent.getChildAt(Box);
                                            BoxView.setImageResource(R.drawable.bluebox);
                                            BoxView.animate().rotationYBy(-180f).setDuration(1000);
                                            ++P2;
                                            boxpos.add(Box);
                                            Winpos.add(position);
                                            CompBox=BoxView;}
                                    }
                                }


                            }

                        }
                        Disp();
                        WinCond();
                        Log.d("Color",Integer.toString(turn));
                    }
                });



        }

    }

    public void GridFix()
    { mGridView.setNumColumns(Col);
        countC=Col;
        Log.d("yass",Integer.toString( mGridView.getHorizontalSpacing()));
        for(int i=0;i<(Row*Col);++i)
        { if(i==countC)
        { ++countR;
            countC=Col*countR; }
            if(i%2==0 && countR%2!=0)
            {
                Elements.add(R.drawable.dot);
                Log.d(".",Integer.toString(i));
            Dot.add(i);}
            else if(i%2!=0 && countR%2!=0)
            {
                Elements.add(R.drawable.blackhor);
                Log.d("_",Integer.toString(i));
            hor.add(i);}
            else if(i%2!=0 && countR%2==0)
            {
                Elements.add(R.drawable.blackver);Log.d("|",Integer.toString(i));
            ver.add(i);}
            else if(i%2==0 && countR%2==0)
            {
                Elements.add(R.drawable.whitebox);Log.d("",Integer.toString(i));
                box.add(i);}
            Log.d("dots",Integer.toString(countC)+Integer.toString(countR)+Integer.toString(i));
        }

    }
    public void EndGame(String winner){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog .setTitle("Game Over");
        alertDialog.setMessage(winner.toString()+" wins");
        alertDialog.setPositiveButton("EndGame", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }); AlertDialog Dialog  = alertDialog.create();
        Dialog.show();    }
    public void Disp()
    { String win1;
        String win2;
        String win3;
        String win4;
      if(Playable==0) {
              win2 = Integer.toString(P2);
              mPlay2Score.setText(win2);
                  win1 = Integer.toString(P1);
                  mPlay1.setText(win1);

      }
      else {
            if (turn == 2) {
                win2 = Integer.toString(P2);
                mPlay2Score.setText(win2);
            } else if (turn == 1) {
                win1 = Integer.toString(P1);
                mPlay1.setText(win1);
            } else if (turn == 3) {
                win3 = Integer.toString(P3);
                mPlay3Score.setText(win3);
            } else if (turn == 4) {
                win4 = Integer.toString(P4);
                mPlay4Score.setText(win4);
            }
        }
    }
    public void WinCond()
    { if(boxpos.containsAll(box)) {
        ArrayList<Integer> score = new ArrayList<Integer>();
        score.add(P1);
        score.add(P2);
        score.add(P3);
        score.add(P4);
        if (Collections.max(score)==P1) Winner = "player1";
        else if (Collections.max(score)==P2) Winner = "player2";
        else if (Collections.max(score)==P3) Winner = "player3";
        else if (Collections.max(score)==P4) Winner = "player4";
        EndGame(Winner);
    }}
    public void Retrive()
    {
        Intent intent = getIntent();
        Players = intent.getStringExtra("player");
        Col = intent.getIntExtra("col",0);
        Row= intent.getIntExtra("row",0);
        Mode=intent.getStringExtra("mode");
    }
    public void playerDisp(){
        if(Players.equals("3 player"))
        {
            mPlay3.setVisibility(View.VISIBLE);
            mPlay3Score.setVisibility(View.VISIBLE);
            Playable=3;
        }
        else if(Players.equals("4 player"))
        {
            mPlay3.setVisibility(View.VISIBLE);
            mPlay3Score.setVisibility(View.VISIBLE);
            mPlay4.setVisibility(View.VISIBLE);
            mPlay4Score.setVisibility(View.VISIBLE);
            Playable=4;
        }else if(Players.equals("vs Com"))
        { mPlay2.setText("Comp");
            Playable=0;}


    }

    public void alignment()
    {
    if(Row==7)
    { ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();layoutParams.height = 510; mGridView.setLayoutParams(layoutParams);}
    else if(Row==9)
        { ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();layoutParams.height = 650; mGridView.setLayoutParams(layoutParams);}
    else if(Row==11)
    {ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();layoutParams.height = 800; mGridView.setLayoutParams(layoutParams);}
    else if(Row==13) {
        if (Col == 7) {
            ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();
            layoutParams.height = 950;
            mGridView.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();
            layoutParams.height = 920;
            mGridView.setLayoutParams(layoutParams);
        }
    }
        LineView Adapter;
         Adapter=new LineView(this, Elements,Col,Row);
        mGridView.setAdapter(Adapter);

    }

}
