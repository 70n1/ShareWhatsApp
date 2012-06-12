package media.el70n1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 

public class GifsActivity extends Activity { 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gifs_layout);
		importLast();
	}
	
	public void importLast() {
		

        TextView textview = new TextView(this);

        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl("http://www.tiramillas.es/justforshare/last.php");

        JSONArray items = null;
        
        String ids="";
        
        try {
            // Getting Array of Contacts
        	items = json.getJSONArray("items");
 
            // looping through All Contacts
            for(int i = 0; i < items.length(); i++){
                JSONObject c = items.getJSONObject(i);
 
                // Storing each json item in variable
                ids = ids + c.getString("id")  + ", ";
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textview.setText("This is the IDs: " + ids);
        setContentView(textview);
	}

}
