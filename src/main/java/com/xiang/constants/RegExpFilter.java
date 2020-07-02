package com.xiang.constants;

public class RegExpFilter {
	private static final String IP_REGEX = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
	private static final String DOMAIN_REGEX = "^[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-.,@?^=%&:\\/~+#]*[\\w\\-@?^=%&\\/~+#])?$";
	public static boolean validIp(String ip) {
		if(null == ip) {
			return false;
		}
		return ip.matches(IP_REGEX) || ip.matches(DOMAIN_REGEX);
	}
}
