package solutions.whoami.capacitor.sqlcipher.handler;

import android.content.Context;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;


@CapacitorPlugin(name = "SqlcipherHandler")
public class SqlcipherHandlerPlugin extends Plugin {

  private SqlcipherHandler implementation = new SqlcipherHandler(this);

  @PluginMethod
  public void openDb(PluginCall call) {
    String dbName = call.getString("dbName");
    String password = call.getString("password");
    JSObject ret = new JSObject();
    ret.put("response", implementation.openDb(dbName,password));
    call.resolve(ret);
  }
  @PluginMethod
  public void dbStatus(PluginCall call) {
    JSObject ret = new JSObject();
    ret.put("response", implementation.dbStatus());
    call.resolve(ret);
  }
  @PluginMethod
  public void execute(PluginCall call) {
    String query = call.getString("query");
    JSObject ret = new JSObject();
    ret.put("response", implementation.execute(query));
    call.resolve(ret);
  }

  @PluginMethod
  public void select(PluginCall call) {
    String query = call.getString("query");
    JSObject ret = new JSObject();
    ret.put("response", implementation.select(query));
    call.resolve(ret);
  }
}
