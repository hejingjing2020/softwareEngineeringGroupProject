package page.page1;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String dbname="mydb.dp";
    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address

        db.execSQL("create table if not exists users" +
                "(userId varchar(20) primary key," +
                "passWord varchar(20) not null," +
                "name varchar(20)," +
                "subject varchar(20)," +
                "phone varchar(15)," +
                "qq varchar(15)," +
                "address varchar(50))");


        //商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
        db.execSQL("create table if not exists iteminfo(" +
                "s_id varchar(100) not null," +
                "course_code varchar(20) not null," +
                "prof_name varchar(20) not null," +
                "comment varchar(1000) not null," +
                "cid varchar(120) not null," +
                "primary key(cid))");
        //评论者账号userId，评论商品编号itemId，评论内容comment，评论时间time
        db.execSQL("create table if not exists comments(" +
                "userId varchar(100)," +
                "cid varvhar(120)," +
                "comment varchar(1000)," +
                "time DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

