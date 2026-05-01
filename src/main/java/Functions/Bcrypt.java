package Functions;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

    public static String criarHash(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt(14));
    }

    public static boolean verificarHash(String senhaDigitada, String hashBanco){
        return BCrypt.checkpw(senhaDigitada, hashBanco);
    }

}
