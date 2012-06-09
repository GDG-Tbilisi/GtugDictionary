package ge.gtug.dictionary;

import ge.gtug.dictionary.R;
import ge.gtug.dictionary.bl.WordTranslator;
import ge.gtug.dictionary.database.DBHelper;
import ge.gtug.dictionary.enrty.TranslationEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GtugDictionaryActivity extends Activity {
	/** Called when the activity is first created. */
	DBHelper myDbHelper;
	protected SQLiteDatabase db;
	private Handler handler = new Handler();
	public ImageView geo;
	public ImageView eng;
	public boolean isGeo = true;
	protected EditText searchText;
	TextView txt;
	ListView list;
	private List wordList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt = (TextView) findViewById(R.id.textView1);
		geo = (ImageView) findViewById(R.id.georgia);
		eng = (ImageView) findViewById(R.id.england);
		searchText = (EditText) findViewById(R.id.searchText);
		final Button button = (Button) findViewById(R.id.switcher);
		final ImageButton search = (ImageButton) findViewById(R.id.searchButton);
		final ProgressBar prog = (ProgressBar) findViewById(R.id.progressbar);
		search.setVisibility(0);
		prog.setVisibility(8);

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

		try {
			myDbHelper.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void search(View view) {
		final ImageButton search = (ImageButton) findViewById(R.id.searchButton);
		final ProgressBar prog = (ProgressBar) findViewById(R.id.progressbar);
		search.setVisibility(8);
		prog.setVisibility(0);

		new Thread(new Runnable() {
			public void run() {
				ArrayList<TranslationEntry> result = new ArrayList();
				WordTranslator db = new WordTranslator(
						GtugDictionaryActivity.this);
				String text = searchText.getText().toString().trim();
				if (text.equals("") || text.trim().length() == 0) {
				} else if (isGeo) {
					myDbHelper.openDataBase();
					result = db.translateWord(text, true, this);
					myDbHelper.close();
				} else {
					myDbHelper.openDataBase();
					result = db.translateWord(text, false, this);
					myDbHelper.close();
				}
				wordList = new ArrayList();
				if (isGeo) {
					wordList = getGeoList(result);
				} else {
					wordList = getEngList(result);
				}
				handler.post(new Runnable() {
					public void run() {
						list = (ListView) findViewById(R.id.wordList);
						list.setTextFilterEnabled(true);
						list.setAdapter(new ArrayAdapter<TranslationEntry>(
								GtugDictionaryActivity.this,
								android.R.layout.simple_list_item_1, wordList));
						list.setOnItemClickListener(new OnItemClickListener() {
/*Redirect to another activity 
 * for detail information about selected word */
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								
								System.out.println("onItemClick  : " + position
										+ "child count  ");
								
							}
						});
					}
				});
				handler.post(new Runnable() {
					public void run() {
						prog.setVisibility(8);
						search.setVisibility(0);
					}
				});
			}

		}).start();

	}

	private List getEngList(ArrayList<TranslationEntry> result) {
		// TODO Auto-generated method stub
		for (TranslationEntry entry : result) {
			wordList.add(entry.getTarget() + "-" + entry.getSource());
		}
		return wordList;
	}

	private List getGeoList(ArrayList<TranslationEntry> result) {
		// TODO Auto-generated method stub
		for (TranslationEntry entry : result) {
			wordList.add(entry.getSource() + "-" + entry.getTarget());
		}
		return wordList;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gtug_menu, menu);
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