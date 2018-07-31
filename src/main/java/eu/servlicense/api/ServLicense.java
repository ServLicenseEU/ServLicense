package eu.servlicense.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

public class ServLicense {

    private String token;
    private Integer productId;

    public ServLicense(String token, int productId) {
        this.token = token;
        this.productId = productId;
    }

    public boolean check() {
        try {
            final URL url = new URL("https://servlicense.eu/api/v2/json?token=" + this.token + "&productid=" + this.productId);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringbuilder = new StringBuilder();

            while (bufferedReader.ready()) {
                stringbuilder.append(bufferedReader.readLine());
            }

            bufferedReader.close();

            JSONObject json = new org.json.JSONObject(stringbuilder.toString());

            String response = json.getString("success");

            if(response.equalsIgnoreCase("false")) {
                return false;
            }

            Integer product = json.getJSONObject("response").getJSONObject("product").getInt("id");

            Integer ns1 = Integer.parseInt(product.toString());
            Integer ns2 = Integer.parseInt(productId.toString());

            System.err.println(ns1 + ":" + ns2);

            if(ns1 != ns2) {
                return false;
            }


            System.err.println(response + "");

            switch (response) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
