package com.example.tranning.screen.main;
import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tranning.screen.ItemDetailFragment;
import com.example.tranning.R;
import com.example.tranning.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUser;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView  name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nameUser);

        }
    }

    public UserAdapter(Context mContext, List<User> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View userView = inflater.inflate(R.layout.user_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = mUser.get(position);
        holder.name.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", user.getName());
                bundle.putString("realName", user.getRealName());
                itemDetailFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.nav_host_fragment, itemDetailFragment).addToBackStack(null).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }
}