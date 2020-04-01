package com.example.retrofittest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittest.R;
import com.example.retrofittest.models.Comment;

import java.util.List;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.Viewholder> {

    private List<Comment> mCommentsList;

    public CommentsRecyclerAdapter(List<Comment> commentsList) {

        this.mCommentsList = commentsList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.postId.setText(Integer.toString(mCommentsList.get(position).getPostId()));
        holder.id.setText(Integer.toString(mCommentsList.get(position).getId()));
        holder.name.setText(mCommentsList.get(position).getName());
        holder.email.setText(mCommentsList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView postId;
        private TextView id;
        private TextView name;
        private TextView email;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
            postId=itemView.findViewById(R.id.postid_textview);
            id=itemView.findViewById(R.id.id_textview);
            name= itemView.findViewById(R.id.name_textview);
            email=itemView.findViewById(R.id.email_textview);

        }
    }

}
