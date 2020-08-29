package com.ugcodes.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaylistCardAdapter extends RecyclerView.Adapter<PlaylistCardAdapter.PlaylistCardViewHolder> {
    private ArrayList<PlaylistCard> mPlaylistCard;

    public PlaylistCardAdapter(ArrayList<PlaylistCard> playlistCard) {
        mPlaylistCard = playlistCard;
    }

    @NonNull
    @Override
    public PlaylistCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_card, parent, false);

        return new PlaylistCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistCardViewHolder holder, int position) {
        PlaylistCard currentItem = mPlaylistCard.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getTitle());
        holder.mTextView2.setText(currentItem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mPlaylistCard.size();
    }

    public static class PlaylistCardViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public PlaylistCardViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.playlist_card_image);
            mTextView1 = itemView.findViewById(R.id.playlist_card_title);
            mTextView2 = itemView.findViewById(R.id.playlist_card_subtitle);
        }
    }
}
