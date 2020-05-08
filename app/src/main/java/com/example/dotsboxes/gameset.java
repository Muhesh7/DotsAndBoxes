package com.example.dotsboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class gameset extends AppCompatActivity
{
    int mrow;
    String mMode;
        String mplay;
        int mcol;
        Spinner Row;
        Spinner Col;
        Spinner Player;
        Button Play;
        Spinner difficulty;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gameset);

            Row=(Spinner)findViewById(R.id.spinner);
            Col=(Spinner)findViewById(R.id.spinner2);
            Player=(Spinner)findViewById(R.id.spinner3);
            difficulty=(Spinner)findViewById(R.id.spinner4) ;
            Play=(Button)findViewById(R.id.button2);

            setAll();

            Row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int X=(int) parent.getSelectedItem();
                    if(X==3) mrow=5;
                    else if(X==5) mrow=9;
                    else if(X==6) mrow=11;
                    else if(X==7) mrow=13;
                    else if(X==4) mrow=7;



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
            Col.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int X=(int) parent.getSelectedItem();
                    if(X==3) mcol=5;
                    else if(X==5) mcol=9;
                    else if(X==6) mcol=11;
                    else if(X==7) mcol=13;
                    else if(X==4) mcol=7;




                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
            Player.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String P=(String) parent.getSelectedItem();
                    mplay=P;
                    if(mplay.equals("vs Com"))
                    {
                        difficulty.setVisibility(View.VISIBLE);
                    }
                    else difficulty.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
            difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mMode=(String) parent.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(gameset.this,MainActivity.class);
                    intent.putExtra("col",mcol);
                    intent.putExtra("row",mrow);
                    intent.putExtra("player",mplay);
                    intent.putExtra("mode",mMode);
                    finish();
                    startActivity(intent);
                }
            });


        }
        public void setAll(){
            ArrayList<Integer> rowList = new ArrayList<Integer>();
            for(int i=4;i<8;++i)
            {
                rowList.add(i);
            }
            ArrayAdapter<Integer> rowAdapter =new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,rowList);
            Row.setAdapter(rowAdapter);
            ArrayList<Integer> colList = new ArrayList<Integer>();
            for(int i=4;i<8;++i)
            {
                colList.add(i);
            }
            ArrayAdapter<Integer> colAdapter =new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,colList);
            Col.setAdapter(colAdapter);
            ArrayList<String> playList=new ArrayList<String>();
            playList.add("2 player");
            playList.add("3 player");
            playList.add("4 player");
            playList.add("vs Com");
            ArrayAdapter<String> PlayAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,playList);
            Player.setAdapter(PlayAdapter);
            ArrayList<String> mode=new ArrayList<String>();
            mode.add("Easy");
            mode.add("Hard");
            ArrayAdapter<String> modeAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mode);
            difficulty.setAdapter(modeAdapter);

        }



}
