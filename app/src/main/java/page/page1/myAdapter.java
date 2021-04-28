package page.page1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;


public class myAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<CommentData> mData;

    public myAdapter(LinkedList<CommentData> list, Context context) {
        mData = list;
        mContext = context;
    }

    public void refresh(LinkedList<CommentData> list) {
        mData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
