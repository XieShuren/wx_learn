package wx_learn;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import wx_learn.inter.receive.message.ReplyNewsArticle;
import wx_learn.inter.receive.message.ReplyNewsMessage;

public class HelloTest {
	
	/*public static void main(String[] args) throws Exception {
		ReplyNewsMessage replyNewsMessage = new ReplyNewsMessage();
		replyNewsMessage.setToUserName("toUserName");
		replyNewsMessage.setMsgType("news");
		replyNewsMessage.setFromUserName("fromUserName");
		replyNewsMessage.setCreateTime(System.currentTimeMillis());
		replyNewsMessage.setArticleCount(2);
		
		ArrayList<ReplyNewsArticle> list = new ArrayList<ReplyNewsArticle>();
		list.add(new ReplyNewsArticle("title1", "description", "picUrl", "url"));
		list.add(new ReplyNewsArticle("title2", "description", "picUrl", "url"));
		replyNewsMessage.setArticles(list);
		
		StringWriter stringWriter = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(ReplyNewsMessage.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(replyNewsMessage, stringWriter);
		System.out.println(stringWriter.toString());
	}*/
}
