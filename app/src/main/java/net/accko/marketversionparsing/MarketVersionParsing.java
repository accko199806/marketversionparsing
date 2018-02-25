package net.accko.marketversionparsing;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.ExecutionException;

public class MarketVersionParsing {

    private static String packageName;

    public static String getPackageName(String getPackageName) {
        packageName = getPackageName;
        return packageName;
    }

    public static class getMarketVersionTask extends AsyncTask<String, String, String> {
        String getVersion;
        String PlayStoreLink = "https://play.google.com/store/apps/details?id=" + packageName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect(PlayStoreLink).get();
                getVersion = doc.getElementsByAttributeValue("itemprop", "softwareVersion").text();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getVersion;
        }
    }

    public static String getMarketVersion() {
        String SavedMarketVersionStr = null;
        try {
            SavedMarketVersionStr = new getMarketVersionTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return SavedMarketVersionStr;
    }
}