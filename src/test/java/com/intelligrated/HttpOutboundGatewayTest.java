package com.intelligrated;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.AbstractMessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/http-outbound-config.xml")
public class HttpOutboundGatewayTest {

	@Autowired
	@Qualifier("http.out.channel") // TO http
	AbstractMessageChannel outChannel;
	
	@Autowired
	@Qualifier("http.in.channel") // FROM http
	PollableChannel inChannel;
//	MessageChannel inChannel;
	
	@Test
	public void httpOutboundReceivesResponse() {
		boolean sentSuccessfully = outChannel.send(MessageBuilder.withPayload("matt").build());
		Assert.assertTrue(sentSuccessfully);
		Message<?> message = inChannel.receive();
		assertThat(message.getPayload(), is(notNullValue()));
		System.out.println("msg payload: " + message.getPayload().toString());
	}
}
