package test;

import json.JsonAttributeExtractor;
import network.HttpManager;
import org.json.JSONObject;

public class Test {
    public static void main(String[] args) {
        try {
            String data = HttpManager.getResponse("https://dummyjson.com/products/2").body();
            JSONObject json = new JSONObject(data);

            JsonAttributeExtractor a = new JsonAttributeExtractor(json);
            a.extract();
            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
