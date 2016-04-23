package uchat.uchat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;



// webView fragment
public class Chat extends WebViewFragment {
    public String pref_response;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View V = super.onCreateView(inflater, container, state);

        return V;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        SharedPreferences shared_pref = getActivity().getSharedPreferences(LoginActivity.pref_string, 0);
        pref_response = shared_pref.getString("username", "");
        String course = shared_pref.getString("course","");


        // change the webView according to what is clicked
        WebView WV = getWebView();

        WV.getSettings().setDomStorageEnabled(true);
        WV.getSettings().setJavaScriptEnabled(true);

        String html = "http://73.42.47.33/chat-script.php?username=" + pref_response +"&room=" + course;

        WV.loadUrl(html);


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
