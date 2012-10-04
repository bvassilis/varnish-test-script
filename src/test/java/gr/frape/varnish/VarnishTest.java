package gr.frape.varnish;

import static org.junit.Assert.assertEquals;
import gr.frape.varnish.http.client.methods.HttpPurge;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class VarnishTest {

    static AbstractHttpClient client;

    @BeforeClass
    public static void beforeClass() {
        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
        client = new DefaultHttpClient(params);
    }

    @AfterClass
    public static void afterClass() {
        client.getConnectionManager().shutdown();
    }

    @Test
    public void testGetRuleRequest() throws ClientProtocolException, IOException {
        String[] varnishUris = {};
        for (String varnishUri : Arrays.asList(varnishUris)) {
            HttpGet request = new HttpGet(varnishUri);
            HttpContext localContext = new BasicHttpContext();
            
            /**
             * client add cookie
             */
            // CookieStore cookieStore = new BasicCookieStore();
            // localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            // client.getCredentialsProvider().setCredentials(new AuthScope("localhost", 443), new UsernamePasswordCredentials("username", "password"));

            HttpResponse response = client.execute(request, localContext);
            
            // check status
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
            
            // check cookies
            // List<Cookie> cookies = client.getCookieStore().getCookies();
            
            // check Headers 
            /** 
             *  String varnishHeaderName = null;
             *  String varnishHeaderValue = null;
             *  Header[] varnishHeaders = response.getHeaders(varnishHeaderName);
             *  for (Header varnishHeader : varnishHeaders) {
             *      assertEquals(varnishHeaderValue, varnishHeader.getValue());
             *  } 
             */
        }
    }

    @Test
    public void testPostRuleRequest() throws ClientProtocolException, IOException {
        String[] varnishUris = {};
        for (String varnishUri : Arrays.asList(varnishUris)) {
            HttpPost post = new HttpPost(varnishUri);
            /**
             *  add form params pairs
             */
            // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            // nameValuePairs.add(new BasicNameValuePair("name", "value"));
            // post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            
            // check status
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        }
    }
    
    /**
     * Same
     * HttpDelete; HTTP DELETE method
     * HttpHead; HTTP HEAD method.
     * HttpOptions; HTTP OPTIONS method.
     * HttpPatch; HTTP PATCH method.
     * HttpPut; HTTP PUT method.
     * HttpTrace; HTTP TRACE method.
     */
    

    /**
     * What about No API method?
     * example by PURGE method
     */
    @Test
    public void testOtherRuleMethodRequest() throws ClientProtocolException, IOException {
        String[] varnishUris = {};
        for (String varnishUri : Arrays.asList(varnishUris)) {
            HttpPurge purge = new HttpPurge(varnishUri);
            HttpResponse response = client.execute(purge);
            
            // check status
            assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        }
    }

}
