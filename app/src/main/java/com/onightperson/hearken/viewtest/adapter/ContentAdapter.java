package com.onightperson.hearken.viewtest.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.viewtest.model.ContactContent;
import com.onightperson.hearken.viewtest.model.ContentBase;
import com.onightperson.hearken.wave.BottomLayout;

import java.util.List;

/**
 * Created by liubaozhu on 17/2/23.
 */

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ContentAdapter";
    private List<ContentBase> mContentList;

    public ContentAdapter(List<ContentBase> contactInfos) {
        mContentList = contactInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                itemView = inflater.inflate(R.layout.cardview_layout, parent, false);
                return new ContactViewHolder(itemView);
            case 1:
                itemView = inflater.inflate(R.layout.activity_bottom, parent, false);
                return new AnimViewHolder(itemView);
            default:
                itemView = inflater.inflate(R.layout.cardview_layout, parent, false);
                return new ContactViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ContentBase content = mContentList.get(position);
        return content.contentType;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                ContactContent contactInfo = (ContactContent) mContentList.get(position);
                ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
                contactViewHolder.mNameTextView.setText(contactInfo.name);
                contactViewHolder.mSurNameTextView.setText(contactInfo.surName);
                contactViewHolder.mTitleTextView.setText(contactInfo.title);
                contactViewHolder.mEmailTextView.setText(contactInfo.email);
                contactViewHolder.mAddressTextView.setText(contactInfo.addr);
                break;
            case 1:

        }
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView mNameTextView;
        protected TextView mSurNameTextView;
        protected TextView mTitleTextView;
        protected TextView mEmailTextView;
        protected TextView mAddressTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
            mSurNameTextView = (TextView) itemView.findViewById(R.id.sur_name);
            mTitleTextView = (TextView) itemView.findViewById(R.id.contact_title);
            mEmailTextView = (TextView) itemView.findViewById(R.id.email);
            mAddressTextView = (TextView) itemView.findViewById(R.id.address);
        }
    }

    public static class AnimViewHolder extends RecyclerView.ViewHolder {
        public BottomLayout bottomLayout;
        public AnimViewHolder(View itemView) {
            super(itemView);
            bottomLayout = (BottomLayout) itemView.findViewById(R.id.bottom_layout);
        }
    }
}
