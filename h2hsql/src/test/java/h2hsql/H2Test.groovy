package h2hsql;

import groovy.sql.Sql

import org.h2.tools.Server
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class H2Test {

	static Server server;
	
	static def sql;

	@BeforeClass
	public  static void beforeClass() {
		server = Server.createTcpServer((String[])["-tcpPort",
			"8080",
			"-tcpAllowOthers"]).start();
		
		sql = Sql.newInstance( 'jdbc:h2:mem:test;MODE=PostgreSQL', 'sa', '', 'org.postgresql.Driver' )
	}

	@AfterClass
	public  static void afterClass() {
		if (server != null && server.isRunning(false)) {
			server.stop();
		}
	}

	@Test
	void test() {
		sql.execute("create table test (field text);")
		sql.execute("insert into test values ('a')")
		sql.execute("insert into test values ('b')")
		def rows= sql.rows("select * from test")
		
		println rows[0].field
		
		
	}
}
