/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

public enum Crossroad {

    MALIY_PROSPECT_DONSKAYA(59.943624d, 30.262850d, "Перекрёсток Малого проспекта и Донской улицы"),
    DONSKAYA_NEMANSKY(59.940460d, 30.266566d, "Перекрёсток улицы Донской и Неманского переулка"),
    NEMANSKY_PER_14_15_LINES(59.940764d, 30.267803d, "Перекрёсток Неманского переулка и 14-15 линий"),
    NEMANSKY_PER_16_17_LINES(59.940077d, 30.265338d, "Перекрёсток Неманского переулка и 16-17 линий"),
    MALIY_PROSPECT_18_19_LINES(59.942499d, 30.258880d, "Перекрёсток Малого проспекта и 18-19 линий"),
    MALIY_PROSPECT_16_17_LINES(59.943283d, 30.261440d, "Перекрёсток Малого проспекта и 16-17 линий"),
    MALIY_PROSPECT_14_15_LINES(59.943960d, 30.264087d, "Перекрёсток Малого проспекта и 14-15 линий"),
    MALIY_PROSPECT_12_13_LINES(59.944750d, 30.266572d, "Перекрёсток Малого проспекта и 12-13 линий"),
    MALIY_PROSPECT_10_11_LINES(59.945475d, 30.269192d, "Перекрёсток Малого проспекта и 10-11 линий"),
    MALIY_PROSPECT_8_9_LINES(59.946197d, 30.271619d, "Перекрёсток Малого проспекта и 8-9 линий"),
    SREDNIY_PROSPECT_18_19_LINES(59.938697d, 30.263411d, "Перекрёсток Среднего проспекта и 18-19 линий"),
    SREDNIY_PROSPECT_16_17_LINES(59.939462d, 30.265936d, "Перекрёсток Среднего проспекта и 16-17 линий"),
    SREDNIY_PROSPECT_14_15_LINES(59.940077d, 30.265338d, "Перекрёсток Среднего проспекта и 14-15 линий"),
    SREDNIY_PROSPECT_12_13_LINES(59.940933d, 30.271084d, "Перекрёсток Среднего проспекта и 12-13 линий"),
    SREDNIY_PROSPECT_10_11_LINES(59.941679d, 30.273586d, "Перекрёсток Среднего проспекта и 10-11 линий"),
    SREDNIY_PROSPECT_8_9_LINES(59.942385d, 30.276066d, "Перекрёсток Среднего проспекта и 8-9 линий"),
    BOLSHOY_PROSPECT_8_9_LINES(59.938485d, 30.280603d, "Перекрёсток Большого проспекта и 8-9 линий"),
    BOLSHOY_PROSPECT_10_11_LINES(59.937727d, 30.278112d, "Перекрёсток Большого проспекта и 10-11 линий"),
    BOLSHOY_PROSPECT_12_13_LINES(59.936977d, 30.275547d, "Перекрёсток Большого проспекта и 12-13 линий"),
    BOLSHOY_PROSPECT_14_15_LINES(59.936256d, 30.273056d, "Перекрёсток Большого проспекта и 14-15 линий"),
    BOLSHOY_PROSPECT_16_17_LINES(59.935491d, 30.270623d, "Перекрёсток Большого проспекта и 16-17 линий"),
    BOLSHOY_PROSPECT_18_19_LINES(59.934778d, 30.268134d, "Перекрёсток Большого проспекта и 18-19 линий"),

    SMOLENKA_EMB_8_9_LINES(59.948642d, 30.268834d, "Перекрёсток набережной Смоленки и 8-9 линий"),
    SMOLENKA_EMB_10_11_LINES(59.947742d, 30.266452d, "Перекрёсток набережной Смоленки и 10-11 линий"),
    KAMSKAYA_SMOLENKA_EMB_12_13_LINES(59.947665d, 30.263453d, "Перекрёсток Камской - набережной Смоленки и 12-13 линий"),
    KAMSKAYA_14_15_LINES(59.947580d, 30.259914d, "Перекрёсток Камской и 14-15 линий"),
    KAMSKAYA_16_17_LINES(59.946874d, 30.257151d, "Перекрёсток Камской и 16-17 линий");

    private double lat, lon;
    private String description;

    Crossroad(double lat, double lon, String desc) {
        this.lat = lat;
        this.lon = lon;
        this.description = desc;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getDescription() {
        return description;
    }
}