
dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "com.become.bid.util.MySQLUTF8InnoDBDialect"
	username = "grails"
	password = "server"
}
hibernate {
	cache.use_second_level_cache = false
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" 
			url = "jdbc:mysql://localhost:3306/test_merchant?autoreconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8"
			username = "grails"
			password = "server"
			logSql = true
			pooled = true
			properties {
			   maxActive = -1
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   driverClassName = "com.mysql.jdbc.Driver"
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery="SELECT 1"
			}
		
		}
	}
	 test {
		dataSource {
			pooled = true
			jmxExport = true
			driverClassName = "org.h2.Driver"
			username = "sa"
			password = ""
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}
	production {
		dbCreate = "update"
		dataSource {
			url = "jdbc:mysql://localhost:3306/test_merchant?autoreconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8"
			username = "grails"
			password = "server"
			pooled = true
			properties {
			   maxActive = -1
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   driverClassName = "com.mysql.jdbc.Driver"
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery="SELECT 1"
			}
		}
	}
}
