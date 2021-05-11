package page.page1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static page.page1.LoginMainActivity.post_userid;


public class releaseRecruitment extends AppCompatActivity {
    public String job;
    private static final byte REQUEST_SYSTEM_PIC = 10;
    private Spinner sp;
    private ImageButton imageButton;
    private byte[] image;
    private RadioGroup selectedjob;
    private RadioButton USTF;
    private RadioButton RA;

    public releaseRecruitment() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_release_recruitment);
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        //dbHelper=new MyDatabaseHelper(this,"1600802129.db",null,1);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        Button post=(Button)findViewById(R.id.release_comment);
        selectedjob=(RadioGroup)findViewById((R.id.radioGroupJob)) ;
        USTF=(RadioButton) findViewById((R.id.radioustf));
        RA=(RadioButton) findViewById((R.id.radiora));
        post.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (RA.isChecked()){
                    job = "RA";
                }
                else if (USTF.isChecked()){
                    job = "USTF";
                }
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
                values.put("type", job);
                values.put("description", description.getText().toString());
                db.insert("recruitment",null,values);
                Intent intent=new Intent(releaseRecruitment.this, recruitment_prof.class);
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });

        ImageButton but1 = (ImageButton)findViewById(R.id.imageButton_home);
        ImageButton but2 = (ImageButton)findViewById(R.id.imageButton_info);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseRecruitment.this, MyRelease.class);
                startActivity(intent);
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(releaseRecruitment.this,main_page.class);
                startActivity(intent);
            }
        });

    }

}
