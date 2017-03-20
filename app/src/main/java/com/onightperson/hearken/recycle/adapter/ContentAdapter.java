package com.onightperson.hearken.recycle.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.recycle.model.ContactContent;
import com.onightperson.hearken.recycle.model.ContentBase;
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
            case 2:
                itemView = inflater.inflate(R.layout.botton_layout, parent, false);
                return new ButtonHolder(itemView);
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

    public class AnimViewHolder extends RecyclerView.ViewHolder {
        public BottomLayout bottomLayout;
        public AnimViewHolder(View itemView) {
            super(itemView);
            bottomLayout = (BottomLayout) itemView.findViewById(R.id.bottom_layout);
        }
    }

    public class ButtonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mRemoveItemBtn;

        public ButtonHolder(View itemView) {
            super(itemView);
            mRemoveItemBtn = (Button) itemView.findViewById(R.id.remove_item_btn);
            mRemoveItemBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mRemoveItemBtn) {
                mContentList.remove(0);
                notifyItemChanged(0);
            }
        }
    }
}
