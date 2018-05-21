package wx_learn.inter;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import wx_learn.MessageHandler;
import wx_learn.inter.receive.message.ReceiveTextMessage;
import wx_learn.inter.receive.message.ReceiveTextMessageHandler;

public class InterMessageConverter {

	public static MessageHandler converte(String msg) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(msg.getBytes("UTF-8")));
		
		Element rootElement = document.getRootElement();
		if (!"xml".equals(rootElement.getName()))
			throw new RuntimeException("xml message root tag not <xml>");
		Element msgTypeEle = rootElement.element("MsgType");
		if (msgTypeEle == null)
			throw new RuntimeException("xml message don't have <MsgType> tag");
		String msgType = msgTypeEle.getTextTrim();
		switch(msgType) {
			case "text":
				JAXBContext jc = JAXBContext.newInstance(ReceiveTextMessage.class);
				Unmarshaller u = jc.createUnmarshaller();
				ReceiveTextMessage message = (ReceiveTextMessage) u.unmarshal(new ByteArrayInputStream(msg.getBytes("UTF-8")));
				return new ReceiveTextMessageHandler(message);
			default:
				throw new RuntimeException("cannot know the '"+msgType+"' msgType");
		}
	}

}
