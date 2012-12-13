package webapi;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

// http://eureka.ykyuen.info/2010/07/29/java-connect-windows-active-directory-through-ldap-1/
// http://www.emxas.co.jp/itblog/item_309.html
public class LdapContextCreation {

	public static void main(String[] args) {
		try {
			// Create a LDAP Context
			Hashtable env = new Hashtable();  
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");  
			//env.put(Context.SECURITY_AUTHENTICATION, "simple");  
			//env.put(Context.SECURITY_PRINCIPAL, "ohta_n@agricom.co.jp");  
			//env.put(Context.SECURITY_CREDENTIALS, "lovemedo");  
			
			//http://www.emailman.com/ldap/public.html  -  University of California Davis
			//env.put(Context.PROVIDER_URL, "ldap://ldap.ucdavis.edu:389");
			
			//env.put(Context.PROVIDER_URL, "ldap://mail.agricom.co.jp:636");

			//http://blog.stuartlewis.com/2008/07/07/test-ldap-service/
			env.put(Context.PROVIDER_URL, "ldap://ldap.testathon.net:389");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");  
			env.put(Context.SECURITY_PRINCIPAL, "john@ldap.testathon.net");  
			env.put(Context.SECURITY_CREDENTIALS, "john");  

			LdapContext ctx = new InitialLdapContext(env, null);  
			System.out.println("Connection Successful.");

			// Print all attributes of the name in namespace
			Attributes attributes = null;
			attributes = ctx.getAttributes(ctx.getNameInNamespace());
			for (NamingEnumeration ae = attributes.getAll(); ae.hasMoreElements();) {
				Attribute attr = (Attribute)ae.next();
				String attrId = attr.getID();
				for (NamingEnumeration vals = attr.getAll(); vals.hasMore();) {
					String thing = vals.next().toString();
					System.out.println(attrId + ": " + thing);
				}
			}
			ctx.close();
		} catch (NamingException e) {
			System.out.println("LDAP Connection: FAILED");  
			e.printStackTrace();  
		}
	}
}
