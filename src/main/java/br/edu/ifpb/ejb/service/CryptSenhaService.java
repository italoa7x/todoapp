package br.edu.ifpb.ejb.service;

import org.mindrot.jbcrypt.BCrypt;

public abstract class CryptSenhaService {

	public static String criptografar(String senha) {
		String salt = BCrypt.gensalt();

		return BCrypt.hashpw(senha, salt);
	}

	/**
	 * Decodifica string na base 64 (Decoder)
	 */
	public static boolean compararSenha(String senha, String senhaHash) {
		return BCrypt.checkpw(senha, senhaHash);
	}
}
