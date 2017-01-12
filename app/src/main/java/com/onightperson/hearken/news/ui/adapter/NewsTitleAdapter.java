package com.onightperson.hearken.news.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.onightperson.hearken.R;

import org.w3c.dom.Text;

/**
 * Created by liubaozhu on 17/1/10.
 */

public class NewsTitleAdapter extends ArrayAdapter {

    private int mRecoureId;
    private LayoutInflater mInflater;
    public NewsTitleAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        mRecoureId = resource;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(mRecoureId, parent, false);
            holder.titleView = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String title = (String) getItem(position);
        holder.titleView.setText(title);

        return convertView;
    }

    public class ViewHolder {
        public TextView titleView;
    }
}
