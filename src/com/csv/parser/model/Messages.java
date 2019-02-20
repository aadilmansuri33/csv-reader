package com.csv.parser.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Messages {

	private String topic;
	private String sentiments;
	private BigDecimal tweetId;
	private Date tweetDate;
	private String tweetText;

	public Messages() {
		// TODO Auto-generated constructor stub
	}

	public Messages(String topic, String sentiments, BigDecimal tweetId, Date tweetDate, String tweetText) {
		this.topic = topic;
		this.sentiments = sentiments;
		this.tweetId = tweetId;
		this.tweetDate = tweetDate;
		this.tweetText = tweetText;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSentiments() {
		return sentiments;
	}

	public void setSentiments(String sentiments) {
		this.sentiments = sentiments;
	}

	public BigDecimal getTweetId() {
		return tweetId;
	}

	public void setTweetId(BigDecimal bigDecimal) {
		this.tweetId = bigDecimal;
	}

	public String getTweetDate() {
		DateFormat dateFormat = null;
		Date date = null;
		try {
			dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.US);
			date = dateFormat.parse(tweetDate.toString());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		} catch (IllegalArgumentException | ParseException e) {
			// TODO: handle exception
			System.out.println("Invalid TweetDate");
		}
		return dateFormat.format(date);
	}

	public void setTweetDate(Date date2) {
		this.tweetDate = date2;
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
}
