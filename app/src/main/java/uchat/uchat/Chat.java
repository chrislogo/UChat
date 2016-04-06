package uchat.uchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;


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

        // change the webView according to what is clicked
        WebView WV = getWebView();

       WV.loadUrl("http://73.42.47.33/chat-page.html");

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
