package org.apc.colnodo.piensaentic.Activities.ActivityFiveEncryption;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import org.apc.colnodo.piensaentic.IndexManagement.FragmentBookInterface;
import org.apc.colnodo.piensaentic.R;
import org.apc.colnodo.piensaentic.Utils.LocalConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by apple on 11/18/16.
 */

public class One extends Fragment {
    Context mContext;
    List<TextView> mTvValues;
    View mView;
    FragmentBookInterface mFragmentInterface;
    private YouTubePlayerSupportFragment youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private Button mPlayButton;

    public One(){}

    public static One newInstance() {
        One fragment = new One();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTvValues = new ArrayList<>();
        mView = inflater.inflate(R.layout.activity_five_one, container, false);
        LinearLayout lyYoutube = (LinearLayout)mView.findViewById(R.id.ly_youtube_video_frame);
        lyYoutube.removeAllViews();
        youTubePlayerView = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.ly_youtube_video_frame, youTubePlayerView).commit();
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(LocalConstants.YOUTUBE_VIDEO_ID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        mPlayButton = (Button) mView.findViewById(R.id.button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.initialize(LocalConstants.YOUTUBE_API_KEY, onInitializedListener);
                mPlayButton.setVisibility(View.GONE);
            }
        });

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mFragmentInterface = (FragmentBookInterface) mContext;
        mFragmentInterface.changeMenuItem(R.drawable.hamburguesa);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Fragment", "Visible: " + isVisibleToUser);
        if (isVisibleToUser) {
            try {

            } catch (Exception ea){
                ea.printStackTrace();
            }
        }
    }

}
