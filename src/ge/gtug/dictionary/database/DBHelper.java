package ge.gtug.dictionary.database;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import ge.gtug.dictionary.R;
import ge.gtug.dictionary.R.raw;
import ge.gtug.dictionary.ASCII2UTF8Converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/ge.gtug.dictionary/databases/";
	private static String DB_NAME = "ilingoka.db";
	private static final int DB_VERSION = 2;
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private Resources resources;
	private String viewWords = "viewWords";
	protected Cursor cursor;
	protected ListAdapter adapter;

	public static final String VIEW_NAME = "DicV";	
	String createView = "CREATE VIEW IF NOT EXISTS DicV AS select (select geo from geo g where g._id = ge.geo_id) as geo , (select eng from eng e where e._id = ge.eng_id) as eng from geo_eng ge ";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
		resources = context.getResources();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		myContext.deleteDatabase(DB_NAME);
		System.out.println("DB DELETED");
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
				SQLiteDatabase db = this.getReadableDatabase();
				db.execSQL(createView);				
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
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null) {
			myDataBase.close();
		}
		super.close();
	}
}