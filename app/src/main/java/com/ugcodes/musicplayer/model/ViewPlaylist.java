package com.ugcodes.musicplayer.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ugcodes.musicplayer.MyScrollView;
import com.ugcodes.musicplayer.R;
import com.ugcodes.musicplayer.adapter.PlaylistItemAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPlaylist extends Fragment {
    private static final String TAG = "hello";
    private LinearLayout topSection;
    private ImageView playlistImage;
    private TextView playlistTitle;
    private TextView playlistTitleText, playlistSubtitleText;
    private Button playButton, shuffleButton;

    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;
    int currentNumber;

    private MyScrollView parentScroll;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RelativeLayout topBar, topSectionBackground;
    private ImageView topSectionBackgroundImage;
    private LinearLayout buttonSection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_playlist, container, false);

        topSection = view.findViewById(R.id.view_playlist_top_section);
        playlistImage = view.findViewById(R.id.view_playlist_playlistImage);
        parentScroll = view.findViewById(R.id.view_playlist_parent_scroll);
        topBar = view.findViewById(R.id.view_playlist_top_bar);
        topSectionBackground = view.findViewById(R.id.top_section_background);
        topSectionBackgroundImage = view.findViewById(R.id.top_section_background_image);
        playlistTitle = view.findViewById(R.id.view_playlist_title);
        playButton = view.findViewById(R.id.view_playlist_play_button);
        shuffleButton = view.findViewById(R.id.view_playlist_shuffle_button);
        buttonSection = view.findViewById(R.id.view_playlist_button_section);
        playlistTitleText = view.findViewById(R.id.view_playlist_title_text);
        playlistSubtitleText = view.findViewById(R.id.view_playlist_subtitle_text);

        ArrayList<PlaylistItem> items = new ArrayList<>();
        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
                "Tones And I", true));
        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));
//        items.add(new PlaylistItem(R.drawable.ic_playlist_icon, "Dance Monkey",
//                "Tones And I", true));

        recyclerView = view.findViewById(R.id.view_playlist_child_scroll);
        recyclerView.setHasFixedSize(true);
        adapter = new PlaylistItemAdapter(items);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        final int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

        parentScroll.setPadding(0,
                ((height * 50) / 100 ) - generatePixels(50f), 0, 0);

        final float myHeight = (float) (height*0.50);

        parentScroll.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver
                        .OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (parentScroll.getScrollY()<=myHeight){
                    playlistTitle.setAlpha(parentScroll.getScrollY()/myHeight);
                    playlistImage.setScaleX((float) (1-(0.3*parentScroll.getScrollY()/myHeight)));
                    playlistImage.setScaleY((float) (1-(0.3*parentScroll.getScrollY()/myHeight)));
                    playlistImage.setAlpha(1-parentScroll.getScrollY()/myHeight);
                    topSectionBackgroundImage.setTranslationY(-parentScroll.getScrollY());
                }
            }
        });

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)
                topSectionBackground.getLayoutParams();
        layoutParams.height = (height * 50) / 100;
        topSectionBackground.setLayoutParams(layoutParams);

        shuffleButton.setWidth((width * 49) / 100);
        playButton.setWidth((width * 49) / 100);

        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)
                playlistImage.getLayoutParams();
        layoutParams2.height = (width * 50) / 100;
        layoutParams2.width = (width * 50) / 100;
        playlistImage.setLayoutParams(layoutParams2);


        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams)
                recyclerView.getLayoutParams();
        layoutParams3.height = height - generatePixels(212);
        recyclerView.setLayoutParams(layoutParams3);

        //Setting Gradient Background
        Bitmap bitmap = ((BitmapDrawable) playlistImage.getDrawable()).getBitmap();

        Palette.from(bitmap).maximumColorCount(128).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if (palette != null) {
                    vibrantSwatch = palette.getVibrantSwatch();
                    lightVibrantSwatch = palette.getLightVibrantSwatch();
                    darkVibrantSwatch = palette.getDarkVibrantSwatch();
                    mutedSwatch = palette.getMutedSwatch();
                    lightMutedSwatch = palette.getLightMutedSwatch();
                    darkMutedSwatch = palette.getDarkMutedSwatch();
                }
                generateBackgroundGradient();
            }
        });

        return view;
    }

    private int generatePixels(float dip) {
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        return (int) px;
    }

    private int getNavigationBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager()
                .getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;

        if (realHeight > usableHeight)
            return realHeight - usableHeight;
        else
            return 0;
    }

    private void generateBackgroundGradient() {
        GradientDrawable drawable;

        if (mutedSwatch != null) {
            drawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[] {mutedSwatch.getRgb(), 0xFF010101});

            topSectionBackgroundImage.setBackground(drawable);
        } else if (darkVibrantSwatch != null) {
            drawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[] {darkVibrantSwatch.getRgb(), 0xFF010101});

            topSectionBackgroundImage.setBackground(drawable);
        } else {
            topSection.setBackgroundColor(getResources().getColor(R.color.background1));
        }
    }
}