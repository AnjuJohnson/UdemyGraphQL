package com.example.udemygraphql.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.udemygraphql.R;
import com.example.udemygraphql.UsersQuery;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {
    private List<UsersQuery.User> mUserList;

    public void setmUserList(List<UsersQuery.User> mUserList) {
        this.mUserList = mUserList;
        notifyDataSetChanged();
    }

    public UserAdapter(List<UsersQuery.User> users) {
        mUserList = users;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        UsersQuery.User user = mUserList.get(position);
        holder.mName.setText(user.name());
        holder.mId.setText("User Id : "+user.id());
        holder.mAge.setText("age : "+user.age());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView mName, mAge, mId;

        public Holder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.username);
            mAge = (TextView) itemView.findViewById(R.id.userage);
            mId = (TextView) itemView.findViewById(R.id.UserId);

        }
    }
}
