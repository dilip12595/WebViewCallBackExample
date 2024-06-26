package com.example.webviewcallbackexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class WebViewActivity : AppCompatActivity() {

    // Web view id
    private var loadWebView: WebView? = null

    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)

        // External url that will be load in web view
        val url = "https://visionias.in/student/pt/test_result_custom.php?test_id=13020"

        // Retrieving view id from the xml file
        loadWebView = findViewById(R.id.webViewBtn)

        // Setting javascript enabled true
        loadWebView?.settings?.javaScriptEnabled = true

        // Setting webViewClient so we can load the web view in app activity otherwise it will
        // open in external browser
        loadWebView?.webViewClient = WebViewClient()

        // Adding JavaScript Interface to get callback from web page
        loadWebView?.addJavascriptInterface(WebAppInterface(this), "Android")

        // Load your webpage using web url
        // loadWebView?.loadUrl(url)

        // Load html data into web view
        loadWebView?.loadData("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>WebView Test</title>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        function sendToAndroid() {\n" +
                "            Android.getData(\"Hello from WebView\");\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Hello WebView</h1>\n" +
                "    <button onclick=\"sendToAndroid()\">Click me to get callback Data</button>\n" +
                "</body>\n" +
                "</html>", "text/html; charset=utf-8", "UTF-8")

    }

    class WebAppInterface(private val mContext: WebViewActivity) {

        /** Get the data as per the requirements from the web page */
        @JavascriptInterface
        fun getData(data: String) {
            // Handle the callback here
            // For example, you can show a toast or start a new activity

            // Toast.makeText(mContext, data, Toast.LENGTH_LONG).show()

            // Send callback data to your Activity as per the requirements
            val intent = Intent(mContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("data", data)
            mContext.startActivity(intent)
        }
    }
}
