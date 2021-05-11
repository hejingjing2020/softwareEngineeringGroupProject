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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SSE extends AppCompatActivity {
    String TABLENAME = "iteminfo";
    byte[] imagedata;
    Bitmap imagebm;
    private android.widget.ListView lv;
    private myAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LinkedList<CommentData> mList = new LinkedList<CommentData>();
        setContentView(R.layout.activity_s_s_e);

        this.lv = (ListView) findViewById(R.id.kind_list1);//这个一定要放在setContentView后面，不然没法确定是哪个layout
        DatabaseHelper dbtest = new DatabaseHelper(this);
        final SQLiteDatabase db = dbtest.getWritableDatabase();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query(TABLENAME,null,"school=?",new String[]{"SSE"},null,null,null,null); // 数据库查询
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                CommentData cd = new CommentData();  // 为列表项赋值
                cd.setS_id(cursor.getInt(0));
                cd.setCourse_code(cursor.getString(1));
                cd.setProf_name(cursor.getString(2));
                cd.setComment(cursor.getString(3));
                cd.setCid();
                mList.add(cd);
                cursor.moveToNext();
            }
        }
        mAdapter = new myAdapter<CommentData>(mList, SSE.this, R.layout.listitem) {
            @Override
            public void convertView(ViewHolder holder, CommentData Data) {
                holder.set(R.id.title, Data.getCourse_code())
                        .set(R.id.kind, Data.getProf())
                        .set(R.id.info, Data.getComment());
            }
        };
        //这里在每次更新数据时刷新listView
        lv.setAdapter(mAdapter);//listView里应该是mList的内容
        mAdapter.notifyDataSetChanged();
        //先setAdapter再notifyDataChanged
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
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_main_page:
                Intent button1 = new Intent(SSE.this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;

        }
    }
}

