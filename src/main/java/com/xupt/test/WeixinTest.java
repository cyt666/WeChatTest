package com.xupt.test;

import com.xupt.po.AccessToken;
import com.xupt.util.WeixinUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Created by as on 2017/1/17.
 */
public class WeixinTest {
    public static void main(String[] args) {
        try {
            AccessToken token = WeixinUtil.getAccessToken();
            System.out.println("票据"+token.getToken());
            System.out.println("有效时间"+token.getExpiresIn());

            String path = "C:/Users/as/Desktop/1.jpg";
        //    String mediaId = WeixinUtil.upload(path,token.getToken(),"image");
        //    String mediaId = WeixinUtil.upload(path,token.getToken(),"thumb");
        //    System.out.println(mediaId);

            String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
            int result = WeixinUtil.createMenu(token.getToken(),menu);
            if (result==0){
                System.out.println("创建菜单成功");
            }
            else {
                System.out.println("错误码："+result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
