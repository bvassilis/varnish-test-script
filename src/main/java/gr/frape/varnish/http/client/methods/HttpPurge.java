package gr.frape.varnish.http.client.methods;

import java.net.URI;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * HTTP PURGE method
 * <p>
 * The HTTP PURGE method is defined in section 9.7 of
 * <a href="http://www.ietf.org/rfc/rfc2616.txt">RFC2616</a>:
 * <blockquote>
 * The PURGE method requests that the origin server purge the resource
 * identified by the Request-URI. [...] The client cannot
 * be guaranteed that the operation has been carried out, even if the
 * status code returned from the origin server indicates that the action
 * has been completed successfully.
 * </blockquote>
 *
 * @since 4.0
 */
@NotThreadSafe // HttpRequestBase is @NotThreadSafe
public class HttpPurge extends HttpRequestBase {

    public final static String METHOD_NAME = "PURGE";


    public HttpPurge() {
        super();
    }

    public HttpPurge(final URI uri) {
        super();
        setURI(uri);
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public HttpPurge(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

}
