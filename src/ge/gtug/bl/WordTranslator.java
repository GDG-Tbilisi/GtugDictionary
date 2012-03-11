package ge.gtug.bl;

import ge.gtug.ASCII2UTF8Converter;
import ge.gtug.database.DBHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

public class WordTranslator extends DBHelper{
	
	public WordTranslator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private SQLiteDatabase myDataBase;
	public String translateWord(Editable text, boolean IsGeo) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();

		String result = "";	
		if(IsGeo){
			cursor = db.rawQuery("SELECT geo,eng FROM DicV WHERE geo LIKE '"+  text.toString() + "%'",new String [] {} );
			if (cursor.getCount()<1)
				return result = "Not Found!"; 			
			for (cursor.moveToFirst();!cursor.isLast(); cursor.moveToNext()) {
				result += ASCII2UTF8Converter.toUTF8(cursor.getString(0)) + " - " + cursor.getString(1) + "\n";				
			}
		}
		else{
			cursor = db.rawQuery("SELECT eng,geo FROM DicV WHERE eng LIKE '"+  text.toString() + "%'",new String [] {} );
			if (cursor.getCount()<1)
				return result = "Not Found!"; 
			for (cursor.moveToFirst();!cursor.isLast(); cursor.moveToNext()) {
				result += cursor.getString(0) + " - " + ASCII2UTF8Converter.toUTF8(cursor.getString(1)) + "\n";
			}
			
		}
		 
		
			cursor=null;  
			
			
	
		
		return result;
		
	}

}
