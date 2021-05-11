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

    String TABLENAME = "recruitment";
    Intent intent;
    byte[] imagedata;
    Bitmap imagebm;
    private EditText et_name;
    private LinkedList<RecruitmentData> mList;
    private myAdapter mAdapter;
    private ListView mListView;
    private Handler mHandler;
    private android.widget.ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_prof);
        this.lv = (ListView) findViewById(R.id.listView);


        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();
        mList = new LinkedList<RecruitmentData>();

        mAdapter = new myAdapter<RecruitmentData>(mList, recruitment_prof.this, R.layout.list_recruitment_item) {
            @Override
            protected void convertView(ViewHolder holder, RecruitmentData Data) {
                holder.set(R.id.title, Data.getTitle())
                        .set(R.id.kind, Data.getEmail())
                        .set(R.id.info, Data.getDescription());
            }
        };

        Map<String, Object> item;  // 列表项内容用Map存储
        final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(); // 列表
        Cursor cursor = db.query(TABLENAME,null,null,null,null,null,null,null); // 数据库查询


        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                RecruitmentData cd = new RecruitmentData();  // 为列表项赋值
                cd.setRid(cursor.getString(0));
                cd.setUid(cursor.getString(1));
                cd.setTitle(cursor.getString(2));
                cd.setEmail(cursor.getString(3));
                cd.setSalary(cursor.getString(4));
                cd.setType(cursor.getString(5));
                cd.setDescription(cursor.getString(6));
                mList.add(cd);
                cursor.moveToNext();
            }
        }

        lv.setAdapter(mAdapter);//listView里应该是mList的内容
        mAdapter.notifyDataSetChanged();
        // 为列表项设置监听器
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent = new Intent(recruitment_prof.this, item_info.class);
                intent.putExtra("rid", data.get(position).get("rid").toString()); // 获取该列表项的key为id的键值，即商品的id，将其储存在Bundle传递给打开的页面
                startActivity(intent);
            }
        });

        ImageView kind1 = (ImageView) findViewById(R.id.ra);
        kind1.setOnClickListener(this);

        ImageView kind2 = (ImageView) findViewById(R.id.ustf);
        kind2.setOnClickListener(this);

        RadioButton btn_main_page = (RadioButton)findViewById(R.id.button_main_page);
        RadioButton btn_self_center = (RadioButton)findViewById(R.id.button_self_center);

        RadioButton btn_post = (RadioButton)findViewById(R.id.button_post_comment);
        btn_post.setOnClickListener(this);
        btn_main_page.setOnClickListener(this);
        btn_self_center.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ra:
                Intent KindIntent2 = new Intent(this,RA.class);
                startActivity(KindIntent2);
                break;
            case R.id.ustf:
                Intent KindIntent3 = new Intent(this,USTF.class);
                startActivity(KindIntent3);
                break;
            case R.id.button_post_comment:
                Intent post = new Intent(this, releaseRecruitment.class);
                startActivity(post);
                break;
            case R.id.button_main_page:
                Intent button1 = new Intent(recruitment_prof.this, main_page.class);
                startActivity(button1);
                break;
            case R.id.button_self_center:
                Intent button3 = new Intent(this, MyselfActivity.class);
                startActivity(button3);
                break;

        }
    }
}
