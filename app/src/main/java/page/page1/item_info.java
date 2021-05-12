package page.page1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static page.page1.LoginMainActivity.post_userid;

public class item_info extends AppCompatActivity {
    // 这里是展示之前选中的课程评价的
    String TABLENAME;
    byte[] imagedata;
    Bitmap imagebm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);
        final DatabaseHelper dbtest = new DatabaseHelper(this);
        final Intent intent = getIntent();
        final SQLiteDatabase db = dbtest.getWritableDatabase();
        ImageView image = (ImageView)findViewById(R.id.imageView);
        TextView price = (TextView)findViewById(R.id.item_price);
        TextView title = (TextView)findViewById(R.id.item_title) ;
        TextView info = (TextView)findViewById(R.id.item_info);
        TextView contact = (TextView)findViewById(R.id.contact);
 // 根据接收到的id进行数据库查询
        Cursor cursor;
        if (intent.getStringExtra("cid")!=null) {
            cursor = db.query("iteminfo", null, "cid=?", new String[]{intent.getStringExtra("cid")}, null, null, null, null); // 根据接收到的id进行数据库查询
        }
        else {

            cursor = db.query("recruitment", null, "rid=?", new String[]{intent.getStringExtra("rid")}, null, null, null, null);
        }
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                title.setText(cursor.getString(1));
                price.setText(cursor.getString(2));
                info.setText(cursor.getString(3));
                cursor.moveToNext();
            }
        }

        String pk;
        if (intent.getStringExtra("cid")!=null) {
            pk = pk = intent.getStringExtra("cid");
            TABLENAME = "iteminfo";
        }
        else {
            pk = intent.getStringExtra("rid");
            TABLENAME = "recruitment";
        }

        Button delete = (Button)findViewById(R.id.delete);
        String finalPk = pk;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TABLENAME=="iteminfo") {
                    db.delete(TABLENAME, "cid=?", new String[]{finalPk});
                }
                else {
                    db.delete(TABLENAME, "rid=?", new String[]{finalPk});
                }
                Intent intent_=new Intent(item_info.this,MyRelease.class);
                startActivity(intent_);
            }
        });
    }
}