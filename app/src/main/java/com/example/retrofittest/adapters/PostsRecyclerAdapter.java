package com.example.retrofittest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittest.R;
import com.example.retrofittest.models.Post;

import java.util.List;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.ViewHolder> {

    private List<Post> mPostsList;

    public PostsRecyclerAdapter(List<Post> postsList) {

        this.mPostsList = postsList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(Integer.toString(mPostsList.get(position).getId()));
        holder.userId.setText(Integer.toString(mPostsList.get(position).getUserId()));
        holder.title.setText(mPostsList.get(position).getTitle());
        holder.content.setText(mPostsList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView userId;
        private TextView title;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            id=itemView.findViewById(R.id.id_textview);
            userId=itemView.findViewById(R.id.userid_textview);
            title=itemView.findViewById(R.id.title_textview);
            content=itemView.findViewById(R.id.content_textview);

        }
    }
}
