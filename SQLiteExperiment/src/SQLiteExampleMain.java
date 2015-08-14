import java.io.File;

import com.almworks.sqlite4java.*;

public class SQLiteExampleMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		SQLiteConnection db = new SQLiteConnection(new File("databases/test.db"));
	    db.open(true);
//	    SQLiteStatement st = db.prepare("SELECT order_id FROM orders WHERE quantity >= ?");
//	      st.bind(1, "TEST");
//	      while (st.step()) {
//			orders.add(st.columnLong(0));
//	      }
//	    st.dispose();
	    db.dispose();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
