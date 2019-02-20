package com.csv.parser.main;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csv.parser.model.Messages;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author inexture
 *
 */
public class CsvTOBean {
	private static CSVReader csvReader;
	private static List<Messages> messageList;
	private static List<String[]> recordList;
	private static Messages messages;

	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static List<Messages> parsrMessages(String fileName) throws Exception {

		// Get File using FileReader and build a csvReader
		csvReader = new CSVReaderBuilder(new FileReader(new File(CsvTOBean.class.getResource("/"+fileName).toURI()))).withSkipLines(1).build();

		messageList = new ArrayList<Messages>();

		// Reading Records in a RecordList of String array
		recordList = csvReader.readAll();

		// for just line count
		int countLine = 1;
		// Record store in a StringArray
		for (String[] record : recordList) {

			// for identify the Line
			countLine++;
			messages = new Messages();

			// Set Record One by One in to Bean Properties
			messages.setTopic(record[0]);
			messages.setSentiments(record[1]);
			if (!record[2].isEmpty()) {
				try {
					messages.setTweetId(new BigDecimal(record[2]));
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Input is Not a valid TweetId at :" + record[2] + " Line No: " + countLine);
					continue;
				}
			} else {
				try {
					messages.setTweetId(new BigDecimal(""));
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Input is Not a valid TweetId at :" + record[2] + " Line No: " + countLine);
					continue;
				}
			}
			if (!record[3].isEmpty()) {
				try {
					messages.setTweetDate(new Date(record[3]));
				} catch (IllegalArgumentException e) {
					// TODO: handle exception
					System.out.println("Input is Not a valid Tweetdate at :" + record[3] + " Line : " + countLine);
					continue;
				}
			} else
				messages.setTweetDate(new Date(""));

			messages.setTweetText(record[4]);

			// Record store in the MessageList
			messageList.add(messages);
		}
		// Return the Record list
		return messageList;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//Change the filename below if the file has to be other than the given file.
		messageList = parsrMessages("full-corpus.csv");
		// Print all Records for Message
		for (Messages messages : messageList) {
			System.out.print(messages.getTopic() + "\t" + messages.getSentiments() + "\t" + messages.getTweetId() + "\t"
					+ messages.getTweetDate() + "\t" + messages.getTweetText() + "\n");
		}

	}
}
