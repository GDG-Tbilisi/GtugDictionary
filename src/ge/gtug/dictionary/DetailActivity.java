package ge.gtug.dictionary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	TextView tv;
	String selectedWord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		/*Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		selectedWord = extras.getString("selectedWord");
		tv = (TextView) findViewById(R.id.selectedWord);
		tv.setText(selectedWord);*/
	}
}
