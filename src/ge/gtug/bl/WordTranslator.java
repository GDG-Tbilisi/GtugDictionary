package ge.gtug.bl;

import java.util.ArrayList;

import ge.gtug.ASCII2UTF8Converter;
import ge.gtug.database.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import ge.gtug.database.DbNames;
import ge.gtug.enrty.TranslationEntry;

public class WordTranslator extends DBHelper {

	public WordTranslator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private SQLiteDatabase myDataBase;

	public ArrayList<TranslationEntry> translateWord(String text, boolean isGeo) {
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
				null, null, null, null);

		for (c.moveToFirst(); !c.isLast(); c.moveToNext()) {
			// result += c.getString(1) + " - "+
			// ASCII2UTF8Converter.toUTF8(c.getString(0)) + "\n";
			String source = ASCII2UTF8Converter.toUTF8(c.getString(0))
					.toString();
			String target = c.getString(1).toString();
			result.add(new TranslationEntry(source, target));

			
		}
			cursor = null;
			return result;
	}
}
