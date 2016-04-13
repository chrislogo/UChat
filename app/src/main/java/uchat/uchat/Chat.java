package uchat.uchat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

import org.json.JSONException;
import org.json.JSONObject;


// webView fragment
public class Chat extends WebViewFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state)
    {
        View V = super.onCreateView(inflater, container, state);

        return V;
    }

    @Override
    public void onActivityCreated(Bundle state)
    {
        super.onActivityCreated(state);

        SharedPreferences shared_pref = getActivity().getSharedPreferences(LoginActivity.pref_string, 0);
        final String pref_response = shared_pref.getString("username", "");

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", pref_response);
        }catch(JSONException e){
            e.printStackTrace();
        }

        // change the webView according to what is clicked
        WebView WV = getWebView();
        class JsObject{
            @JavascriptInterface
            public String toSring(){return pref_response;}
        }
        WV.getSettings().setDomStorageEnabled(true);
        WV.getSettings().setJavaScriptEnabled(true);
        WV.addJavascriptInterface(new JsObject(), "username");


        WV.loadUrl("http://73.42.47.33/chat-page.html");


        // disable scroll on touch
       /* WV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });*/

        // so the app doesn't open a browser
        WV.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    String url) {

                return false;
            }
        });
    }
}
