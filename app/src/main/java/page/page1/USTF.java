package page.page1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USTF extends AppCompatActivity implements View.OnClickListener {
    String TABLENAME = "recruitment";
    byte[] imagedata;
    Bitmap imagebm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_s_t_f);
        DatabaseHelper dbtest = new DatabaseHelper(this);
        final SQLiteDatabase db = dbtest.getWritableDatabase();
        ListView listView = (ListView)findViewById(R.id.kind_list1);
        Map<String, Object> item = new HashMap<String, Object>();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query(TABLENAME,null,"type=?",new String[]{"USTF"},null,null,null,null); // 数据库查询
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                item = new HashMap<String, Object>();  // 为列表项赋值
                item.put("id",cursor.getInt(0));
                item.put("userid",cursor.getString(1));
                item.put("title",cursor.getString(2));
                item.put("kind",cursor.getString(3));
                item.put("info",cursor.getString(4));
                item.put("price",cursor.getString(5));
                imagedata = cursor.getBlob(6);
                imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
                //kind1.setImageBitmap(imagebm);
                item.put("image",imagebm);
                cursor.moveToNext();
                data.add(item); // 加入到列表中
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.listitem, new String[] { "image", "title", "kind", "info", "price" },
                new int[] { R.id.item_image, R.id.title, R.id.kind, R.id.info, R.id.price });
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView iv = (ImageView)view;
                    iv.setImageBitmap( (Bitmap)data );
                    return true;
                }else{
                    return false;
                }
            }
        });
        listView.setAdapter(simpleAdapter);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_main_page:
                Intent button1 = new Intent(this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;

        }
    }
}
