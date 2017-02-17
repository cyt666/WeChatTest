package com.xupt.util;


import com.thoughtworks.xstream.XStream;
import com.xupt.po.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by as on 2017/1/15.
 */
//将xml转为集合
public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "click";
    public static final String MESSAGE_VIEW = "view";
    public static final String MESSAGE_SCANCODE= "scancode_push";

    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        //从request中获取输入流
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for (Element e:list){
            map.put(e.getName(),e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 将文本消息对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String initText(String toUserName,String fromUserName,String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
        sb.append("1、课程介绍\n\n");
        sb.append("2、慕课网介绍\n\n");
        sb.append("3、图文消息\n\n");
        sb.append("4、图片信息\n\n");
        sb.append("5、音乐消息\n\n");
        sb.append("回复？调出此菜单。");
        return sb.toString();
    }

    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("本套课程介绍微信公众号开发，主要涉及公众号介绍，编辑模式、开发模式介绍\n\n");
        return sb.toString();
    }

    public static String secondtMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("慕课网有好多东西\n\n");
        return sb.toString();
    }

    /**
     * 将图文消息转换为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",newsMessage.getClass());
        xStream.alias("item",new News().getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 图片消息转换为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * 音乐消息转为xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */

    public static String initNewsMessage(String toUserName,String fromUserName){
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("慕课网介绍");
        news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
        news.setPicUrl("http://www.cytwechat.com.ngrok.cc/image/pic14.png");
        news.setUrl("www.bilibili.com");

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);
        return message;
    }

    /**
     *图片组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName,String fromUserName){
        String message = null;
        Image image = new Image();
        image.setMediaId("q80AG1CUeyghgBRF6ERi-bDt42kGBKMXydnzpm-gRzHRL8dHYXqqwwXTkKMCLeiP");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        return message;

    }
    /**
     * 音乐消息组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initMusicMessage(String toUserName,String fromUserName){
        String message = null;
        Music music = new Music();
        music.setThumbMediaId("u7yfm4KyTM2rGqI29dIRk7un86LypMXtL_BoBXCaaxLdzC8JhYTVVgMPggszyvOM");
        music.setTitle("this歌");
        music.setDescription("11:11");
        music.setMusicUrl("http://www.cytwechat.com.ngrok.cc/resource/See You Again.mp3");
        music.setHQMusicUrl("http://www.cytwechat.com.ngrok.cc/resource/See You Again.mp3");

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMusic(music);
        message = musicMessageToXml(musicMessage);
        return message;

    }

}
