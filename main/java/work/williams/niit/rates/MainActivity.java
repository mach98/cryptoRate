package work.williams.niit.rates;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import controllers.AppController;
import model.CardItems;
import model.CurrencyAdapter;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<CardItems> cardItemsList;
    private CurrencyAdapter currencyAdapter;
    //String btc,eth,ltc,nxt,dash,btcd,zec, jsonResponse;
    private String TAG = MainActivity.class.getSimpleName();
    private static final String URL_DATA= "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH,LTC,DASH,NXT,ZEC,BTCD&tsyms=NGN";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        recyclerView = findViewById(R.id.recyclerView);
        /*progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();*/
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardItemsList = new ArrayList<>();
        currencyAdapter = new CurrencyAdapter(cardItemsList,this);
        populateCurrency();
        recyclerView.setAdapter(currencyAdapter);
    }
    private void populateCurrencyList(){
        cardItemsList.add(new CardItems("BTC", 45.55));
        cardItemsList.add(new CardItems("LTC", 789.98));
        cardItemsList.add(new CardItems("ETH", 3345.9));
        cardItemsList.add(new CardItems("NXT", 4446.00));
        cardItemsList.add(new CardItems("ZEC", 337373.87));
        cardItemsList.add(new CardItems("DASH", 3434353.433));
        cardItemsList.add(new CardItems("BTCD", 445656.034));
    }

   /* private void showprogressDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideprogressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
*/
    private void populateCurrency(){
       // showprogressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(GET, URL_DATA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject btc = response.getJSONObject("BTC");
                    JSONObject ltc = response.getJSONObject("LTC");
                    JSONObject eth = response.getJSONObject("ETH");
                    JSONObject nxt = response.getJSONObject("NXT");
                    JSONObject zec = response.getJSONObject("ZEC");
                    JSONObject dash = response.getJSONObject("DASH");
                    JSONObject btcd = response.getJSONObject("BTCD");

                    String btcVal = btc.getString("NGN");
                    String ltcVal = ltc.getString("NGN");
                    String ethVal = eth.getString("NGN");
                    String nxtVal = nxt.getString("NGN");
                    String zecVal = zec.getString("NGN");
                    String dashVal = dash.getString("NGN");
                    String btcdVal = btcd.getString("NGN");

                    cardItemsList.add(new CardItems("BTC", Double.parseDouble(btcVal)));
                    cardItemsList.add(new CardItems("LTC", Double.parseDouble(ltcVal)));
                    cardItemsList.add(new CardItems("ETH", Double.parseDouble(ethVal)));
                    cardItemsList.add(new CardItems("NXT", Double.parseDouble(nxtVal)));
                    cardItemsList.add(new CardItems("ZEC", Double.parseDouble(zecVal)));
                    cardItemsList.add(new CardItems("DASH", Double.parseDouble(dashVal)));
                    cardItemsList.add(new CardItems("BTCD", Double.parseDouble(btcdVal)));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
               // hideprogressDialog();
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
               // hideprogressDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    }
