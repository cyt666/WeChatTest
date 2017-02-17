package com.wx.auth.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by as on 2017/2/7.
 */
public class AuthUtil {
    public static final String APPID = "wx247e94f6b6c15b2d";
    public static final String APPSECRET = "0004711f2cfb8b89a2976febab489879";
    public static JSONObject doGetJson(String url) throws IOException,ClientProtocolException{
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity!=null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);

        }
        httpGet.releaseConnection();
        return jsonObject;
    }
}
