package ge.gtug.dictionary.bl;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;
import ge.gtug.dictionary.ASCII2UTF8Converter;
import ge.gtug.dictionary.GtugDictionaryActivity;
import ge.gtug.dictionary.database.DBHelper;
import ge.gtug.dictionary.database.DbNames;
import ge.gtug.dictionary.enrty.TranslationEntry;

public class WordTranslator extends DBHelper {

	public WordTranslator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private SQLiteDatabase myDataBase;

	public ArrayList<TranslationEntry> translateWord(String text,
			boolean isGeo, Object o) {

		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<TranslationEntry> result = new ArrayList<TranslationEntry>();
		// String result = "";

		int i = 0;
		if (!isGeo)
			i = 1;

		String[] columns = new String[] { DbNames.View.GEO_Word.toString(),
				DbNames.View.ENG_WORD.toString() };
		Cursor c = db.query(DbNames.View.TABLE.toString(), columns,
				columns[i].toString() + " Like '" + text.toString() + "%'",
				null, null, null, null, "10");
		if (c.getCount() != 0) {
			for (c.moveToFirst(); !c.isLast(); c.moveToNext()) {
				String source = ASCII2UTF8Converter.toUTF8(c.getString(0))
						.toString();
				String target = c.getString(1).toString();
				result.add(new TranslationEntry(source, target));
			}
		} else {
			result.add(new TranslationEntry("Not Found", "Not Found"));
		}

		c = null;
		return result;
	}
}
