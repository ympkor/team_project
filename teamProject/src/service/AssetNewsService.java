package service;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
 
public class AssetNewsService {
    
	public static StringBuilder sb;
 
    public StringBuilder getNews() {
        String clientId = "RzpFtCOVzFOj6cT9QK4s";
        String clientSecret = "QPGjM29nTw";
        int display = 10;
        try {
            String text = URLEncoder.encode("자산 저축 부동산 주식", "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=" + display + "&sort=sim";
 
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
}
