package ge.gtug;

import java.io.IOException;





import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

//Testing Commit


public class GtugDictionaryActivity extends Activity {
	/** Called when the activity is first created. */
	DataBaseHelper myDbHelper;
	protected SQLiteDatabase db;
	
	
    
    
	
	
	protected EditText searchText, resultBox;
	TextView txt;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 txt = (TextView) findViewById(R.id.textView1);
		 
		 final ImageView geo = (ImageView) findViewById(R.id.georgia);
		 final ImageView eng = (ImageView) findViewById(R.id.england);
		 
		 Button button = (Button) findViewById(R.id.switcher);
		 button.setOnClickListener(new OnClickListener() {
			    int i = 1;
					public void onClick(View v) {
						if (i==1){
							geo.setImageResource(R.drawable.england);
							eng.setImageResource(R.drawable.georgia);
							i++;
							} else { geo.setImageResource(R.drawable.georgia);
							         eng.setImageResource(R.drawable.england);
							         i--;
							 }
						
							
					}

				});

		myDbHelper = new DataBaseHelper(this);
		  searchText = (EditText) findViewById(R.id.searchText);
		  resultBox = (EditText) findViewById(R.id.resultBox);
		 
		try {
			myDbHelper.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater koba = getMenuInflater();
		koba.inflate(R.menu.gtug_menu, menu);
		return true;
	}
	 public void search(View view) {
	//	String t = "";
		String result = myDbHelper.translateWord(searchText.getText());		
		resultBox.setText(result);
	    // if (result.length()<3)
		   // Toast.makeText(this,"Suck" + result,Toast.LENGTH_LONG).show();
		
	//	 txt.setText(t);
		// searchText.setText(txt);
		 
	 
	 }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}