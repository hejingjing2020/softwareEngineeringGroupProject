package page.page1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutMainActivity extends AppCompatActivity {
    private Button cometo;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_main);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_home:
                Intent KindIntent1 = new Intent(AboutMainActivity.this, main_page.class);
                startActivity(KindIntent1);
                break;

        }
    }

}