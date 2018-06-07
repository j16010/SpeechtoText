package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_main {

		// TODO 自動生成されたメソッド・スタブ
		public static void main(String[] args) {
		    SpeechToText service = new SpeechToText();
		    service.setUsernameAndPassword("J16010", "J16010");
		    
		  
		    
		    File audio = new File("./audio/output.wav");
		    RecognizeOptions options = null;
			
		    
		    try {
				options = new RecognizeOptions.Builder()
					.model("ja-JP_BroadbandModel")
					.audio(audio)
				    .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
				    .build();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		    SpeechRecognitionResults transcript = service.recognize(options).execute();
		    
		    //String s=String.valueOf(transcript);
		    ObjectMapper mapper = new ObjectMapper();
			
			JsonNode node = null;
			try {
				 node = mapper.readTree(transcript.toString());
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		    System.out.println(transcript);

		    String result= node.get("results").get(0).get("alternatives").get(0).get("transcript").toString();
			System.out.println("results: "+result);
			
			
			double confidence= node.get("results").get(0).get("alternatives").get(0).get("confidence").asDouble();
			System.out.println("confidence: "+confidence);
			
			
			 MySQL mysql=new MySQL();//6月7日
			  mysql.updateImage(result, confidence);
	}

}
