package com.wx.auth.servlet;

import com.wx.auth.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by as on 2017/2/7.
 */

/**
 * 入口地址
 */
@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String backUrl = "http://www.cytwechat.com.ngrok.cc/wxCallBack";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtil.APPID
                +"&redirect_uri="+ URLEncoder.encode(backUrl,"UTF-8")
                +"&response_type=code"
                +"&scope=snsapi_userinfo"
                +"&state=STATE#wechat_redirect";
        resp.sendRedirect(url);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
