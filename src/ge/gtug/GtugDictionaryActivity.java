package ge.gtug;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//Testing Commit

public class GtugDictionaryActivity extends Activity {
	/** Called when the activity is first created. */
	DataBaseHelper myDbHelper;
	Button btnGo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		 btnGo = (Button) findViewById(R.id.btnGo);
		 myDbHelper = new DataBaseHelper(this);
	       
		
		try {
			myDbHelper.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	btnGo.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			
			
		}
	});	
	}

	
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater koba = getMenuInflater();
		koba.inflate(R.menu.gtug_menu, menu);
		return true;
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