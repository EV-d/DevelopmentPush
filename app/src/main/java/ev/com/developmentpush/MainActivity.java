package ev.com.developmentpush;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;


public class MainActivity extends ActionBarActivity {
    Button send;
    EditText text;
    CheckBox dev1,dev2;
    boolean dev1checked,dev2checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.Send);
        text=(EditText)findViewById(R.id.pushmeesage);
        dev1=(CheckBox)findViewById(R.id.dev1);
        dev2=(CheckBox)findViewById(R.id.dev2);
        dev1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                    dev1checked = true;
                }
                else
                {
                    dev1checked =false;
                }
            }
        });
          dev2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ==true)
                {
                    dev2checked = true;
                }
                else
                {
                    dev2checked =false;
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(text.getText().toString().isEmpty()==false) {
                 if((dev1checked==true) && (dev2checked==true))
                 {
                     sendpush(text.getText().toString(),"anwar");
                     sendpush(text.getText().toString(),"haitham");
                 }
                 else
                 if((dev1checked==true) && (dev2checked==false)) {
                     sendpush(text.getText().toString(), "anwar");
                 }
                 else
                  if((dev1checked==false) && (dev2checked==true))
                 {
                     sendpush(text.getText().toString(),"haitham");
                 }
                   if((dev1checked==false) && (dev2checked==false))
                 {
                     Toast.makeText(getApplicationContext(),"Please Chose at least on developer ",Toast.LENGTH_SHORT).show();
                 }
             }
            }
        });

    }

     public void sendpush(String message,String name)
    {
        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
        ParseQuery pushQuery = ParseInstallation.getQuery();
        pushQuery.whereEqualTo("Receive", true);
        pushQuery.whereEqualTo("name", name);
        ParsePush push = new ParsePush();
        push.setQuery(pushQuery); // Set our Installation query
        push.setMessage(message);
        push.sendInBackground();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Check your Intrnet Connection",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
