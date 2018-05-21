package wx_learn.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wx_learn.MessageHandler;
import wx_learn.WeixinMessageDigest;
import wx_learn.inter.InterMessageConverter;

@RestController
public class ValidateController {
	
	@RequestMapping(value="/wx", method=RequestMethod.GET)
	public String wxValidate(String signature, String timestamp, String nonce, String echostr) {
		 WeixinMessageDigest wxDigest = WeixinMessageDigest.getInstance();  
		 if (signature == null || timestamp == null || nonce == null || echostr == null)
			 return null;
	     boolean bValid = wxDigest.validate(signature, timestamp, nonce);
	     if (bValid)
	    	 return echostr;
	     else
	    	 return null;
	}
	
	@RequestMapping(value="/wx", method=RequestMethod.POST)
	public String wxReceive(HttpServletRequest request) {
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		System.out.println(jb.toString());
		String result = "success";
		try {
			MessageHandler handler = InterMessageConverter.converte(jb.toString());
			result = handler.handle().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
