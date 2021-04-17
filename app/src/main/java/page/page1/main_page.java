package page.page1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Button;

public class main_page extends AppCompatActivity implements OnClickListener{

    Intent intent;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button course_evaluation_page;
    private Button recruit_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main_page);

        RadioButton btn4 = (RadioButton)findViewById(R.id.button_4);
        RadioButton btn5 = (RadioButton)findViewById(R.id.button_5);
        RadioButton btn6 = (RadioButton)findViewById(R.id.button_6);
        Button btn7 = (Button)findViewById(R.id.course_evaluation_page);
        Button btn8 = (Button)findViewById(R.id.recruit_page);

        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_evaluation_page:
                Intent KindIntent1 = new Intent(main_page.this, MainMainPageActivity.class);
                startActivity(KindIntent1);
                break;
            case R.id.recruit_page:
                Intent KindIntent2 = new Intent(main_page.this, MainMainPageActivity.class);
                startActivity(KindIntent2);
                break;
            case R.id.button_4:
                Intent button1 = new Intent(main_page.this,main_page.class);
                startActivity(button1);
                break;
            case R.id.button_5:
                Intent button2 = new Intent(main_page.this,AddItem.class);
                startActivity(button2);
                break;
            case R.id.button_6:
                Intent button3 = new Intent(main_page.this,MyselfActivity.class);
                startActivity(button3);
                break;
        }
    }
}



