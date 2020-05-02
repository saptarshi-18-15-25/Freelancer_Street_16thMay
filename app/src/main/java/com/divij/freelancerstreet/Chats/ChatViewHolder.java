package com.divij.freelancerstreet.Chats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.divij.freelancerstreet.R;

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mMessage;
    public CardView mChatcard;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mMessage=itemView.findViewById(R.id.messsage);
        mChatcard=itemView.findViewById(R.id.Chatcard);

    }

    @Override
    public void onClick(View view) {

    }
}