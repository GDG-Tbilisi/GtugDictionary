package ge.gtug;

import android.content.Context;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/ge.gtug/databases/";
	private static String DB_NAME = "ilingoka.db";

	public static final String GEO_ID = "_id";
    public static final String GEO_WORD = "geo";
    public static final String GEO_TYPE = "type";
	
	
    public static final String ENG_ID = "_id";
    public static final String ENG_WORD = "eng";
    public static final String ENG_TYPE = "type";
    public static final String TRANSCRIPTOPN = "transcription";
    
    public static final String TYPES = "_id";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_ABBR = "abbr";
   
	
    
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private Resources resources;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		resources = context.getResources();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

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
	}
	
	@Override
	public synchronized void close() {
		if (myDataBase != null) {
			myDataBase.close();
		}

		super.close();
	}
}