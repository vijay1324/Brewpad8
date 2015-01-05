package brewpad8.technify.agr.brewpad8;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;


public class LoginActivity extends ActionBarActivity {

    EditText uname,pword;
    Button login, clear;
    ProgressDialog pd;
    String format, username, password, deviceid, status, dbname;
    String json;
    String qry[];
    ArrayList<String> al_qry=new ArrayList<String>();
    HttpPost get;
    List<NameValuePair> nameValuePairs;
    HttpClient client;
    String query="";

    final static String URL = "http://192.168.1.36:8080/Brewpadserver/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.login_label);
        setContentView(R.layout.activity_login);
        LinearLayout lv = (LinearLayout) findViewById(R.id.llay);
        uname = (EditText) findViewById(R.id.etname);
        pword = (EditText) findViewById(R.id.etpwt);
        login = (Button) findViewById(R.id.btnsub);
        clear = (Button) findViewById(R.id.btnclr);
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceid = deviceUuid.toString();
        client = new DefaultHttpClient();

        /*final TextView pdtv = new TextView(this);
        pdtv.setText("Connect to Server");
        pdtv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        pdtv.setTextColor(Color.RED);*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                username = uname.getText().toString();
                password = pword.getText().toString();
                pd = new ProgressDialog(LoginActivity.this);
                String s = "Connect to Server";
                String title = "Wait...";
                SpannableString ss1 = new SpannableString(title);
                ss1.setSpan(new RelativeSizeSpan(2f), 0, ss1.length(), 0);
                ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, ss1.length(), 0);
                SpannableString ss2 = new SpannableString(s);
                ss2.setSpan(new RelativeSizeSpan(2f), 0, ss2.length(), 0);
                ss2.setSpan(new ForegroundColorSpan(Color.GREEN), 0, ss2.length(), 0);
                //pd.setView(pdtv);
                //pd.setMessage("Connect to Server");
                pd.setTitle(ss1);
                pd.setMessage(ss2);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setIndeterminate(true);
                pd.show();
                if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
                    pd.dismiss();
                    Utility.ShowMessageBox(LoginActivity.this, "Please Enter Username & Password.", getResources().getDimension(R.dimen.textsize));
                    uname.hasFocus();
                }
                else
                new Read().execute();
                //Toast.makeText(getApplicationContext(), androidId, Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname.setText("");
                pword.setText("");
                Intent i = new Intent(LoginActivity.this, IndexPage.class);
                startActivity(i);
                LoginActivity.this.finish();
            }
        });



    }
    public String lastTweet(String uname) throws ClientProtocolException, IOException {
        StringBuilder url = new StringBuilder(URL);
        url.append(uname);
        get = new HttpPost(url.toString());
//    	HttpResponse r = client.execute(get);
//    	int Status = r.getStatusLine().getStatusCode();
//    	if(Status == 200)
//    	{

        nameValuePairs = new ArrayList<NameValuePair>(6);
        nameValuePairs.add(new BasicNameValuePair("format", "start"));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("deviceid", deviceid));
        nameValuePairs.add(new BasicNameValuePair("status", "1"));
        nameValuePairs.add(new BasicNameValuePair("dbname", ""));
        get.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Execute HTTP Post Request
        //System.out.println("Go to Responce=====>");
        HttpResponse r = client.execute(get);
        //System.out.println("Go to ResponceResult=====>"+r);
        int Status = r.getStatusLine().getStatusCode();
        if (Status != 200) {
            //Utility.ShowMessageBox(MainActivity.this, "Server not Running", txtsize);
        } else {
            //Utility.ShowMessageBox(MainActivity.this, "Server Running", txtsize);


            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
    		/*JSONArray timeline = new JSONArray(data);
    		JSONObject last = timeline.getJSONObject(0);*/
            System.out.println(data);
            return data;
        }
//    	}else
//    	{
//
//    		Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
        return null;
//    	}
    }

    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                //System.out.println("Try");
                json = lastTweet("init_call");//url java servelt file name
                //System.out.println("json====>"+json);
                if (json.equalsIgnoreCase("23"))
                    Log.e("Error", "New Devices");
                else
                    return json;
                return null;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
			/*tv.setText(result);*/
            System.out.println("onPostExecute Result=====>" + result);
            if (result == null) {
                pd.dismiss();
                Utility.ShowMessageBox(LoginActivity.this, "Server not Responce or Inorrect Password", getResources().getDimension(R.dimen.textsize));
            } else {
                //String rq = "update bp_companydetails set companyname='Prodemo Brewing Company',add1='400 Rte 9',add2='',city='NEWARK',state='NJ',zip='07101',boiltime='0' where companyid='1'^update bp_defaultdetails set lowerstreamstatus='1' where id='1'^insert into am_packagelist (packagename, volume, status) values ('1/2 BBL','0.5','1');^insert into am_packagelist (packagename, volume, status) values ('1/6 BBL','0.16667','1');^insert into am_packagelist (packagename, volume, status) values ('22 oz cases','0.06653','2');^insert into am_packagelist (packagename, volume, status) values ('2 - 12 packs','0.07258','2');^insert into am_packagelist (packagename, volume, status) values ('Case of 6 packs','0.07258','2');^insert into am_packagelist (packagename, volume, status) values ('Firkin','0.35484','1');^insert into am_packagelist (packagename, volume, status) values ('Pin','0.17742','1');^insert into am_packagelist (packagename, volume, status) values ('2/12 Cans','.07258','2');^insert into am_packagelist (packagename, volume, status) values ('4/6/24 Cans','.07258','2');^insert into am_packagelist (packagename, volume, status) values ('1 BBL','1','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('2','FT01','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('3','FT02','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('4','FT03','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('5','FT04','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('6','FT05','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('7','FT06','30','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('8','FT07','60','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('9','FT08','60','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('10','FT09','90','1','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('11','FT10','90','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('12','FT11','15','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('13','FT12','15','1','0','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('14','Oak01','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('15','Oak02','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('16','Oak03','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('17','Oak04','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('18','Oak05','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('19','Oak06','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('20','Oak07','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('21','Bourbon01','2','0','1','0','1');^insert into am_tanklist (lineid,tankname,capacity,fermentation,conditioning,packaging,status) values ('22','Bourbon02','2','0','1','0','1');";
                StringTokenizer st = new StringTokenizer(result, "^");
                //SaveData sd = new SaveData();

			/*while(st.hasMoreTokens())
			{
				query=(String) st.nextToken();
				tv.setText("Success");
				//sd.SaveDataa(query);
				SaveData(null);
				System.out.println("Query====>"+query);
			}*/

                al_qry = new ArrayList<String>();

//			qry = new String[(st.countTokens())];
                for (int i = 0; st.hasMoreTokens(); i++) {
//				qry[i] = ;
                    al_qry.add(st.nextToken("^"));
                    //System.out.println("Query"+"["+i+"]"+al_qry);
                }
                //System.out.println("Query====>"+al_qry);
                SaveData(null);
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);

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
        }else if(id == R.id.link)
        {
            Toast.makeText(LoginActivity.this, "Go To Brewpat Website", Toast.LENGTH_LONG).show();
           /* Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com"));
            startActivity(i);*/
        }

        return super.onOptionsItemSelected(item);
    }


    public void SaveData(View v)
    {
        //String query = "insert into produce_eventlist (eventname,stagename,value,type) values ('Sparge end','Vorlauf','857','1');";
        //System.out.println("ONCLICK");
        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
	    	/*String sq = "";
	    	for(int i = 0; i<=qry.length;i++)
	    	{
	    		sq = qry[i];
	    		mDbHelper.SaveDataa(sq);
	    	}
*/	    	//System.out.println("Al_Qry===>"+al_qry);
        if(mDbHelper.SaveDataa(al_qry))
        {
            Utility.ShowMessageBox(this,"Data saved.", getResources().getDimension(R.dimen.textsize));
            LoadSaved(v);
            //System.out.println("Data saved.");
        }
        else
        {
            Utility.ShowMessageBox(this,"OOPS try again!", getResources().getDimension(R.dimen.textsize));
        }
    }

    public void LoadSaved(View v)
    {
        pd.dismiss();
        Intent i = new Intent(LoginActivity.this, IndexPage.class);
        startActivity(i);
        LoginActivity.this.finish();

	    }

}
