package com.onightperson.hearken.recycle.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.recycle.model.ContactInfo;

import java.util.List;

/**
 * Created by liubaozhu on 17/2/23.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<ContactInfo> mContactInfoList;

    public ContactAdapter(List<ContactInfo> contactInfos) {
        mContactInfoList = contactInfos;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactInfo contactInfo = mContactInfoList.get(position);
        holder.mNameTextView.setText(contactInfo.name);
        holder.mSurNameTextView.setText(contactInfo.surName);
        holder.mTitleTextView.setText(contactInfo.title);
        holder.mEmailTextView.setText(contactInfo.email);
        holder.mAddressTextView.setText(contactInfo.addr);
    }

    @Override
    public int getItemCount() {
        return mContactInfoList.size();
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
}
