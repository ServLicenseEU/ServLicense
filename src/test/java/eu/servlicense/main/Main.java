package eu.servlicense.main;

import eu.servlicense.api.ServLicense;

public class Main {

    public static void main(String[] args) {
        ServLicense servLicense = new ServLicense("TOKEN", 0);
        servLicense.check();
    }

}
