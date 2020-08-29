package com.ugcodes.musicplayer;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private RecyclerView mRecyclerView, mRecyclerView_Favourite,
            mRecyclerView_UniquelyYour, mRecyclerView_UserPlaylist;
    private RecyclerView.Adapter mAdapter;
    private ScrollView scrollView;
    private ImageView settingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<PlaylistCard> list = new ArrayList<>();
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));
        list.add(new PlaylistCard(R.drawable.ic_playlist_icon, "UG's Tracks", "Playlist"));

        ArrayList<PlaylistCard> list2 = new ArrayList<>();
        list2.add(new PlaylistCard(R.drawable.ic_liked_image, "Liked Songs", "Playlist"));
        list2.add(new PlaylistCard(R.drawable.ic_shuffle_image,
                "Shuffled Songs", "Playlist"));
        list2.add(new PlaylistCard(R.drawable.ic_repeat_image,
                "On Repeat", "Playlist"));

        scrollView = view.findViewById(R.id.scrollView);
        settingsButton = view.findViewById(R.id.settings_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    Toast.makeText(getActivity(), ""+i1, Toast.LENGTH_SHORT).show();
                }
            });
        }

        mRecyclerView = view.findViewById(R.id.recently_played_items);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView_Favourite = view.findViewById(R.id.your_favourite_items);
        mRecyclerView_Favourite.setHasFixedSize(true);

        mRecyclerView_UniquelyYour = view.findViewById(R.id.uniquely_your_items);
        mRecyclerView_UniquelyYour.setHasFixedSize(true);

        mRecyclerView_UserPlaylist = view.findViewById(R.id.your_playlist_items);
        mRecyclerView_UserPlaylist.setHasFixedSize(true);

        mAdapter = new PlaylistCardAdapter(list);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView_Favourite.setLayoutManager(
                new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView_Favourite.setAdapter(mAdapter);

        mRecyclerView_UserPlaylist.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView_UserPlaylist.setAdapter(mAdapter);

        mAdapter = new PlaylistCardAdapter(list2);

        mRecyclerView_UniquelyYour.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView_UniquelyYour.setAdapter(mAdapter);

        return view;
    }
}