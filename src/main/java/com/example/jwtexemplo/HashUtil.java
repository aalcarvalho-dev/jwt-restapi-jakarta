package com.example.jwtexemplo;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    public static String hashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senha, String hashArmazenado) {
        return BCrypt.checkpw(senha, hashArmazenado);
    }

}
