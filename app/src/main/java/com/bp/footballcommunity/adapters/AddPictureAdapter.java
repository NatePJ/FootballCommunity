package com.bp.footballcommunity.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bp.footballcommunity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BP on 2017/6/4.
 */
public class AddPictureAdapter extends BaseAdapter{
    private Context context;
    private List<Bitmap> data;
    private LayoutInflater inflater;
    private GridView mGridView;
    private int gridViewH;
    private int imageViewH;

    public AddPictureAdapter(Context context, List<Bitmap> data, GridView mGridView) {
        this.context = context;
        this.data = data;
        Log.d("data.size()", String.valueOf(data.size()));
        this.mGridView = mGridView;
        inflater = LayoutInflater.from(context);
        LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) mGridView.getLayoutParams();
        gridViewH = params.height;
        Log.i("LJC", gridViewH + "");

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.griditem, null);
            holder = new Holder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            // 获取到ImageView的高度
            RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) holder.imageView
                    .getLayoutParams();
            imageViewH = params.height;
            Log.d("imageViewH.size()", String.valueOf(imageViewH));
            convertView.setTag(holder);
        } else {
            Log.d("imageViewH.size()", "erro");
            setGridView();
            holder = (Holder) convertView.getTag();
        }

        //TODO 作出修改
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // 绑定图片原始尺寸，方便以后应用
//        if(data != null) {
//            int[] parameter = {data.get(position).getWidth(), data.get(position).getHeight()};
//            holder.imageView.setTag(parameter);
            holder.imageView.setImageBitmap(data.get(position));
//        }
        return convertView;
    }

    private void setGridView() {
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mGridView.getLayoutParams();
        if (data.size() < 4) {
            lp.height = gridViewH;
        } else if (data.size() < 8) {
            lp.height = gridViewH * 2 - (gridViewH - imageViewH) / 2;
        } else {
            lp.height = gridViewH * 3 - (gridViewH - imageViewH);
        }
        mGridView.setLayoutParams(lp);
    }

    class Holder {
        private ImageView imageView;
    }
}
