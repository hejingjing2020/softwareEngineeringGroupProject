package page.page1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static page.page1.LoginMainActivity.post_userid;




public class releaseRecruitment_RA extends AppCompatActivity {
    private static final byte REQUEST_SYSTEM_PIC = 10;
    //private static PreparedStatement dbHelper;

    private Spinner sp;
    private ImageButton imageButton;

    public releaseRecruitment_RA() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        setContentView(R.layout.activity_release_recruitment__u_s_t_f);
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        //dbHelper=new MyDatabaseHelper(this,"1600802129.db",null,1);

        String[] ctype = new String[]{"生活用品", "学习用品", "电子产品", "体育用品"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式


        Button fabu=(Button)findViewById(R.id.release_comment);
        fabu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText title=(EditText)findViewById(R.id.m1_title);
                EditText email=(EditText)findViewById(R.id.m1_email);
                EditText description=(EditText)findViewById(R.id.m1_description);
                EditText salary=(EditText)findViewById(R.id.m1_salary);
                Date curDate = new Date(System.currentTimeMillis());
                String time = formatter.format(curDate);
                ContentValues values=new ContentValues();
                values.put("rid", String.valueOf(post_userid)+title.getText());
                values.put("uid",post_userid);
                values.put("title", title.getText().toString());
                values.put("email", email.getText().toString());
                values.put("salary", salary.getText().toString());
                values.put("type", "RA");
                values.put("description", description.getText().toString());
                db.insert("recruitment",null,values);
                Intent intent=new Intent(releaseRecruitment_RA.this, courseEvaluation.class);
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });

        ImageButton but1 = (ImageButton)findViewById(R.id.imageButton_home);
        ImageButton but2 = (ImageButton)findViewById(R.id.imageButton_info);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseRecruitment_RA.this, MyRelease.class);
                startActivity(intent);
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseRecruitment_RA.this,main_page.class);
                startActivity(intent);
            }
        });

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
}
