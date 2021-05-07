package page.page1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;


public abstract class myAdapter<T> extends BaseAdapter {

    private Context mContext;
    private LinkedList<T> mData;
    protected int mLayoutId;

    public myAdapter(LinkedList<T> list, Context context, int layoutId) {
        mData = list;
        mContext = context;
        mLayoutId=layoutId;
    }

    public void refresh(LinkedList<T> list) {
        mData = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(mContext, mLayoutId, convertView, parent);
        convertView(holder, mData.get(position));
        return holder.getConvertView();
    }

    protected abstract void convertView(ViewHolder holder, T Data);


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

}
