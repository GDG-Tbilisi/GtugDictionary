package ge.gtug.bl;

import ge.gtug.ASCII2UTF8Converter;
import ge.gtug.database.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import ge.gtug.database.DbNames;
public class WordTranslator extends DBHelper{
	
	public WordTranslator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private SQLiteDatabase myDataBase;
	public String translateWord(String text, boolean isGeo) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();

		String result = "";	
		
		int i = 0;
		if(!isGeo) i = 1;
		
		
		String[] columns = new String[] { DbNames.View.GEO_Word.toString(), DbNames.View.ENG_WORD.toString()};
		Cursor c = db.query(DbNames.View.TABLE.toString(), columns, columns[i].toString() +" Like '" + text.toString() + "%'",null, null,null,null);

		System.out.println(c.getCount());
		
		if (c.getCount()<1)
			return result = "Not Found!"; 
		if(!isGeo){
			for (c.moveToFirst();!c.isLast(); c.moveToNext()) {
				result +=  c.getString(1) + " - "+ ASCII2UTF8Converter.toUTF8(c.getString(0)) +"\n";				
			}
		}else{
			for (c.moveToFirst();!c.isLast(); c.moveToNext()) {
				result += ASCII2UTF8Converter.toUTF8(c.getString(0)) + " - " + c.getString(1) + "\n";				
			}
		}
		
		 
		
			cursor=null;  
			
			
	
		
		return result;
		
	}

}
