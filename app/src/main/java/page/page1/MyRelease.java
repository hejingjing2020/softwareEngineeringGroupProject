package page.page1;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyRelease extends AppCompatActivity implements View.OnClickListener{
    String TABLENAME = "iteminfo";
    byte[] imagedata;
    Bitmap imagebm;
    private myAdapter mAdapter;
    private android.widget.ListView lv;
    public static LinkedList<CommentData> mList = new LinkedList<CommentData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();


        Cursor cursor = db.query(TABLENAME,null,null,null,null,null,null,null); // 数据库查询

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                CommentData cd = new CommentData();  // 为列表项赋值
                cd.setS_id(cursor.getInt(0));
                cd.setCourse_code(cursor.getString(1));
                cd.setProf_name(cursor.getString(2));
                cd.setComment(cursor.getString(3));
                cd.setCid();
                cd.setImage(cursor.getBlob(6));
                mList.add(cd);
                cursor.moveToNext();
            }
        }
        mAdapter = new myAdapter<CommentData>(mList, MyRelease.this, R.layout.listitem) {
            @Override
            public void convertView(ViewHolder holder, CommentData Data) {
                holder.set(R.id.title, Data.getCourse_code())
                        .set(R.id.kind, Data.getProf())
                        .set(R.id.info, Data.getComment());
                ImageView iv = findViewById(R.id.item_image);
                byte[] imagedata = Data.getImage();
                if (imagedata!=null) {
                    Bitmap imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
                    iv.setImageBitmap((Bitmap) imagebm);
                }

            }
        };
        //这里在每次更新数据时刷新listView
        lv.setAdapter(mAdapter);//listView里应该是mList的内容
        mAdapter.notifyDataSetChanged();
        /*
        item = new HashMap<String, Object>();
        item.put("id",1);
        item.put("userid","ysh");
        item.put("image", R.drawable.buy_item1);
        item.put("title","一个九成新的篮球");
        item.put("kind","体育用品");
        item.put("info", "刚买没多久，希望转卖出去...");
        item.put("price", "59元");
        data.add(item);
        item = new HashMap<String, Object>();
        item.put("id",2);
        item.put("userid","xg");
        item.put("image", R.drawable.buy_item2);
        item.put("title","一个八成新的篮球");
        item.put("kind","体育用品");
        item.put("info", "刚买没多久，希望转卖出去...");
        item.put("price", "59元");
        data.add(item);
        item = new HashMap<String, Object>();
        item.put("id",3);
        item.put("userid","hdq");
        item.put("image", R.drawable.buy_item3);
        item.put("title","一个八成新的篮球");
        item.put("kind","体育用品");
        item.put("info", "刚买没多久，希望转卖出去...");
        item.put("price", "59元");
        data.add(item);
        */
        // 使用SimpleAdapter布局listview


    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_main_page:
                Intent button1 = new Intent(MyRelease.this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;
        }
    }
}
