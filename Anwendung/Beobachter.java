interface Beobachter
{
    //Beobachter werden benachrichtigt, wenn Sensoren neue Daten messen.
    public void aktualisieren();
    public void aktualisieren(String wasAktualisieren);
}
