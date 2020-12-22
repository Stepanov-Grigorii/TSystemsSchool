package ru.grandstep.logiweb.filler;

import java.util.List;
import java.util.Random;


public class DriverID {
    public static String getUserId(String fName, String lName, List<String> ids){
        String uniqId = String.valueOf(fName.charAt(0)) + lName.charAt(0);
        uniqId += new Random().nextInt(10);
        uniqId += new Random().nextInt(9) + 1;
        uniqId += (char)(new Random().nextInt(26) + 'A');
        uniqId += (char)(new Random().nextInt(26) + 'A');
        uniqId += (char)(new Random().nextInt(26) + 'A');
        if(!ids.isEmpty() && ids.contains(uniqId)){
            return getUserId(fName, lName, ids);
        }
        return uniqId;
    }
}
