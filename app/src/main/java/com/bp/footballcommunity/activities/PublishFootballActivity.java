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
    //照片路径
    private String photoPath;
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
                    // 这里开始的第二部分，获取图片的路径：
//                    String[] proj = { MediaStore.Images.Media.DATA };
//                    Cursor cursor = managedQuery(uri, proj, null, null, null);
                    // 按我个人理解 这个是获得用户选择的图片的索引值
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    cursor.moveToFirst();
                    // 最后根据索引值获取图片路径
                    //TODO 修改
//                    Log.d(TAG,)
//                    photoPath = cursor.getString(column_index);
                    /**
                     * 上传图片
                     */
                    String[] proj = { MediaStore.Images.Media.DATA };

                    Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);

                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    actualimagecursor.moveToFirst();

                    final String img_path = actualimagecursor.getString(actual_image_column_index);



                    String url = "http://192.168.137.1:8080/SSHProject/file.png";
                    Log.d("downloadpicfile","1");
                    AsyncHttpClient client = new AsyncHttpClient();
                    Log.d("downloadpicfile","2");
                    client.get(url, new FileAsyncHttpResponseHandler(this) {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, File response) {
                            try {//此try-catch块中的代码将下载到的文件保存在一个目录下
                                // Context.getFilesDir()，该方法返回/data/data/[youPackageName]/files的File对象
                                FileOutputStream fileOutputStream = new FileOutputStream(
                                        PublishFootballActivity.this.getFilesDir().getPath() + "/file.png");
                                Log.d("downloadpicfile",PublishFootballActivity.this.getFilesDir().getPath() + "/file.png");
                                FileInputStream fileInputStream = new FileInputStream(response);
                                byte[] bytes = new byte[(int) response.length()];
                                fileInputStream.read(bytes);
                                fileOutputStream.write(bytes);
                                Log.d("downloadpicfile",PublishFootballActivity.this.getFilesDir().getPath() + "/file.png");
                                fileOutputStream.flush();
                                fileInputStream.close();
                                fileOutputStream.close();
                            } catch (Exception e) {
                                Log.d("downloadpicfile","fail");
                                e.printStackTrace();
                            }

                            /*********do something ...... ********/
                            /***************上传部分***************/
                            /*********do something ...... ********/
                /*上传部分*/
                            //找到上传的靶文件（此时的靶文件应该是我们刚刚下载到的文件）
//                File file = new File(UpLoadActivity.this.getFilesDir().getPath(),"file.png");
                            File file1 = new File("/data/user/0/com.bp.footballcommunity/files/headimage6.png");

                            File file = new File("/storage/emulated/0/Pictures/headimage6.png");
                            Log.d("downloadpicfile",PublishFootballActivity.this.getFilesDir().getPath());
                            Log.d("downloadpicfile0", String.valueOf(file));
                            Log.d("downloadpicfile1", String.valueOf(file1));
                            //封装待发送参数（以键值对形式），即靶文件，内容类型设置为multipart/form-data
                            RequestParams params = new RequestParams();
                            try {
                                params.put("file", file, "multipart/form-data");
                                params.put("filename","headimage6.png");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            //没错，又是它
                            AsyncHttpClient uploader = new AsyncHttpClient();

                            //这次是post请求，看清楚，多了第二个参数params，其实也就是被封装的靶文件啦
                            //这次使用的响应体接收者是AsyncHttpResponseHandler，回调处理也简单，就是现实上传成功与否
                            Log.d("uploadpicfile", "upload test");
                            uploader.post("http://192.168.137.1:8080/SSHProject/uploadpicture", params,
                                    new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                                            Log.d("uploadpicfile", "upload success");

                                        }
                                        @Override
                                        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                                            Log.d("uploadpicfile", "upload failure");

                                        }
                                    });

                        }
                        //下载失败时回调onFailure方法
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                            Log.i("file", "failure" + statusCode);
                        }
                    });
                    /**
                     * 上传图片
                     */
                    photoPath = getRealPathFromURI(uri);
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

            Bitmap newBp = BitmapUtils.decodeSampledBitmapFromFd(photoPath, 300, 300);

            Log.d(TAG, photoPath);
            Log.d(TAG, String.valueOf(newBp));
            data.remove(data.size() - 1);
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
            Bitmap bp2 = BitmapFactory.decodeResource(getResources(), R.drawable.add_picture);

            data.add(mBitmap);
            data.add(bp);
            //将路径设置为空，防止在手机休眠后返回Activity调用此方法时添加照片
            photoPath = null;
            adapter = new AddPictureAdapter(getApplicationContext(), data, mGridView);
            mGridView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
        }
    }
}
