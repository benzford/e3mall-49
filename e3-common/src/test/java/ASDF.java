import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author hasee
 *
 */
public class ASDF {
	@Test
	public void testName() throws Exception {
		String domainName = null;
		String serverName="http://sso.e3mall.com/page/token";
		
		if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf("\\:") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
	}
}
