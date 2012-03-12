package ge.gtug;

import ge.gtug.bl.WordTranslator;
import ge.gtug.database.DBHelper;
import ge.gtug.enrty.TranslationEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//Testing Commit

public class GtugDictionaryActivity extends Activity {
	/** Called when the activity is first created. */
	DBHelper myDbHelper;
	protected SQLiteDatabase db;
	 
	public ImageView geo;
	public ImageView eng;
	public boolean isGeo = true;
	protected EditText searchText, resultBox;
	TextView txt;
	 ListView list;
	 private List wordList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt = (TextView) findViewById(R.id.textView1);
		System.out.println("onCreate");
		geo = (ImageView) findViewById(R.id.georgia);
		eng = (ImageView) findViewById(R.id.england);
		searchText = (EditText) findViewById(R.id.searchText);
		final Button button = (Button) findViewById(R.id.switcher);

		button.setOnClickListener(new OnClickListener() {
			int i = 1;

			public void onClick(View v) {
				if (i == 1) {
					geo.setImageResource(R.drawable.england);
					eng.setImageResource(R.drawable.georgia);
					isGeo = false;
					i++;
					button.setText("Eng-Geo");
				} else {
					geo.setImageResource(R.drawable.georgia);
					eng.setImageResource(R.drawable.england);
					isGeo = true;
					i--;
					button.setText("Geo-Eng");
				}
				searchText.setText("");

			}

		});

		myDbHelper = new DBHelper(this);

		resultBox = (EditText) findViewById(R.id.resultBox);

		try {
			myDbHelper.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void search(View view) {
		//String result = "";
		ArrayList<TranslationEntry> result = new ArrayList();
		WordTranslator db = new WordTranslator(this);
		String text = searchText.getText().toString().trim();

		if (text.equals("") || text.trim().length() == 0) {

	//		result = "Not Found!";

		} else if (isGeo) {
			myDbHelper.openDataBase();
			result = db.translateWord(text, true);
			myDbHelper.close();
		} else {
			myDbHelper.openDataBase();
			result = db.translateWord(text, false);
			myDbHelper.close();
		}
		list = (ListView) findViewById(R.id.wordList);
		wordList = new ArrayList();
		 if (isGeo) {
		wordList = getGeoList(result);
		 }else{
				wordList = getEngList(result);
		 }
			
			list.setAdapter(new ArrayAdapter<TranslationEntry>(GtugDictionaryActivity.this, android.R.layout.simple_list_item_1,wordList));
				
		resultBox.setText("xx");

	}
	private List getEngList(ArrayList<TranslationEntry> result) {
		// TODO Auto-generated method stub
		for(TranslationEntry entry : result){
			 wordList.add( entry.getTarget()+ "-" +entry.getSource() );
			 	//System.out.println(entry.getSource() + "-" + entry.getTarget());
		 	}
		return wordList;
	}

	private List getGeoList(ArrayList<TranslationEntry> result) {
		// TODO Auto-generated method stub
		for(TranslationEntry entry : result){
			 wordList.add(entry.getSource() + "-" + entry.getTarget());
			 	//System.out.println(entry.getSource() + "-" + entry.getTarget());
		 	}
		return wordList;
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
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}