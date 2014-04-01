package h2hsql

import groovy.sql.Sql

import org.h2.tools.Server
import org.hsqldb.server.WebServer
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class HSqlDBTest {

	private static WebServer server;
	
	static def sql;

	@BeforeClass
	public  static void beforeClass() {
		server = new WebServer();
		
		sql = Sql.newInstance( 'jdbc:hsqldb:mem:test', 'sa', '', 'org.postgresql.Driver' )
	}

	@AfterClass
	public  static void afterClass() {
		if (server != null) {
			server.shutdown();
		}
	}

	@Test
	void test() {

		sql.execute("SET DATABASE SQL SYNTAX PGS TRUE")
		sql.execute("create table test (field text);")
		sql.execute("insert into test values ('a')")
		sql.execute("insert into test values ('b')")
		def rows= sql.rows("select * from test")
		println rows[0].field
		
		
	}
}
