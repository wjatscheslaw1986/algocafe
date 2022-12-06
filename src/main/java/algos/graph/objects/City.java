/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

public enum City {
    SAINT_PETERSBURG("Saint-Petersburg", 59.922196, 30.311909),
    HELSINKI("Helsinki", 60.169527, 24.951914),
    VYBORG("Vyborg", 60.710713, 28.749180),
    PETROZAVODSK("Petrozavodsk", 61.784920, 34.346541),
    GREAT_NOVGOROD("Novgorod the Great", 58.522976, 31.269246),
    PSKOV("Pskov", 57.819140, 28.332373),
    MOSCOW("Moscow", 55.755793, 37.617134),
    TVER("Tver", 55.755793, 37.617134),
    KALUGA("Kaluga", 55.184217, 30.202878),
    TULA("Tula", 54.193097, 37.617134),
    VLADIMIR("Vladimir", 56.129057, 40.406635),
    RYAZAN("Ryazan'", 54.629665, 39.741680),
    VOLOGDA("Vologda", 59.220664, 39.891373),
    YAROSLAVL("Yaroslavl", 57.626510, 39.893734),
    KOSTROMA("Kostroma", 57.767918, 40.926894),
    TIHVIN("Tihvin", 59.644284, 33.542112),
    VITEBSK("Vitebsk", 55.184217, 30.202878),
    TALLIN("Tallin", 59.437511, 24.744541),
    SMOLENSK("Smolensk", 54.782751, 32.045179),
    MOGILEV("Mogilev", 53.894451, 30.330573),
    MINSK("Minsk", 53.902156, 27.561247),
    SHLISSELBURG("Shlisselburg", 59.944936, 31.034620),
    SARANSK("Saransk", 54.187433, 45.183938),
    PENZA("Penza", 53.195108, 45.017682),
    BRYANSK("Bryansk", 53.243400, 34.363991),
    KIEV("Kiev", 50.450350, 30.523526),
    KHARKIV("Kharkiv", 49.992174, 36.230754),
    VINNITSA("Vinnitsa", 49.232335, 28.468333),
    DNIPRO("Dnipro", 48.464895, 35.045568),
    VORONEZH("Voronezh", 51.660702, 39.199900),
    LVOV("Lvov", 49.839205, 24.029721),
    ODESSA("Odessa", 46.484396, 30.731414),
    KAZAN("Kazan", 55.795947, 49.105922),
    KIROV("Kirov", 58.603595, 49.668023);

    private String enTextual;
    private double latitude;
    private double longitude;

    City(String s, double lat,  double lon) {
        this.enTextual = s;
        this.latitude = lat;
        this.longitude = lon;
    }

    public boolean equals(City other) {
        return this.enTextual.equals(other.toString()) && this.latitude == other.getLatitude() && this.longitude == other.getLongitude();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return enTextual;
    }
}
