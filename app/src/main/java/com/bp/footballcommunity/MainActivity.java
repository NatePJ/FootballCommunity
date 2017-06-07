package com.bp.footballcommunity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bp.footballcommunity.adapters.FocusAdapter;
import com.bp.footballcommunity.items.ItemFocus;
import com.bp.footballcommunity.news.NewsBitmap;
import com.bp.footballcommunity.news.NewsFocus;
import com.bp.footballcommunity.utilities.NewsToItemFocus;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ArrayList<Contact> contacts;
    private ArrayList<ItemFocus> mItemFocuses;
    private ArrayList<NewsFocus> mNewsFocuses = new ArrayList<NewsFocus>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);

        /**
         * 初始化列表
         */
        contacts = Contact.createContactsList(5);
        contacts.addAll(Contact.createContactsList(30));

//        mNewsFocuses.add(new NewsFocus(R.drawable.headimage, "hello", "yes we are", R.drawable.body_focus_image_test, NewsBitmap.mBitmap));
//        mNewsFocuses.add(new NewsFocus(R.drawable.headimage, "hello", R.drawable.body_focus_image_test,NewsBitmap.mBitmap));
//        mNewsFocuses.add(new NewsFocus(R.drawable.headimage, "hello", "yes we are",NewsBitmap.mBitmap));
        mItemFocuses = NewsToItemFocus.newsToItemFocus(mNewsFocuses);

        /**
         * 设置适配器
         */
//        ContactsAdapter adapter = new ContactsAdapter(this,contacts);
//        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(View itemView, int position) {
//                String name = contacts.get(position).getName();
//                Toast.makeText(MainActivity.this,name+"was clicked",Toast.LENGTH_SHORT).show();
//            }
//        });
        Log.d(TAG,"3.3");
        FocusAdapter focusAdapter = new FocusAdapter(this,mItemFocuses);
        Log.d(TAG,"3.4");
        mRecyclerView.setAdapter(focusAdapter);

        /**
         * 布局管理器
         */
        Log.d(TAG,"3.5");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);
        Log.d(TAG,"3.6");
        /**
         * 一排显示几个
         */
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
//        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        gridLayoutManager.scrollToPosition(6);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

        /**
         * We can decorate the items using various decorators attached to the recyclerview
         * such as the DividerItemDecoration:
         */
//        RecyclerView.ItemDecoration itemDecoration = new
//                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        mRecyclerView.addItemDecoration(itemDecoration);

        /**
         * 点击事件
         */
//        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
//            @Override
//            public void onTouchEvent(RecyclerView recycler, MotionEvent event){
//                //TODO Handle on touch events here
//                String name = contacts.get(5).getName();
//                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView recycler, MotionEvent event){
//                return false;
//            }
//        });

        /**
         *
         */
//        contacts.add(0,new Contact("Barney",true,1));
//        adapter.notifyItemInserted(contacts.size() - 1);
////        mRecyclerView.scrollToPosition(adapter.getItemCount() - 1);//页面开始的位置
//        mRecyclerView.setHasFixedSize(true);//优化
        /**
         *
         */

        // record this value before making any changes to the existing list
//        int curSize = adapter.getItemCount();
        // replace this line with wherever you get new records
//        ArrayList<Contact> newItems = Contact.createContactsList(5);


        // update the existing list
//        contacts.addAll(newItems);
        // curSize should represent the first element that got added
        // newItems.size() represents the itemCount
//        adapter.notifyItemRangeInserted(5,newItems.size());
    }

}
