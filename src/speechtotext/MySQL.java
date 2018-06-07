package speechtotext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
    String server, dbname, url, user, password;// データベースの指定
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    private String id;
    
    
    
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "J16010";
        this.dbname = "J16010";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "J16010";
        this.password = "J16010";
        this.id="J16010";
        
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
		
	//値の無い物全て
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `images` WHERE  `age_min` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateImage(String transcript,double confidence) {
		//keywordテーブルへ格納
		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO  `speeches` (`user_id`, `transcript` ,`confidence`) VALUES ( 'J16010'," + transcript + ", "+ confidence +"  );");//6月8日
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
