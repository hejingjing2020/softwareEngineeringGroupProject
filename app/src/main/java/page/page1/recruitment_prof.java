package page.page1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class recruitment_prof extends AppCompatActivity implements View.OnClickListener{

    String TABLENAME = "Comment";
    Intent intent;
    byte[] imagedata;
    Bitmap imagebm;
    private EditText et_name;
    private LinkedList<CommentData> mList;
    private myAdapter mAdapter;
    private ListView mListView;
    private Handler mHandler;

    /*
    @Override
    protected void onResume() {

        super.onResume();

        onCreate(null);

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_prof);


        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();
        ListView listView = (ListView)findViewById(R.id.listView);
        mList = new LinkedList<CommentData>();

        mAdapter = new myAdapter<CommentData>(mList, recruitment_prof.this, R.layout.listitem) {
            @Override
            protected void convertView(ViewHolder holder, CommentData Data) {

            }
        };
        //这里在每次更新数据时刷新listView
        //listView.setAdapter(mAdapter);


        Map<String, Object> item;  // 列表项内容用Map存储
        final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(); // 列表
        Cursor cursor = db.query(TABLENAME,null,null,null,null,null,null,null); // 数据库查询


        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                item = new HashMap<String, Object>();  // 为列表项赋值
                item.put("s_id",cursor.getInt(0));
                item.put("course_code",cursor.getString(1));
                item.put("prof_name",cursor.getString(2));
                item.put("comment",cursor.getString(3));
                //imagedata = cursor.getBlob(6);
                //item.put("image",imagebm);
                cursor.moveToNext();
                data.add(item); // 加入到列表中
            }
        }
        // 使用SimpleAdapter布局 listview
        // listitem.xml
        //myAdapter mAdapter = new myAdapter();

        ImageView kind1 = (ImageView) findViewById(R.id.kind1);
        kind1.setOnClickListener(this);
        ImageView kind2 = (ImageView) findViewById(R.id.kind2);
        kind2.setOnClickListener(this);
        ImageView kind3 = (ImageView) findViewById(R.id.kind3);
        kind3.setOnClickListener(this);
        ImageView kind4 = (ImageView) findViewById(R.id.kind4);
        kind4.setOnClickListener(this);



        // 为列表项设置监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent = new Intent(recruitment_prof.this, item_info.class);
                intent.putExtra("id", data.get(position).get("id").toString()); // 获取该列表项的key为id的键值，即商品的id，将其储存在Bundle传递给打开的页面
                startActivity(intent);
            }
        });

        RadioButton btn1 = (RadioButton)findViewById(R.id.button_main_page);
        RadioButton btn2 = (RadioButton)findViewById(R.id.button_post_comment);
        RadioButton btn3 = (RadioButton)findViewById(R.id.button_self_center);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }
    private ArrayList<Map<String, Object>> getData(){
        ArrayList<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        Map<String,Object> hashmap=new HashMap<String, Object>();
        for(int i=0;i<5;i++){
            hashmap.put("key1", "data1");
            hashmap.put("key2", "data2");
            list.add(hashmap);
        }
        return list;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.kind1:
                Intent KindIntent1 = new Intent(this,RA.class);
                startActivity(KindIntent1);
                break;
            case R.id.kind2:
                Intent KindIntent2 = new Intent(this,USTF.class);
                startActivity(KindIntent2);
                break;
            case R.id.button_main_page:
                Intent button1 = new Intent(recruitment_prof.this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_post_comment:
                Intent button2 = new Intent(recruitment_prof.this, releaseRecruitment.class);
                startActivity(button2);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;

        }
    }
}
