package service;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AssetNewsService {
    
	public StringBuilder sb;
	String keywords = "자산, 저축, 금리, 상품, 부동산, 주식";
    int displays = 12;
	
    public StringBuilder getNews() {
        String clientId = "RzpFtCOVzFOj6cT9QK4s";
        String clientSecret = "QPGjM29nTw";
        try {
            String text = URLEncoder.encode(keywords, "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=" + displays + "&sort=sim";
 
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");  
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;
 
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb;
    }

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getDisplays() {
		return displays;
	}

	public void setDisplays(int displays) {
		this.displays = displays;
	}

}
