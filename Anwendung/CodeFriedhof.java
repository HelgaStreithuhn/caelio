
public class CodeFriedhof
{
    /*
    //aus der Klasse Internet-Verbinder:
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
        
        // vgl. Get-Anfrage
        BufferedReader eingang = new BufferedReader(new InputStreamReader(anfrage.getInputStream()));
        String eingangsZeile;
        String text = "";
        while ((eingangsZeile = eingang.readLine()) != null){
            text = text + "\n" + eingangsZeile;
        }
        eingang.close();
        return text;
    }
    */
   
    /*
    // Klasse Controller, Methode sensorboxLaden: Vorgänger der selbstladenden Sensorbox
            String daten = "";
            Datensatz datensatz = sensorbox.datensatzFinden("Temperatur");
            for (int i = 0; i < datensatz.messwerteGeben().size(); i ++)
                daten += "\n" + datensatz.messwerteGeben().get(i).zuString();
    */
    
   
   
    /*
     //Klasse Sensorbox: Da kümmert sich jetzt jeder Sensor einzeln drum.
     public void datenNachladen() throws Exception
    {
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");
        JSONObject ergebnis = new JSONObject(rohdaten);
        
        //Setzt die letzte Messzeit fest
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sourceFormat.setTimeZone(utc);
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");
        letzteMessung = destFormat.format(sourceFormat.parse(ergebnis.getString("updatedAt")));
    }
    */
   
   
   /* Aus der Klasse Sensor, Konstruktor: alter Timer warf ständig Fehler
    * 
    Timer timer = new Timer();
        timer.schedule(new TimerTask()
            {
                public void run()
                {
                    try
                    {
                        messen();
                    }
                    catch (Exception fehler)
                    {
                        System.out.println("Fehler beim Messen.");
                    }
                }
            }, 0, 20000);/**/
    
}
