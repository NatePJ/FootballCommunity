package com.bp.footballcommunity.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bp.footballcommunity.MainActivity;
import com.bp.footballcommunity.R;
import com.bp.footballcommunity.adapters.AddPictureAdapter;
import com.bp.footballcommunity.utilities.BitmapUtils;
import com.bp.footballcommunity.utilities.UploadUtility;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BP on 2017/6/3.
 */
public class PublishFootballActivity extends Activity{
    private static final String TAG = "PublishFootballActt";
    /**
     * 申明布局组件
     */
    private List<Bitmap> data = new ArrayList<Bitmap>();
    private GridView mGridView;
    private EditText mEditText;
    private Button mButton;
    //照片路径
    private String photoPath;
    private String upPhotoPath;
    private Bitmap mBitmap;

    /**
     * 照片适配器
     */
    private AddPictureAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_football_time);

        // 设置默认图片为加号
        Bitmap bp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_addpic);
        data.add(bp);
        //找到控件ID
        mEditText = (EditText) findViewById(R.id.content_et);
        mButton = (Button) findViewById(R.id.send_btn);
        mGridView = (GridView) findViewById(R.id.gridView1);
        // 绑定Adapter
        adapter = new AddPictureAdapter(getApplicationContext(), data, mGridView);
        mGridView.setAdapter(adapter);
        // 设置点击监听事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data.size() == 10) {
                    Toast.makeText(PublishFootballActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                } else {
                    if (position == data.size() - 1) {
                        Toast.makeText(PublishFootballActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
                        // 选择图片
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, 0x1);
                    } else {
                        Toast.makeText(PublishFootballActivity.this, "点击第" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadUtility.uploadFootballMessage(upPhotoPath,mEditText.getText().toString());
//                Intent i = new Intent(PublishFootballActivity.this,ParentActivity.class);
//                startActivity(i);
            }
        });

        //设置长按监听事件
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog(position);
                return true;
            }

        });
    }

    /**
     * 获取真实的Uri
     * @param contentURI
     * @return
     */
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    /**
     * Dialog对话框提示用户删除操作 position为删除图片位置
     * @param position
     */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublishFootballActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 响应startActivityForResult，获取图片路径
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x1 && resultCode == RESULT_OK) {
            if (data != null) {

                ContentResolver resolver = getContentResolver();
                try {
                    Uri uri = data.getData();
                    photoPath = getRealPathFromURI(uri);
                    upPhotoPath = getRealPathFromURI(uri);
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    Log.d(TAG, String.valueOf(uri));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(photoPath)) {

            Log.d(TAG, photoPath);
            data.remove(data.size() - 1);
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
            data.add(mBitmap);
            data.add(bp);
            //将路径设置为空，防止在手机休眠后返回Activity调用此方法时添加照片
            photoPath = null;
            adapter = new AddPictureAdapter(getApplicationContext(), data, mGridView);
            mGridView.setAdapter(adapter);
        }
    }
}
