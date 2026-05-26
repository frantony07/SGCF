package Functions.FunctionsByMain;

import org.ONE.model.entity.ENUM.CountryTour;

import java.util.ArrayList;
import java.util.Collections;

public class LoadCountry {
    public static ArrayList<CountryTour> getAllCountr(){
        ArrayList<CountryTour> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,CountryTour.values());
        return arrayList;
    }
}
