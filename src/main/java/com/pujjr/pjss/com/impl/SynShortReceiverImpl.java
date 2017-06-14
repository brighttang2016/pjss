package com.pujjr.pjss.com.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.pujjr.pjss.com.ISynShortReceiver;
import com.pujjr.pjss.com.ISynShortSender;
import com.pujjr.utils.Utils;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author tom
 *
 */
public class SynShortReceiverImpl implements ISynShortReceiver{
	private static final Logger logger = Logger.getLogger(SynShortReceiverImpl.class);
	@Override
	public void doReceive(String recStr,final ChannelHandlerContext ctx) {
		logger.info("receive from client："+recStr);
		System.out.println("receive from client："+recStr);
		String sendStr = "";
		String tranCode = "";
		JSONObject recJson = new JSONObject();
		try {
			recJson = JSONObject.parseObject(recStr);
			tranCode = recJson.getString("tranCode");
			logger.info("tranCode："+tranCode);
		} catch (Exception e) {
			logger.error("客户端报文错误，报文recStr："+recStr);
			System.out.println("客户端报文错误，报文recStr："+recStr);
		}
		String appId = recJson.getString("appId");
		long timeBegin = System.currentTimeMillis();
		
		
//		返回空数组(反空测试打开)
//		String filePath = SynShortReceiverImpl.class.getClassLoader().getResource("").getPath();
		sendStr = Utils.getProperty("send.msg", "pjss.properties", Utils.getJarConfFilepath());
		long timeEnd = System.currentTimeMillis();
	    logger.info("执行完成，耗时："+(timeEnd-timeBegin)/1000);
	    System.out.println("执行完成，耗时："+(timeEnd-timeBegin)/1000);
		ISynShortSender sender = new SynShortSenderImpl();
		sender.doSend(sendStr, ctx);
	}

}
