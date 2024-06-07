package solutions.whoami.capacitor.sqlcipher.handler;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.JSObject;

import java.util.List;

import Helper.DBHelper;

public class SqlcipherHandler extends AppCompatActivity {

  private SqlcipherHandlerPlugin instance;

  public SqlcipherHandler(SqlcipherHandlerPlugin instance) {
    this.instance = instance;
  }

  public JSObject openDb(String dbName, String password){
    Context context = this.instance.getActivity().getApplicationContext();
    JSObject out = DBHelper.openDb(context, dbName, password);
    return out;
  }
  public JSObject dbStatus(){
    return DBHelper.dbStatus();
  }
  public JSObject execute(String query) {
    JSObject out = DBHelper.execute(query);
    return out;
  }

  public JSObject select(String query) {
    JSObject out = DBHelper.select(query);
    return out;
  }
}
