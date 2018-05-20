package com.rank.ccms.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

public class GenerateToken {

	public static final String PROVISION_TOKEN = "provision";
	private static final long EPOCH_SECONDS = 62167219200l;
	private static final String DELIM = "\0";

	public static String calculateExpiry(String expires) throws NumberFormatException {
		long expiresLong;
		long currentUnixTimestamp = System.currentTimeMillis() / 1000;
		expiresLong = Long.parseLong(expires);
		return "" + (EPOCH_SECONDS + currentUnixTimestamp + expiresLong);
	}

	public static String generateProvisionToken(String key, String jid, String expires, String vcard) throws NumberFormatException {
		String payload = PROVISION_TOKEN + DELIM + jid + DELIM + calculateExpiry(expires) + DELIM + vcard;
		return new String(Base64.encodeBase64((payload + DELIM + HmacUtils.hmacSha384Hex(key, payload)).getBytes()));
	}

	public static String getToken() {
		String key = "48861db7fac244929ba09a0f83b5e79f";
		String appID = "eabf52.vidyo.io";
		String userName = "manirulmallick";

		String expiresInSeconds = "999999999999999999";
		String ret = "";
		try {
			ret = generateProvisionToken(key, userName + "@" + appID, expiresInSeconds, null);	

			System.out.println("GENERATED TOKEN :--->>> " + ret);
		} catch (NumberFormatException nfe) {
			System.out.println("Failed to parse expiresInSeconds: " + expiresInSeconds);
		}
		return ret;
	}


}
