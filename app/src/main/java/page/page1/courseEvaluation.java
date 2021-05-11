package page.page1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class courseEvaluation extends AppCompatActivity implements View.OnClickListener{

    String TABLENAME = "iteminfo";
    Intent intent;
    byte[] imagedata;
    Bitmap imagebm;
    private EditText et_name;

    private myAdapter mAdapter;
    private android.widget.ListView lv;
    public static LinkedList<CommentData> mList = new LinkedList<CommentData>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mList = new LinkedList<CommentData>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_evaluation);
        this.lv = (ListView) findViewById(R.id.listView);
        SearchView searchView;


        //先setAdapter再notifyDataChanged

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
        mAdapter = new myAdapter<CommentData>(mList, courseEvaluation.this, R.layout.listitem) {
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

        ImageView kind1 = (ImageView) findViewById(R.id.kind1);
        kind1.setOnClickListener(this);
        ImageView kind2 = (ImageView) findViewById(R.id.kind2);
        kind2.setOnClickListener(this);
        ImageView kind3 = (ImageView) findViewById(R.id.kind3);
        kind3.setOnClickListener(this);
        ImageView kind4 = (ImageView) findViewById(R.id.kind4);
        kind4.setOnClickListener(this);
        ImageView kind5 = (ImageView) findViewById(R.id.kind5);
        kind5.setOnClickListener(this);



        // 为列表项设置监听器
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(courseEvaluation.this, item_info.class);
                intent.putExtra("cid", mList.get(position).getCid()); // 获取该列表项的key为id的键值，即商品的id，将其储存在Bundle传递给打开的页面
                startActivity(intent);
            }
        });


        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String query) {

                                                  Cursor search_cursor = db.query(TABLENAME,null,"course_code like ? OR prof_name like ?",new String[]{"%"+query+"%", "%"+query+"%"},null,null,null,null); // 数据库查询
                                                  //Cursor search_cursor = db.rawQuery("SELECT * FROM iteminfo where course_code like '%?%' OR prof_name like '%?%'", new String[]{query, query});
                                                  mList.clear();
                                                  if (search_cursor.moveToFirst()){
                                                      while (!search_cursor.isAfterLast()) {
                                                          CommentData cd = new CommentData();  // 为列表项赋值
                                                          cd.setS_id(search_cursor.getInt(0));
                                                          cd.setCourse_code(search_cursor.getString(1));
                                                          cd.setProf_name(search_cursor.getString(2));
                                                          cd.setComment(search_cursor.getString(3));
                                                          cd.setCid();
                                                          mList.add(cd);
                                                          search_cursor.moveToNext();
                                                      }
                                                  }
                                                  mAdapter.notifyDataSetChanged();
                                                  return true;
                                              }


                                              @Override
                                              public boolean onQueryTextChange(String newText) {
                                                  //    adapter.getFilter().filter(newText);
                                                  return false;
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
                Intent KindIntent1 = new Intent(this,HSS.class);
                startActivity(KindIntent1);
                break;
            case R.id.kind2:
                Intent KindIntent2 = new Intent(this,SSE.class);
                startActivity(KindIntent2);
                break;
            case R.id.kind3:
                Intent KindIntent3 = new Intent(this,SME.class);
                startActivity(KindIntent3);
                break;
            case R.id.kind4:
                Intent KindIntent4 = new Intent(this,SDS.class);
                startActivity(KindIntent4);
                break;
            case R.id.kind5:
                Intent KindIntent5 = new Intent(this,LHS.class);
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



