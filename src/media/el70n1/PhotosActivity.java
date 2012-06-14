package media.el70n1;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
 

public class PhotosActivity extends BaseActivity {  
	
	public void onCreate(Bundle savedInstanceState) {

		//Log.v("ccccc", "2iniciando....");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_layout);

		//imageLoader = ImageLoader.getInstance();
		 
		
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        
        // Instance of ImageAdapter Class
        
        gridView.setAdapter(new ImageAdapter());
        
        
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImageGalleryActivity(position);
			}
		});
		
	}
	
	@Override
	protected void onStop() {
		imageLoader.stop();
		super.onStop();
	}
	
	

	private void startImageGalleryActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}


	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = (ImageView) convertView;
			if (imageView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image , parent, false);
			}

			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;
		}
	}
	

}
