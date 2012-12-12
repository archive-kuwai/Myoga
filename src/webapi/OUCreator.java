package webapi;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.InitialDirContext;

/**
 * 組織階層を作成します。
 */
public class OUCreator {
	/**
	 * 組織階層を作成します。
	 * 
	 * @param args
	 * @throws NamingException
	 *             LDAPアクセスに失敗した場合
	 */

	public static void main(String args[]) throws NamingException {
		// ★★ LDAP接続環境を設定します Start
		// ここで指定する情報は外部ファイル化するのが良いでしょう
		Hashtable env = new Hashtable();

		// Contextファクトリを設定
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");

		// 接続先URL(ホスト，ポート番号)を設定
		env.put(Context.PROVIDER_URL, "ldap//:mail.agricom.co.jp:389");

		// 接続ユーザIDを設定します

		// ※このユーザはオブジェクト作成権限を持つ必要があります

		env.put(Context.SECURITY_PRINCIPAL,

				"CN=jndi_connector,OU=Users,DC=emxas,DC=co,DC=jp");

		// 接続ユーザのパスワードを設定します。

		env.put(Context.SECURITY_CREDENTIALS, "password");

		// ★★ LDAP接続環境を設定します End



		// 設定した環境を元にコンテキストを生成します

		InitialDirContext context = new InitialDirContext(env);



		// 属性集合

		Attributes attrs = new BasicAttributes();

		// ou属性

		Attribute attrOu = new BasicAttribute("ou");

		attrOu.add(0, "ou1");

		attrs.put(attrOu);

		// objectClass(必須)

		Attribute attrObjClass = new BasicAttribute("objectClass");

		attrObjClass.add(0, "top");

		attrObjClass.add(1, "organizationalUnit");

		attrs.put(attrObjClass);



		// 組織階層を作成

		context.createSubcontext(

				"OU=ou1,OU=Users,DC=emxas,DC=co,DC=jp", attrs);



	}



}




