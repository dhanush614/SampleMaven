import java.io.IOException;
import java.util.Iterator;

import javax.security.auth.Subject;

import com.filenet.api.collection.ChoiceListSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

public class TestConn {
	private static Connection conn = null;

	public static Connection getCEConn(String ceURI) {
		try {

			String userName = "dadmin";
			String password = "dadmin";
			if (conn == null) {
				conn = Factory.Connection.getConnection(ceURI);
				Subject subject = UserContext.createSubject(conn, userName, password, null);
				UserContext uc = UserContext.get();
				uc.pushSubject(subject);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("CE Connection" + conn);
		return conn;
	}

	public static void getChoiceListValues() throws IOException {
		String ceURI = null;
		try {
			ceURI = "http://ibmbaw:9080/wsi/FNCEWS40MTOM/";
			conn = getCEConn(ceURI);
			if(conn!=null){
				System.out.println(conn.getURI());
			}
			Domain domain = Factory.Domain.fetchInstance(conn, null, null);
			ObjectStore objStore = Factory.ObjectStore.fetchInstance(domain, "tos", null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		getChoiceListValues();
	}
}