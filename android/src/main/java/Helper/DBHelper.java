package Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.getcapacitor.JSObject;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import net.sqlcipher.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

public class DBHelper extends SQLiteOpenHelper {

  private static SQLiteDatabase db;
  public DBHelper(Context context, String dbName) {
    super(context, dbName, null, 1);
    SQLiteDatabase.loadLibs(context);
  }

  public static JSObject openDb(Context context, String dbName, String password){
    JSObject response = new JSObject();
    DBHelper instance = null;
    try{
      instance = new DBHelper(context, dbName);
      response.put("status", "success");
      response.put("msg", "established connection");
    }catch(SQLiteException e){
      response.put("status", "error");
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      String error = sw.toString().split("\n")[0];
      response.put("msg", error);
    }
    try{
      db = instance.getWritableDatabase(password);
    }catch(SQLiteException e){
      response.put("status", "error");
      response.put("msg", "incorrect password");
    }
    return response;
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    Log.i("OnCreate", "ON_CREATE");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

  public static JSObject dbStatus(){
    JSObject response = new JSObject();
    if (db == null){
      response.put("status", "closed");
    }else{
      response.put("status", "opened");
    }
    return response;
  }
  public static JSObject execute(String query) {
    String[] out = new String[]{""};
    JSObject response = new JSObject();
    if (db == null){
      response.put("status", "error");
      response.put("msg", "incorrect password");
      return response;
    }
    try {
      db.execSQL(query);
    } catch (SQLiteException e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      out = sw.toString().split("\n");
      response.put("status", "error");
      response.put("msg", out[0]);
    }
    //db.close();
    if (out[0].isEmpty()) {
      response.put("status", "success");
      response.put("msg", "sentence executed successfully -- " + query);
    }
    return response;
  }

  @SuppressLint("Range")
  public static JSObject select(String query) {
    JSObject response = new JSObject();
    if (db == null){
      response.put("status", "error");
      response.put("msg", "incorrect password");
      return response;
    }
    try {
      Cursor cursor = db.rawQuery(query, null);
      List<JSObject> data = new ArrayList<>();
      String[] columnNames = cursor.getColumnNames();
      if (cursor.moveToFirst()) {
        while (!cursor.isAfterLast()) {
          JSObject ret = new JSObject();
          for (String columnName : columnNames) {
            ret.put(columnName, cursor.getString(cursor.getColumnIndex(columnName)));
          }
          data.add(ret);
          cursor.moveToNext();
        }
      }
      cursor.close();

      response.put("status", "success");
      response.put("msg", "select query processed successfully -- "+query);
      response.put("data", data);
    } catch (SQLiteException e) {
      response.put("status", "error");
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      String error = sw.toString().split("\n")[0];
      response.put("msg", error);
    }
    return response;
  }
}
