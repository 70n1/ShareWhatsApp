package media.el70n1;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public abstract class BaseActivity extends Activity {

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected String[] imageUrls;
	protected DisplayImageOptions options;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.stub_image)
			.showImageForEmptyUrl(R.drawable.image_for_empty_url)
			.cacheInMemory()
			.cacheOnDisc()
			.build();

		importLast();
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case R.id.item_clear_memory_cache:
				imageLoader.clearMemoryCache();
				return true;
			case R.id.item_clear_disc_cache:
				imageLoader.clearDiscCache();
				return true;
			default:
				return false;
		}
		
	}
	*/

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
	
}
