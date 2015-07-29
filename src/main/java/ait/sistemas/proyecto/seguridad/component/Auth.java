package ait.sistemas.proyecto.seguridad.component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.google.gwt.thirdparty.guava.common.base.Charsets;
import com.google.gwt.thirdparty.guava.common.hash.Hashing;

public class Auth {

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String hash(String password) {
		return Hashing.sha1().hashString(password, Charsets.UTF_8).toString();
	}
	
	public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	    crypt.reset();	
	    crypt.update(password.getBytes("UTF-8"));
	    return new BigInteger(1, crypt.digest()).toString(16);
	}

	public static SessionModel login(String usuario, String password) throws SQLException {
		password = Auth.hash(password);
		final UsuarioImpl usuarioimpl = new UsuarioImpl();
		return usuarioimpl.login(usuario, password);
	}
	public static  SessionModel getDefaultUser(){
		final UsuarioImpl usuario = new UsuarioImpl();
		return usuario.login("ANONIMO", "0000");
	}
}
