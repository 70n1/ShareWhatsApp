package media.el70n1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 

public class PhotosActivity extends Activity {    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_layout);
		
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
	}
	
	public void importLast() {

        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl("http://www.tiramillas.es/justforshare/last.php");

        JSONArray items = null;
        try {
            // Getting Array of Contacts
        	items = json.getJSONArray("items");
 
            // looping through All Contacts
            for(int i = 0; i < items.length(); i++){
                JSONObject c = items.getJSONObject(i);
 
                // Storing each json item in variable
                String id = c.getString("id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

}
