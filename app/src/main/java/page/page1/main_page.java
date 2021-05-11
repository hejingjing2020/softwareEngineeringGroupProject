package page.page1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class main_page extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();
        intent = getIntent();


        LinearLayout btn1 = (LinearLayout)findViewById(R.id.course_evaluation_page);
        LinearLayout btn2 = (LinearLayout)findViewById(R.id.recruit_page);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String role = intent.getStringExtra("role");
        switch (v.getId()) {
            case R.id.course_evaluation_page:
                Intent KindIntent1;
                if (role=="Student") {
                    KindIntent1 = new Intent(main_page.this, courseEvaluation.class);
                }
                else {
                    KindIntent1 = new Intent(main_page.this, courseEvaluation_prof.class);
                }
                startActivity(KindIntent1);
                break;
            case R.id.recruit_page:
                Intent KindIntent2;
                if (role=="Student") {
                    KindIntent2 = new Intent(main_page.this, recruitment.class);
                }
                else {
                    KindIntent2 = new Intent(main_page.this, recruitment_prof.class);
                }
                startActivity(KindIntent2);
                break;
            case R.id.button_main_page:
                Intent button1 = new Intent(this,main_page.class);
                startActivity(button1);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this,MyselfActivity.class);
                startActivity(button3);
                break;
        }
    }

}


