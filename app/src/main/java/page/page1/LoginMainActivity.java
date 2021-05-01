package page.page1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class LoginMainActivity extends Activity {

    private static final String TAG = "database_information";
    private EditText User;
    private DatabaseHelper dbhelper;
    private EditText Password;
    private Button button_login;
    private Button createDatabase;
    private Button addData;
    private Button queryData;
    private TextView first;
    private TextView toRegister;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    protected Intent intent;
    protected static String post_userid;
    String user=null;
    String password=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        User=(EditText)findViewById(R.id.login_user);
        Password=(EditText)findViewById(R.id.login_password);
        button_login=(Button)findViewById(R.id.login);
        toRegister=(TextView)findViewById(R.id.toRegister);
        createDatabase=(Button)findViewById(R.id.create_database);
        addData = (Button) findViewById(R.id.add_data);
        queryData = (Button) findViewById(R.id.query_data);
        Button createDatabase = (Button) findViewById(R.id.create_database);


        post_userid="";

       dbhelper = new DatabaseHelper(this,"current_lgu.dp", null, 2);

        createDatabase.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dbhelper.getWritableDatabase();
                //Toast.makeText(mContext, "Create successed", Toast.LENGTH_SHORT).show();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("userId", "1");
                values.put("itemId", "01");
                values.put("comment", "a");

                db.insert("comments", null, values);

                values.clear();
                values.put("userId", "2");
                values.put("itemId", "02");
                values.put("comment", "a");

                db.insert("comments", null, values);
                values.clear();
                values.put("userId", "3");
                values.put("itemId", "03");
                values.put("comment", "a");

                db.insert("comments", null, values);
                values.clear();

                Log.d(TAG, "insert");
                db.close();
            }
        });

/*
"userId varchar(100)," +
                "itemId integer," +
                "comment varchar(1000)," +
                "time DATETIME)");
 */
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String cur_str = "";
               Cursor cursor = db.query("comments", null,null,null,null,null,null);
               if (cursor.moveToFirst()){
                   do{
                       String name = cursor.getString(cursor.getColumnIndex("userId"));
                       int itemId = cursor.getInt(cursor.getColumnIndex("itemId"));
                       String comment = cursor.getString(cursor.getColumnIndex("comment"));
                       String time = cursor.getString(cursor.getColumnIndex("time"));

                       Log.d("Query","name is" + name);
                       Log.d("Query","itemId is" + itemId);

                       Log.d("Query","comment is" + comment);
                       Log.d("Query","time is" + time);
                       cur_str += "name: ";
                       cur_str += name;


                   }while (cursor.moveToNext());

               }
                Toast.makeText(getApplicationContext(), "" + cur_str, Toast.LENGTH_SHORT).show();

                cursor.close();


            }
        });



        //登录验证，成功后跳转到主页
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=User.getText().toString();
                password=Password.getText().toString();

                if(user.equals("")||user==null){
                    Toast.makeText(getApplicationContext(), "Please enter user account！", Toast.LENGTH_SHORT).show();
                }
                if(password.equals("")||password==null){
                    Toast.makeText(getApplicationContext(), "Please enter the user password！", Toast.LENGTH_SHORT).show();
                }
                checkUser(user,password);

            }
        });

        //跳转到注册页面
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginMainActivity.this,RegisterMainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void checkUser(String user,String password){
        DatabaseHelper dbhelper = new DatabaseHelper(this,"new.dp", null, 1);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        try{
            String sql="SELECT * FROM users WHERE userId=? and passWord=?";
            Cursor cursor=db.rawQuery(sql,new String[]{user,password});
            if(cursor.getCount()==0){
                Toast.makeText(getApplicationContext(), "The user password is wrong！", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginMainActivity.this,main_page.class);
                post_userid=user;
                startActivity(intent);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
    class MyOnClickListener implements View.OnClickListener {
        //按钮点击
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.login) {//被点击的是确认按钮
                //获取选中项
                RadioGroup radioGroup = findViewById(R.id.radioGroupRole);
                String role = "";
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioTeacher) {
                    role = "Teacher";
                } else {
                    role = "Student";
                }
            }
        }
    }


}