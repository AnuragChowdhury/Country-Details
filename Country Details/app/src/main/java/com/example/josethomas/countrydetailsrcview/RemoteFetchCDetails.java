package com.example.josethomas.countrydetailsrcview;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by JoseThomas on 3/22/2016.
 */
public class RemoteFetchCDetails {

    public static void main (String args[]){
        String countryDetails = "";
        String countryName = "United States";

        System.out.println("I am here:");
        try {

            /*String[] isoCountryCodes = Locale.getISOCountries();
            for (String countryCode : isoCountryCodes) {
                Locale locale = new Locale("", countryCode);
                countryName = locale.getDisplayCountry();
                countryName = countryName.replace(" ","-").toLowerCase();
                System.out.println("Country Name: "+countryName);

            }*/
            String[] isoCountryCodes = Locale.getISOCountries();
            for (String countryCode : isoCountryCodes) {
                Locale locale = new Locale("", countryCode);
                countryName = locale.getDisplayCountry();
                if(countryName.toLowerCase().equals("india")){
                    System.out.println("Country Code: "+countryCode);
                }
            }










            /*String GET_TIME_URL = "http://www.bing.com/search?q=time+"+countryName;
            String GET_CAPITAL_URL = "http://www.bing.com/search?q=capital+"+countryName;
            String GET_CURRENCY_URL = "http://www.bing.com/search?q=currency+"+countryName;

            String GET_LEADERS_URL = "http://www.bing.com/search?q=currency+"+countryName;

            Document doc_getTime = Jsoup.connect(GET_TIME_URL)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.bing.com")
                    .get();

            Document doc_getCapital = Jsoup.connect(GET_CAPITAL_URL)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.bing.com")
                    .get();

            Document doc_getCurrency = Jsoup.connect(GET_CURRENCY_URL)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.bing.com")
                    .get();

            Elements currentTime = doc_getTime.select("div[class=b_focusTextLarge]");
            Elements currentDate = doc_getTime.select("div[class=b_secondaryfocus]");
            Elements timeText = doc_getTime.select("div[class=b_focusLabel]");

            Elements capital = doc_getCapital.select("div[class=b_focusTextMedium]");
            Elements currency = doc_getCurrency.select("div[class=b_focusTextMedium]");


            HashMap <String,String> detalsMap = new HashMap <String,String>();
            detalsMap.put("Time",currentTime.text());
            detalsMap.put("TimeText",timeText.text());
            detalsMap.put("Date",currentDate.text());
            detalsMap.put("Capital",capital.text());
            detalsMap.put("Currency", currency.text());

            System.out.println(detalsMap.get("TimeText"));
            System.out.println(detalsMap.get("Time"));
            System.out.println(detalsMap.get("Date"));
            System.out.println(detalsMap.get("Capital"));
            System.out.println(detalsMap.get("Currency"));*/



        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }

        //return countryDetails;
    }

     public static Map<String,String> getCountryDetails (String countryName){

         HashMap <String,String> detailsMap = new HashMap <String,String>();

        if(countryName.isEmpty()){
            return  detailsMap;
        }


         try {
             String GET_TIME_URL = "http://www.bing.com/search?q=time+"+countryName;
             String GET_CAPITAL_URL = "http://www.bing.com/search?q=capital+"+countryName;
             String GET_CURRENCY_URL = "http://www.bing.com/search?q=currency+"+countryName;
             String GET_POPULATION_URL = "http://www.bing.com/search?q=population+"+countryName;

             Document doc_getTime = Jsoup.connect(GET_TIME_URL)
                     .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                     .referrer("http://www.bing.com")
                     .get();

             Document doc_getCapital = Jsoup.connect(GET_CAPITAL_URL)
                     .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                     .referrer("http://www.bing.com")
                     .get();

             Document doc_getCurrency = Jsoup.connect(GET_CURRENCY_URL)
                     .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                     .referrer("http://www.bing.com")
                     .get();

             Document doc_getPopulation = Jsoup.connect(GET_POPULATION_URL)
                     .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                     .referrer("http://www.bing.com")
                     .get();

             Elements currentTime = doc_getTime.select("div[class=b_focusTextLarge]");
             Elements currentDate = doc_getTime.select("div[class=b_secondaryFocus]");
             Elements timeText = doc_getTime.select("div[class=b_focusLabel]");
             Elements population = doc_getPopulation.select("div[class=b_focusTextMedium]");

             Elements capital = doc_getCapital.select("div[class=b_focusTextMedium]");
             if(capital == null || capital.text().isEmpty()){
                 capital = doc_getCapital.select("div[class=b_focusTextLarge]");
                 if(capital == null || capital.text().isEmpty()){
                     capital = doc_getCapital.select("div[class=b_secondaryFocus]");
                 }
             }

             Elements currency = doc_getCurrency.select("div[class=b_focusTextMedium]");
             if(currency == null || currency.text().isEmpty()){
                 currency = doc_getCurrency.select("div[class=b_focusTextLarge]");
             }

             detailsMap.put("Time",currentTime.text());
             detailsMap.put("TimeText",timeText.text());
             detailsMap.put("Date",currentDate.text());
             detailsMap.put("Capital",capital.text());
             detailsMap.put("Currency",currency.text());
             detailsMap.put("Population",population.text());

             System.out.println(detailsMap.get("TimeText"));
             System.out.println(detailsMap.get("Time"));
             System.out.println(detailsMap.get("Date"));
             System.out.println(detailsMap.get("Capital"));
             System.out.println(detailsMap.get("Currency"));
             System.out.println(detailsMap.get("Population"));



         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }

        return detailsMap;
    }
}
