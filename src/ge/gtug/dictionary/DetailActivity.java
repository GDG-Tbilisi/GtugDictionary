package ge.gtug.dictionary;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	TextView tv;
	String selectedWord;
	WebView mWebView;
	String[] words;   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_inc);

	//	Bundle extras = getIntent().getExtras();
	//	if (extras == null) {
	//		return;
	//	}
	//	selectedWord = extras.getString("selectedWord");
	//	tv = (TextView) findViewById(R.id.selectedWord);
	//	tv.setText(selectedWord);
	//	words = selectedWord.split("-");
		
		
/*		 WebViewClient yourWebClient = new WebViewClient()
	       {
	           // Override page so it's load on my view only
	           @Override
	           public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	           {
	            // This line we let me load only pages inside Firstdroid Webpage
	            if ( url.contains("firstdroid") == true )
	               // Load new URL Don't override URL Link
	               return false;
	             
	            // Return true to override url loading (In this case do nothing).
	            return true;
	           }
	       };*/
	       
	       
	       // Get Web view
//	       mWebView = (WebView) findViewById( R.id.webView1 ); //This is the id you gave
//	       mWebView.getSettings().setJavaScriptEnabled(true);
//	       mWebView.getSettings().setSupportZoom(true);       //Zoom Control on web (You don't need this
	                                              //if ROM supports Multi-Touch      
//	       mWebView.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM
//	       mWebView.setWebViewClient(yourWebClient);
	        
	       // Load URL
//	       mWebView.loadUrl("http://www.google.com");
		
		

	}

}