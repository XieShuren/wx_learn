package wx_learn.inter.receive.message;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import wx_learn.MessageHandler;

public class ReceiveTextMessageHandler extends MessageHandler<ReceiveTextMessage>{

	public ReceiveTextMessageHandler(ReceiveTextMessage message) {
		super(message);
	}

	@Override
	public Object handle() throws Exception {
		StringWriter writer = new StringWriter();
		if ("这辈子不可能打工".equals(this.message.getContent())) {
			ReplyNewsMessage replyNewsMessage = new ReplyNewsMessage();
			replyNewsMessage.setToUserName(this.message.getFromUserName());
			replyNewsMessage.setMsgType("news");
			replyNewsMessage.setFromUserName(this.message.getToUserName());
			replyNewsMessage.setCreateTime(System.currentTimeMillis());
			replyNewsMessage.setArticleCount(1);
			
			ArrayList<ReplyNewsArticle> list = new ArrayList<ReplyNewsArticle>();
			ReplyNewsArticle replyNewsArticle = new ReplyNewsArticle();

			replyNewsArticle.setTitle("这辈子不可能打工");
			replyNewsArticle.setDescription("对，这句话就是我说的！");
			replyNewsArticle.setPicUrl("http://shuren2018.applinzi.com/weixing01.jpg");
			replyNewsArticle.setUrl("http://shuren2018.applinzi.com/index");
			list.add(replyNewsArticle);
			replyNewsMessage.setArticles(list);
			JAXBContext jaxbContext = JAXBContext.newInstance(ReplyNewsMessage.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(replyNewsMessage, writer);
		} else {
			ReplyTextMessage replyTextMessage = new ReplyTextMessage();
			replyTextMessage.setFromUserName(this.message.getToUserName());
			replyTextMessage.setToUserName(this.message.getFromUserName());
			replyTextMessage.setMsgType("text");
			replyTextMessage.setCreateTime(System.currentTimeMillis());
			replyTextMessage.setContent("你是说："+message.getContent());
			JAXBContext jc = JAXBContext.newInstance(ReplyTextMessage.class);
			Marshaller createMarshaller = jc.createMarshaller();
			createMarshaller.marshal(replyTextMessage, writer);
			
		}
		return writer.toString();
	}

}
