package page.page1;

import android.app.Activity;
import android.content.Intent;
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

    private EditText User;
    private EditText Password;
    private Button button_login;
    private TextView first;
    private TextView toRegister;
    private RadioButton radioGroupRole;
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
        post_userid="";
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
        DatabaseHelper dbhelper = new DatabaseHelper(this);
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