
package page.page1;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main_page extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button course_evaluation_page;
    private Button recruit_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        RadioButton btn4 = (RadioButton)findViewById(R.id.button_main_page);
        RadioButton btn6 = (RadioButton)findViewById(R.id.button_self_center);
        Button btn7 = (Button)findViewById(R.id.course_evaluation_page);
        Button btn8 = (Button)findViewById(R.id.recruit_page);

        btn4.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_evaluation_page:
                Intent KindIntent1 = new Intent(main_page.this, courseEvaluation.class);
                startActivity(KindIntent1);
                break;
            case R.id.recruit_page:
                Intent KindIntent2 = new Intent(main_page.this, recruitment.class);
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

