import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public abstract class InternetVerbinder {
    public static String httpGetAnfrage (String url) throws Exception {
        URL adresse = new URL(url);
        URLConnection verbindung = adresse.openConnection();
        BufferedReader ein = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
        String eingangsZeile;
        String text = "";
        while ((eingangsZeile = ein.readLine()) != null){
            text = text + "\n" + eingangsZeile;
        }
        ein.close();
        return text;
    }
    public static String httpPostAnfrage (String url, String args) throws Exception {
        URL adresse = new URL(url);
        URLConnection verbindung = adresse.openConnection();
        HttpURLConnection anfrage = (HttpURLConnection) verbindung;
        anfrage.setRequestMethod("POST");
        anfrage.setDoOutput(true);
        
        byte[] aus = args.getBytes(StandardCharsets.UTF_8);
        int length = aus.length;
        anfrage.setFixedLengthStreamingMode(length);
        anfrage.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        anfrage.connect();
        try(OutputStream ausgangsStrom = anfrage.getOutputStream()) {
            ausgangsStrom.write(aus);
        } catch(Exception e){
            System.out.println(e);
        }
        BufferedReader eingang = new BufferedReader(new InputStreamReader(anfrage.getInputStream()));
        String eingangsZeile;
        String text = "";
        while ((eingangsZeile = eingang.readLine()) != null){
            text = text + "\n" + eingangsZeile;
        }
        eingang.close();
        return text;
        }
    public static void versuchsAnfrage1() throws Exception{
        System.out.println(httpGetAnfrage("http://example.org/"));
    }
    public static void versuchsAnfrage2() throws Exception{
        System.out.println(httpPostAnfrage("https://httpbin.org/anything","foo=bar&te=st"));
    }
}