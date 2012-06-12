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
 

public class PhotosActivity extends Activity {  
	
	
	private String[] imageUrls;

	
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private DisplayImageOptions options;
	
	public void onCreate(Bundle savedInstanceState) {

		Log.v("ccccc", "2iniciando....");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_layout);

		//imageLoader = ImageLoader.getInstance();
		 
		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.stub_image)
			.showImageForEmptyUrl(R.drawable.image_for_empty_url)
			.cacheInMemory()
			.cacheOnDisc()
			.build();
		
		importLast();
		
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
	
	public void importLast() {

        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl("http://www.tiramillas.es/justforshare/last.php");

        JSONArray items = null;
        List<String> listImageUrls= new ArrayList<String>();
        
        try {
            // Getting Array of Contacts
        	items = json.getJSONArray("items");
 
            // looping through All Contacts
            for(int i = 0; i < items.length(); i++){
                JSONObject c = items.getJSONObject(i);
 
                // Storing each json item in variable
                listImageUrls.add(c.getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        imageUrls = new String[ listImageUrls.size() ];
        listImageUrls.toArray( imageUrls );

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
