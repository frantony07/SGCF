package Functions;

import org.ONE.model.entity.ENUM.Language;

import java.util.ArrayList;
import java.util.Collections;

public class loadLanguage {
    public static ArrayList<Language> getLanguage(){
        ArrayList<Language> languages = new ArrayList<>();
        Collections.addAll(languages, Language.values());
        return languages;
    }
}
