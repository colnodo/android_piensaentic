package org.apc.colnodo.piensaentic.Activities.ActivityFiveEncryption;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.apc.colnodo.piensaentic.R;
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
    VideoView videoView;
    private ProgressDialog progressDialog;
    private MediaController mController;
    private int position = 0;
    private Uri uriYouTube;



    public One(){}

    public static One newInstance() {
        One fragment = new One();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new MediaController(getActivity());
        videoView.setMediaController(mController);
        videoView.requestFocus();

 /*Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/"
 + R.raw.sample);
 videoView.setVideoURI(uri);
 videoView.start();*/

        if (savedInstanceState != null) {
            int loc = savedInstanceState.getInt("Loc");
            Log.i("Loaction: ", loc + "");
            uriYouTube = Uri.parse(savedInstanceState.getString("url"));
            videoView.setVideoURI(uriYouTube);
            videoView.seekTo(loc);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.v("onPrepared", "ok");
                    mp.start();
                }
            });
        } else {
            RTSPUrlTask truitonTask = new RTSPUrlTask();
            truitonTask.execute("http://www.youtube.com/watch?v=2zNSgSzhBfM");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTvValues = new ArrayList<>();
        mView = inflater.inflate(R.layout.activity_five_one, container, false);
        videoView = (VideoView)mView.findViewById(R.id.videoView);
//        uriYouTube = Uri.parse(savedInstanceState.getString("url"));
//        try {
//            //set the media controller in the VideoView
//            mVideoView.setMediaController(mediaControls);
//            //set the uri of the video to be played
//            mVideoView.setVideoURI(uriYouTube);
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }
//
//        //mVideoView.setVideoPath("https://www.youtube.com/watch?v=jeS3umd1--A");
//        //mVideoView.start();
//        mVideoView.requestFocus();
//        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                // close the progress bar and play the video
//                progressDialog.dismiss();
//                //if we have a position on savedInstanceState, the video playback should start from here
//                mVideoView.seekTo(position);
//                if (position == 0) {
//                    mediaPlayer.start();
//                } else {
//                    //if we come from a resumed activity, video playback will be paused
//                    mVideoView.pause();
//                }
//            }
//        });

        return mView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

    }

    void startPlaying(String url) {
        uriYouTube = Uri.parse(url);
        videoView.setVideoURI(uriYouTube);
        videoView.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Loc", videoView.getCurrentPosition());
        outState.putString("url", uriYouTube.toString());
    }

    private class RTSPUrlTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = getRTSPVideoUrl(urls[0]);
            return response;
        }
    }

    public String getRTSPVideoUrl(String urlYoutube) {
        try {
            String gdy = "http://gdata.youtube.com/feeds/api/videos/";
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            String id = extractYoutubeId(urlYoutube);
            URL url = new URL(gdy + id);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            Document doc = dBuilder.parse(connection.getInputStream());
            Element el = doc.getDocumentElement();
            NodeList list = el.getElementsByTagName("media:content");
            String cursor = urlYoutube;
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node != null) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    HashMap<String, String> maps = new HashMap<String, String>();
                    for (int j = 0; j < nodeMap.getLength(); j++) {
                        Attr att = (Attr) nodeMap.item(j);
                        maps.put(att.getName(), att.getValue());
                    }
                    if (maps.containsKey("yt:format")) {
                        String f = maps.get("yt:format");
                        if (maps.containsKey("url"))
                            cursor = maps.get("url");
                        if (f.equals("1"))
                            return cursor;
                    }
                }
            }
            return cursor;
        } catch (Exception ex) {
            return urlYoutube;
        }
    }

    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }


}
