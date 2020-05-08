package com.example.dotsboxes;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LineView extends BaseAdapter {
    private Context mContext;
    ArrayList<Integer> mThumbIds;
    int Colum;
    int Row;
    int[] img={R.drawable.dot,R.drawable.blackhor,R.drawable.blackver,R.drawable.whitebox};
    int hl,hr,hu,hd,hw,vl,vr,vu,vd,vh,bl,br,bu,bd,hh;

    public LineView(Context c, ArrayList<Integer> ThumbsIds,int col,int row) {
        mContext = c;
        mThumbIds = ThumbsIds;
        Colum = col;
        Row=row;
        if(Colum==9){
            hu=15;hh=35;hw=100;
            vr=5;vd=5;vu=5;vd=5;vh=100;
            bd=15;bu=10;
        }
        else if(Colum==7){
            hu=15;hh=35;hw=140;
            vr=5;vd=5;vu=5;vd=5;vh=100;
            bd=15;bu=10;}

        else if(Colum==11){
            hu=15;hh=35;hw=90;
            vr=5;vd=5;vu=5;vd=5;vh=99;
            bd=0;bu=0;}
        else if(Colum==13){
            hu=15;hh=35;hw=70;
            vr=5;vd=5;vu=5;vd=5;vh=90;
            bd=0;bu=0;}
    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(mThumbIds.get(position)==img[0])
            {imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 5, 0, 5);}
            else if(mThumbIds.get(position)==img[1]){imageView.setLayoutParams(new GridView.LayoutParams(hw*1, hh*1));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, hu*1,0 , 0);
          }
            else if(mThumbIds.get(position)==img[2]){imageView.setLayoutParams(new GridView.LayoutParams(25,vh*1));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(5, 0, 5, 0);}
            else if(mThumbIds.get(position)==img[3]){imageView.setLayoutParams(new GridView.LayoutParams(hw*1, vh*1));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, bu*1, 0, bd*1);}
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
    }
}
