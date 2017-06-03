package com.bp.footballcommunity.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bp.footballcommunity.R;
import com.bp.footballcommunity.items.ItemFocus;
import com.bp.footballcommunity.utilities.Constant;

import java.util.List;

/**
 * Created by BP on 2017/6/1.
 */
public class FocusAdapter extends RecyclerView.Adapter<FocusAdapter.FocusViewHolder>{
    private static final String TAG = "FocusAdapter";
    private List<ItemFocus> mItemFocuses;
    private Context mContext;

    public FocusAdapter(Context context, List<ItemFocus> itemFocuses){
        mItemFocuses = itemFocuses;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public FocusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        FocusViewHolder focusViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);

        /**
         * 找到布局文件再传递给holder
         */
        switch (viewType){
            case Constant.HEAD:
                View headView = inflater.inflate(R.layout.item_focus_head,parent,false);
                focusViewHolder = new FocusViewHolder(headView,viewType);
                break;
            case Constant.BODYTEXT:
                View bodyTextView = inflater.inflate(R.layout.item_focus_text,parent,false);
                focusViewHolder = new FocusViewHolder(bodyTextView,viewType);
                break;
            case Constant.BODYIMAGE:
                View bodyImageView = inflater.inflate(R.layout.item_focus_image,parent,false);
                focusViewHolder = new FocusViewHolder(bodyImageView,viewType);
                break;
            case Constant.BUTTON:;
                View buttonView = inflater.inflate(R.layout.item_focus_button,parent,false);
                focusViewHolder = new FocusViewHolder(buttonView,viewType);
                break;
        }
        return focusViewHolder;
    }

    @Override
    public void onBindViewHolder(FocusViewHolder holder, int position) {
        /**
         * 把构造方法的list提取出来传入holder
         */
        ItemFocus itemFocus = mItemFocuses.get(position);
        // TODO Why
        /**
         * holder获取得组件赋值
         */
        switch(holder.getItemViewType()){
            case Constant.HEAD:
                ImageView headFocusImage = holder.headFocusImage;
                headFocusImage.setImageResource(itemFocus.getImageSource());
                TextView headFocusText = holder.headFocusText;
                headFocusText.setText(itemFocus.getText());
                break;
            case Constant.BODYTEXT:
                TextView bodyFocusText = holder.bodyFocusText;
                bodyFocusText.setText(itemFocus.getText());
                break;
            case Constant.BODYIMAGE:
                ImageView bodyFocusImage = holder.bodyFocusImage;
                bodyFocusImage.setImageResource(itemFocus.getImageSource());
                break;
            case Constant.BUTTON:;
                ImageButton commentFocusButton = holder.commentFocusButton;
                commentFocusButton.setImageResource(itemFocus.getImageComment());
                ImageButton likeFocusButton = holder.likeFocusButton;
                likeFocusButton.setImageResource(R.drawable.like_not_touch);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItemFocuses.size();
    }

    @Override
    public int getItemViewType(int position){
        return mItemFocuses.get(position).getStyleType();
    }
    /**
     * 监听器
     */
//    private OnItemClickListener listener;
//    public interface OnItemClickListener{
//        void onItemClick(View itemView,int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.listener = listener;
//    }

    /**
     * ViewHolder
     */
    public class FocusViewHolder extends RecyclerView.ViewHolder{
        /**
         * 定义布局文件
         */
        private ImageView headFocusImage;
        private TextView headFocusText;
        private TextView bodyFocusText;
        private ImageView bodyFocusImage;
        private ImageButton commentFocusButton;
        private ImageButton likeFocusButton;

        private Context context;

        public FocusViewHolder(final View itemView,int itemType) {
            super(itemView);

            /**
             * 接收adapter专递过来得View
             * 获取View中得组件
             */
            switch(itemType){
                case Constant.HEAD:
                    headFocusImage = (ImageView)itemView.findViewById(R.id.focus_head_image);
                    headFocusText = (TextView)itemView.findViewById(R.id.focus_head_text);
                    break;
                case Constant.BODYTEXT:
                    bodyFocusText = (TextView)itemView.findViewById(R.id.focus_body_text);
                    break;
                case Constant.BODYIMAGE:;
                    bodyFocusImage = (ImageView)itemView.findViewById(R.id.focus_body_image);
                    break;
                case Constant.BUTTON:
                    commentFocusButton = (ImageButton)itemView.findViewById(R.id.button_focus_comment);
                    likeFocusButton = (ImageButton)itemView.findViewById(R.id.button_focus_like);
            }
            this.context = getContext();

            /**
             * 监听器
             */
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(itemView,position);
//                        }
//                    }
//                }
//            });
        }
    }
}
