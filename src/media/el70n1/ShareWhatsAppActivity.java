package media.el70n1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import android.os.Bundle;

public class ShareWhatsAppActivity extends TabActivity {
    /** Called when the activity is first created. */    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Photos");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Photos", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, PhotosActivity.class);
        photospec.setContent(photosIntent);
 
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Songs");
        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_gifs_tab));
        Intent songsIntent = new Intent(this, GifsActivity.class);
        songspec.setContent(songsIntent);
 
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Videos");
        videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, VideosActivity.class);
        videospec.setContent(videosIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}