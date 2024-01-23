package org.enudelrum;

import java.util.HashMap;
import java.lang.reflect.Field;
import net.runelite.api.HitsplatID;

//Input the hitsplatID and get the hitsplatName.
//EXAMPLE: String hitsplatName = new HitsplatName().nameLookup(hitsplat.getHitsplatType());
//Input int 16 to get string "DAMAGE_ME".

public class HitsplatName {
    Field[] fields = HitsplatID.class.getFields();
    HitsplatID hitsplatID = new HitsplatID();
    HashMap<String, String> hitsplatNames = new HashMap<String, String>();

    public static void main(String[] args) {
    }

    public String nameLookup(int hitsplastID) throws IllegalAccessException {
        for (Field field : fields) {
            hitsplatNames.put(field.get(hitsplatID).toString(), field.getName());
        }
        return hitsplatNames.get(Integer.toString(hitsplastID));
    }

}
