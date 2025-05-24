import org.openqa.selenium.Cookie;

public class SessionCookies {

    public static final Cookie OA = new Cookie.Builder("oa", "0")
        .domain("www.overleaf.com")
        .path("/")
        .isHttpOnly(false)
        .isSecure(false)
        .build();  // session cookie

    public static final Cookie OVERLEAF_SESSION2 = new Cookie.Builder(
            "overleaf_session2",
            "your session here")
        .domain(".overleaf.com")
        .path("/")
        .isHttpOnly(true)
        .isSecure(true)
        .build();
}