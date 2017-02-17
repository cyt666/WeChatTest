package com.xupt.servlet;

import com.xupt.po.TextMessage;
import com.xupt.util.CheckUtil;
import com.xupt.util.MessageUtil;
import org.dom4j.DocumentException;
import sun.misc.Sort;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by as on 2017/1/14.
 */
public class WeixinServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    //开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下表所示
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = req.getParameter("signature");
        //时间戳
        String timestamp = req.getParameter("timestamp");
        //随机数
        String nonce = req.getParameter("nonce");
        //随机字符串
        String echostr = req.getParameter("echostr");

        //如果校验成功，将随机字符串返回
        System.out.println(echostr);

        PrintWriter out = resp.getWriter();
            if(CheckUtil.checkSignature(signature,timestamp,nonce)){
                out.println(echostr);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            //判断消息是否是文本消息
            String message = null;
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if ("1".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.firstMenu());
                }else if ("2".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.secondtMenu());
                }else if ("3".equals(content)){
                    message = MessageUtil.initNewsMessage(toUserName,fromUserName);
                }else if ("4".equals(content)){
                    message = MessageUtil.initImageMessage(toUserName,fromUserName);
                }else if ("5".equals(content)){
                    message = MessageUtil.initMusicMessage(toUserName,fromUserName);
                }
                else if ("?".equals(content)||"？".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }

                /*TextMessage text = new TextMessage();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().getTime());
                text.setContent("您发送的消息是："+content);
                message = MessageUtil.textMessageToXml(text);*/

            }else if (MessageUtil.MESSAGE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
                    String url = map.get("EventKey");
                    message = MessageUtil.initText(toUserName, fromUserName, url);
                }else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
                    String key = map.get("EventKey");
                    message = MessageUtil.initText(toUserName, fromUserName, key);
                }
            }else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
                String label = map.get("Label");
                message = MessageUtil.initText(toUserName, fromUserName, label);
            }

            System.out.println(message);
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

    }
}
