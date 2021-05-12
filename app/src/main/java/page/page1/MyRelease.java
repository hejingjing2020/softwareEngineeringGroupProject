package page.page1;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.LinkedList;

public class MyRelease extends AppCompatActivity implements View.OnClickListener{
    String TABLENAME = "iteminfo";
    byte[] imagedata;
    Bitmap imagebm;
    private myAdapter mAdapter;
    private android.widget.ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        DatabaseHelper database = new DatabaseHelper(this);
        final SQLiteDatabase db = database.getWritableDatabase();
        this.lv = (ListView) findViewById(R.id.show_post);


        Cursor cursor = db.query("iteminfo",null,null,null,null,null,null,null); // 数据库查询
        if (cursor.getCount()>0) {
            LinkedList<CommentData> mList = new LinkedList<CommentData>();
            if (cursor.moveToFirst()) {
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
                    if (imagedata != null) {
                        Bitmap imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
                        iv.setImageBitmap((Bitmap) imagebm);
                    }

                }
            };
            lv.setAdapter(mAdapter);//listView里应该是mList的内容
            mAdapter.notifyDataSetChanged();
        }
        else {
            cursor = db.query("recruitment",null,null,null,null,null,null,null);
            LinkedList<RecruitmentData> mList = new LinkedList<RecruitmentData>();
            if (cursor.moveToFirst()) {
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
            mAdapter = new myAdapter<RecruitmentData>(mList, MyRelease.this, R.layout.listitem) {
                @Override
                public void convertView(ViewHolder holder, RecruitmentData Data) {
                    holder.set(R.id.title, Data.getTitle())
                            .set(R.id.kind, Data.getEmail())
                            .set(R.id.info, Data.getDescription());
                }
            };
            lv.setAdapter(mAdapter);//listView里应该是mList的内容
            mAdapter.notifyDataSetChanged();
        }

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
