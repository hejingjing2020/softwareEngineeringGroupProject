package page.page1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class courseEvaluation extends AppCompatActivity implements View.OnClickListener{

    String TABLENAME = "iteminfo";
    Intent intent;
    byte[] imagedata;
    Bitmap imagebm;
    private EditText et_name;

    private myAdapter mAdapter;
    private android.widget.ListView lv;
    private ImageButton last_page;
    private ImageButton next_page;
    private int VIEW_COUNT = 5; //每页显示条目数
    private int index = 0;  //当前页数索引
    public static LinkedList<CommentData> mList = new LinkedList<CommentData>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mList = new LinkedList<CommentData>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_evaluation);
        this.lv = (ListView) findViewById(R.id.listView);


        //先setAdapter再notifyDataChanged

        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();




        Map<String, Object> item;  // 列表项内容用Map存储
        final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(); // 列表
        Cursor cursor = db.query(TABLENAME,null,null,null,null,null,null,null); // 数据库查询


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
        mAdapter = new myAdapter<CommentData>(mList, courseEvaluation.this, R.layout.listitem) {
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

        // 使用SimpleAdapter布局 listview
        // listitem.xml
        //为什么不能用simpleAdapter？因为它的信息是hard-coded在listitem.xml里的

        ImageView kind1 = (ImageView) findViewById(R.id.kind1);
        kind1.setOnClickListener(this);
        ImageView kind2 = (ImageView) findViewById(R.id.kind2);
        kind2.setOnClickListener(this);
        ImageView kind3 = (ImageView) findViewById(R.id.kind3);
        kind3.setOnClickListener(this);
        ImageView kind4 = (ImageView) findViewById(R.id.kind4);
        kind4.setOnClickListener(this);



        // 为列表项设置监听器
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(courseEvaluation.this, item_info.class);
                intent.putExtra("cid", mList.get(position).getCid()); // 获取该列表项的key为id的键值，即商品的id，将其储存在Bundle传递给打开的页面
                startActivity(intent);
            }
        });

        RadioButton btn1 = (RadioButton)findViewById(R.id.button_main_page);
        RadioButton btn2 = (RadioButton)findViewById(R.id.button_post_comment);
        RadioButton btn3 = (RadioButton)findViewById(R.id.button_self_center);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        last_page = (ImageButton) findViewById(R.id.last_page);
        next_page = (ImageButton) findViewById(R.id.next_page);

        last_page.setOnClickListener(this);
        next_page.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kind1:
                Intent KindIntent1 = new Intent(this, HSS.class);
                startActivity(KindIntent1);
                break;
            case R.id.kind2:
                Intent KindIntent2 = new Intent(this, SSE.class);
                startActivity(KindIntent2);
                break;
            case R.id.kind3:
                Intent KindIntent3 = new Intent(this, SME.class);
                startActivity(KindIntent3);
                break;
            case R.id.kind4:
                Intent KindIntent4 = new Intent(this, SDS.class);
                startActivity(KindIntent4);
                break;
            case R.id.kind5:
                Intent KindIntent5 = new Intent(this, LHS.class);
                startActivity(KindIntent5);
                break;
            case R.id.button_main_page:
                Intent button1 = new Intent(courseEvaluation.this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_post_comment:
                Intent button2 = new Intent(courseEvaluation.this, releaseCourseEvaluation.class);
                startActivity(button2);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;


        }

    }
}


