package com.trivedi.quizeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

 class DatabaseAccess {
	private SQLiteOpenHelper openHelper;
	private SQLiteDatabase database;
	private static DatabaseAccess instance;

	/**
	 * Private constructor to aboid object creation from outside classes.
	 *
	 * @param context
	 */
	private DatabaseAccess(Context context) {
		this.openHelper = new DatabaseOpenHelper(context);
	}

	/**
	 * Return a singleton instance of DatabaseAccess.
	 *
	 * @param context the Context
	 * @return the instance of DabaseAccess
	 */
	public static DatabaseAccess getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseAccess(context);
		}
		return instance;
	}

	/**
	 * Open the database connection.
	 */
	public void open() {
		this.database = openHelper.getWritableDatabase();
	}

	/**
	 * Close the database connection.
	 */
	public void close() {
		if (database != null) {
			this.database.close();
		}
	}

	/**
	 * Read all quotes from the database.
	 *
	 * @return a List of quotes
	 */
	public ArrayList<DataClass> getQuotes() {
		ArrayList<DataClass> list = new ArrayList<>();
		Cursor cursor = database.rawQuery("SELECT * FROM quize", null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			DataClass data = new DataClass();
			data.setQuestion(cursor.getString(1));
			data.setAnswer(cursor.getString(2));
			list.add(data);
			cursor.moveToNext();
		}
		cursor.close();
		return list;
	}
}
 class DatabaseOpenHelper extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "my.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
}
/*
public class DatabaseHelper {
	public static final String DB_NAME = "quize.db";
	public static final String DB_TABLE = "quize";
	//private static String DB_PATH = "";

	public static final int DB_VERSION = 3;
	private SQLiteDatabase db;
	//private static Context acontext;
	private final QuoteDataClass quoteDb;

	public DatabaseHelper(Context context) {
		//acontext = context;
		//DB_PATH = "/data/data/"	+ context.getApplicationContext().getPackageName() + "/databases/";

		this.quoteDb = new QuoteDataClass(context);
		if(this.db!=null){
			if(this.db.isOpen()==false){
				this.db=this.quoteDb.getWritableDatabase();
			}
		}
		else if (this.db == null) 
		{
			this.db = this.quoteDb.getWritableDatabase();
		}
		
	}
	private static class QuoteDataClass extends SQLiteOpenHelper{
		Context contex;
		public QuoteDataClass(Context context) {
			super(context, DB_NAME,null,DB_VERSION);
			this.contex=context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			int i=insertFromFile(contex,R.raw.quizedb,db);
			Log.e("squ statements", "created row="+i);
			*/
/*if(copyDataBase()){
				
			}else{
				Toast.makeText(acontext, "Db not copied", Toast.LENGTH_SHORT).show();
			}*//*


		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
		*/
/*public boolean copyDataBase(){
			
			new Thread(){
				ProgressDialog pd=ProgressDialog.show(acontext, "Loading...", "DB importing");
				@Override
				public void run(){
					try {
						// Open your local db as the input stream
						InputStream myInput = acontext.getAssets().open(DB_NAME);
						// Path to the just created empty db
						String outFileName = DB_PATH + DB_NAME;
						// Open the empty db as the output stream
						OutputStream myOutput = new FileOutputStream(outFileName);
						// transfer bytes from the inputfile to the outputfile
						byte[] buffer = new byte[1024];
						int length;
						while ((length = myInput.read(buffer)) > 0) {
							myOutput.write(buffer, 0, length);
						}
						// Close the streams
						myOutput.flush();
						myOutput.close();
						myInput.close();
						mHandler.sendEmptyMessage(1);
						pd.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
						mHandler.sendEmptyMessage(2);
					}
				}
				private Handler mHandler=new Handler(){
					@Override
					public void handleMessage(Message msg){
						if(msg.what==1){
							Toast.makeText(acontext, "Database copied", Toast.LENGTH_SHORT).show();
							pd.dismiss();
						}else if(msg.what==2){
							Toast.makeText(acontext, "Database not copied", Toast.LENGTH_SHORT).show();
							pd.dismiss();
						}else{
							pd.dismiss();
						}
					}
				};
			}.start();
			return false;
		}*//*

		public int insertFromFile(Context context, int resourceId,SQLiteDatabase db)  {
		    // Reseting Counter
		    try {
		    	int result = 0;

			    // Open the resource
			    InputStream insertsStream = context.getResources().openRawResource(resourceId);
			    BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

			    // Iterate through lines (assuming each insert has its own line and theres no other stuff)
			    while (insertReader.ready()) {
			        String insertStmt = insertReader.readLine();
			        db.execSQL(insertStmt);
			        result++;
			    }
			    insertReader.close();

			    // returning number of inserted rows
			    return result;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			
		}
	}
	
	public void closeDB(){
		if(this.db.isOpen()){
			Log.e("quote", "DB close");
			this.db.close();
		}
	}
	public boolean isOpen(){
		return db.isOpen();
	}
	public ArrayList<DataClass> getQuote(){
		ArrayList<DataClass> myQuote=new ArrayList<DataClass>();
		Cursor quoteCursor=this.db.query(DB_TABLE, null, null, null, null, null, "random()");
		if(quoteCursor.getCount()>0){
			quoteCursor.moveToFirst();
			do {

				 myQuote.add(new DataClass(quoteCursor.getInt(quoteCursor.getColumnIndex("id")),quoteCursor.getString(quoteCursor.getColumnIndex("question")),quoteCursor.getString(quoteCursor.getColumnIndex("answer"))));
			} while (quoteCursor.moveToNext());
			quoteCursor.close();
		}
		return myQuote;
	}
	public String getWidgetQuote(){
		String s="";
		try {
			Cursor quoteCursor=this.db.query(DB_TABLE, null, null, null, null, null, "random()");
			if(quoteCursor.getCount()>0){
				quoteCursor.moveToFirst();
				s=quoteCursor.getString(quoteCursor.getColumnIndex("quote"));
				quoteCursor.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}
	public boolean makeQuoteFav(int quote_id){
		try {
			ContentValues value=new ContentValues();
			value.put("fav", 1);
			int i=db.update(DB_TABLE, value, "_id = "+quote_id,null);
			if(i>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<DataClass> getAllFavQuote(){
		ArrayList<DataClass> myFav=new ArrayList<DataClass>();
		try {
			Cursor quoteCursor=this.db.query(DB_TABLE, null, "fav = "+1, null, null, null, "random()");
			if(quoteCursor.getCount()>0){
				quoteCursor.moveToFirst();
				do {
					 myFav.add(new DataClass(quoteCursor.getInt(quoteCursor.getColumnIndex("id")),quoteCursor.getString(quoteCursor.getColumnIndex("question")),quoteCursor.getString(quoteCursor.getColumnIndex("answer"))));
				} while (quoteCursor.moveToNext());
				quoteCursor.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myFav;
	}
	public boolean removeQuoteFromFav(int quote_id){
		try {
			ContentValues value=new ContentValues();
			value.put("fav", 0);
			int i=db.update(DB_TABLE, value, "id = "+quote_id,null);
			if(i>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
*/
