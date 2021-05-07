package page.page1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static page.page1.LoginMainActivity.post_userid;




public class releaseCourseEvaluation extends AppCompatActivity {
    private static final byte REQUEST_SYSTEM_PIC = 10;
    //private static PreparedStatement dbHelper;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    //SQLiteDatabase db = dbHelper.getWritableDatabase();
    private Spinner sp;
    private ImageButton imageButton;
    private RadioButton radioGroupSchool;
    private byte[] image;

    public releaseCourseEvaluation() throws SQLException {
    }


    public List<HashMap<String,Object>> getinfo() throws SQLException {


        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();

        return list;


    }
        List<HashMap<String,Object>> listItem = getinfo();
        // activity_main_m1.l
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_release_course_evaluation);
       final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        //dbHelper=new MyDatabaseHelper(this,"courseEvaluation.db",null,1);
        dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] ctype = new String[]{"生活用品", "学习用品", "电子产品", "体育用品"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式


        imageButton=(ImageButton)findViewById(R.id.m1_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(releaseCourseEvaluation.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(releaseCourseEvaluation.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //打开系统相册
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }

            }
        });

        Button fabu=(Button)findViewById(R.id.release_comment);
        fabu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                    EditText title=(EditText)findViewById(R.id.m1_course_code);
                    EditText prof=(EditText)findViewById(R.id.m1_prof);
                    EditText s_id=(EditText)findViewById(R.id.m1_sid);
                    EditText comment=(EditText)findViewById(R.id.m1_comment);
                    Date curDate = new Date(System.currentTimeMillis());
                    String time = formatter.format(curDate);
                    ContentValues values=new ContentValues();
                    values.put("s_id",post_userid);
                    values.put("course_code", title.getText().toString());
                    values.put("prof_name", prof.getText().toString());
                    values.put("comment", comment.getText().toString());
                    values.put("cid", (String.valueOf(post_userid)+title.getText().toString()));
                    db.insert("iteminfo",null,values);
                    // int s_id, String course_code, String prof_name, String comment
                    //(courseEvaluation.mList).add(new CommentData(Integer.valueOf(post_userid), title.getText().toString(), prof.getText().toString(), comment.getText().toString()));
                    Intent intent=new Intent(releaseCourseEvaluation.this, courseEvaluation.class);
                    Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }
        });

        ImageButton butHome = (ImageButton)findViewById(R.id.imageButton_home);
        ImageButton butInfo = (ImageButton)findViewById(R.id.imageButton_info);

        butInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseCourseEvaluation.this, MyRelease.class);
                startActivity(intent);
            }
        });
        butHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseCourseEvaluation.this, main_page.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);/*
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        */
    }

    //加载图片
    private void showImage(String imaePath) {
        /*
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        image = baos.toByteArray();
        imageButton.setImageBitmap(bm);*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    class MyOnClickListener implements View.OnClickListener {
        //按钮点击
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.login) {//被点击的是确认按钮
                //获取选中项
                RadioGroup radioGroup = findViewById(R.id.radioGroupSchool);
                String role = "";
                if (radioGroup.getCheckedRadioButtonId() == R.id.radiohss) {
                    role = "HSS";
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radiosse) {
                    role = "SSE";
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radiosme) {
                    role = "SME";
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radiosds) {
                    role = "SDS";
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radiolhs) {
                    role = "LHS";
                }
            }
        }
    }
}
