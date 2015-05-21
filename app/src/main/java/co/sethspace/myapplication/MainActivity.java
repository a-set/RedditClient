package co.sethspace.myapplication;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //Create Recycler view
            final RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //Register a listener
            Button button = (Button)rootView.findViewById(R.id.button_reddit);
            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    String query = ((EditText)rootView.findViewById(R.id.edit_message)).getText().toString();
                    //If text not blank call reddit service
                    if(query!=null&&!query.matches("^\\s*$")){

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        String url ="http://www.reddit.com/r/"+query+".json";

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Create a JSON object
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            JSONArray posts = (jsonObject.getJSONObject("data")).getJSONArray("children");
                                            List<RedditPosts> rposts = new ArrayList<RedditPosts>();
                                            for(int i=0;i<posts.length();i++){
                                                rposts.add(new RedditPosts(posts.getJSONObject(i).getJSONObject("data").getString("title"),
                                                        posts.getJSONObject(i).getJSONObject("data").getInt("score"),posts.getJSONObject(i).getJSONObject("data").getString("author")));
                                            }
                                            mRecyclerView.setAdapter(new MyAdapter(rposts));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error.toString());
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);

                    };
                }
            });
            return rootView;
        }
    }
}
