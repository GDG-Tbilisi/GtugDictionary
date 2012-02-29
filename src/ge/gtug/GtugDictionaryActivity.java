package ge.gtug;

import java.io.IOException;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

//Testing Commit

public class GtugDictionaryActivity extends Activity {
	/** Called when the activity is first created. */
	DataBaseHelper myDbHelper;
	protected SQLiteDatabase db;
	
	
	protected EditText searchText;
	TextView txt;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 txt = (TextView) findViewById(R.id.textView1);

		myDbHelper = new DataBaseHelper(this);
		  searchText = (EditText) findViewById(R.id.searchText);
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
		String t = "";
		 myDbHelper.translateWord(searchText.getText());
		 txt.setText(t);
		// searchText.setText(txt);
		 System.out.println("Event called from xml!!!");
	 
	 }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.options:
			startActivity(new Intent(this, Options.class));
			return true;
		case R.id.help:
			startActivity(new Intent(this, Help.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}