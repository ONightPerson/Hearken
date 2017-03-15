package com.onightperson.hearken.listviewex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/3/13.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private String[] groupList = new String[] {
            "河南", "河北", "湖南", "湖北"
    };

    private String[][] childList = new String[][]{
            {"郑州", "新乡", "洛阳", "安阳", "焦作", "许昌", "平顶山", "漯河", "开封",
                    "濮阳", "鹤壁", "南阳", "三门峡", "驻马店", "商丘", "信阳", "周口"},
            {"秦皇岛", "唐山", "张家口", "承德", "廊坊", "沧州", "保定", "石家庄",
                    "衡水", "邢台", "邯郸"},
            {"长沙", "岳阳", "常德", "衡阳", "株洲", "郴州", "湘潭", "永州", "邵阳",
                    "益阳", "娄底", "怀化", "湘西州", "张家界"},
            {"武汉", "黄石", "十堰", "荆州", "宜昌", "襄阳", "鄂州", "荆门", "孝感",
                    "黄冈", "咸宁", "随州"}
    };

    private Context mContext;
    private int mGroupLayoutId;
    private int mChildLayoutId;

    public MyExpandableListAdapter(Context context, int groupLayoutId, int childLayoutId) {
        mContext = context;
        mGroupLayoutId = groupLayoutId;
        mChildLayoutId = childLayoutId;
    }


    @Override
    public int getGroupCount() {
        return groupList.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        GroupViewHoler groupViewHoler;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mGroupLayoutId, parent, false);
            groupViewHoler = new GroupViewHoler();
            groupViewHoler.groupTextView = (TextView) convertView.findViewById(R.id.group_title);
            convertView.setTag(groupViewHoler);
        } else {
            groupViewHoler = (GroupViewHoler) convertView.getTag();
        }

        String groupTitle = groupList[groupPosition];
        groupViewHoler.groupTextView.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHoler;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mChildLayoutId, parent, false);
            childViewHoler = new ChildViewHolder();
            childViewHoler.childTextView = (TextView) convertView.findViewById(R.id.child_details);
            convertView.setTag(childViewHoler);
        } else {
            childViewHoler = (ChildViewHolder) convertView.getTag();
        }

        String childDetails = childList[groupPosition][childPosition];
        childViewHoler.childTextView.setText(childDetails);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHoler {
        public TextView groupTextView;
    }

    class ChildViewHolder {
        public TextView childTextView;
    }
}
