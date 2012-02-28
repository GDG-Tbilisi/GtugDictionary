package ge.gtug;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/ge.gtug/databases/";
	private static String DB_NAME = "ilingoka.db";
	private static final int DB_VERSION = 1;
	public static final String GEO = "geo";
	public static final String GEO_ID = "id";
	public static final String GEO_WORD = "geo";
	public static final String GEO_TYPE = "type";

	public static final String ENG = "eng";
	public static final String ENG_ID = "id";
	public static final String ENG_WORD = "eng";
	public static final String ENG_TYPE = "type";
	public static final String TRANSCRIPTOPN = "transcription";
	
	public static final String TYPES = "types";
	public static final String TYPE_ID = "id";
	public static final String TYPE_NAME = "name";
	public static final String TYPE_ABBR = "abbr";
	

	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private Resources resources;
	private String viewWords = "viewWords";

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
		resources = context.getResources();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	/*	db.execSQL("CREATE TABLE android_metadata (locale TEXT DEFAULT 'en_US')");
		db.execSQL("INSERT INTO android_metadata VALUES ('en_US')");
		 db.execSQL("CREATE VIEW "+viewWords+
				    " AS select t.abbr,g.geo ,e.eng "
			 			+"  from geo_eng ge, "
						+	"geo g, "
						+	"types t , "
						+	"eng e	"
						+"where g.id = ge.geo_id "
					+	"	and t.id = ge.type "
					+	"	and e.id=ge.eng_id);");
				  //Inserts pre-defined departments
*/		  System.out.println("onCreate Called");		
				  System.out.println("View Created");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		 myContext.deleteDatabase(DB_NAME); 
		 System.out.println("DB DELETED");
		 
		 Log.w("RatedCalls Database",
	                "Upgrading database, this will drop tables and recreate.");
	        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
	        onCreate(db);
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	private boolean checkDataBase() {

		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	private void copyDataBase() throws IOException {
		String outFileName = DB_PATH + DB_NAME;
		OutputStream output = new FileOutputStream(outFileName);

		copy(resources.openRawResource(R.raw.enggeoaa), output);
		copy(resources.openRawResource(R.raw.enggeoab), output);
		copy(resources.openRawResource(R.raw.enggeoac), output);
		copy(resources.openRawResource(R.raw.enggeoad), output);
		copy(resources.openRawResource(R.raw.enggeoae), output);
		copy(resources.openRawResource(R.raw.enggeoaf), output);
		copy(resources.openRawResource(R.raw.enggeoag), output);
		copy(resources.openRawResource(R.raw.enggeoah), output);
		copy(resources.openRawResource(R.raw.enggeoai), output);

		output.flush();
		output.close();
	}

	private void copy(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[1024];
		int length;

		while ((length = input.read(buffer)) > 0) {
			output.write(buffer);
			output.flush();
		}

		input.close();
	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
		System.out.println("DB is Opened");
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null) {
			myDataBase.close();
		}
		System.out.println("DB is Closed");

		super.close();
	}

	// / All ather methods from here

	public String getTranslation() {
		SQLiteDatabase db = this.getReadableDatabase();
		String result = null;
		String[] columns = new String[] {TYPE_NAME, TYPE_ABBR };
		Cursor c = db.query(TYPES, columns, null,
			null, null, null, null);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
		
		result = c.getString(c.getColumnIndex(TYPE_NAME)) + " - " + c.getString(c.getColumnIndex(TYPE_ABBR))+ "\n" + "Bla bla";
		}
		System.out.println("result is " + result);
		System.out.println("Result was Returned");
		return result;
	}

}