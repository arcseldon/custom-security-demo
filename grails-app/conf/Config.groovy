import org.apache.log4j.RollingFileAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = com.become.bid // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml'],
	apiv1:         'application/vnd.com.become.bid.api+json;v=1.0',
	apiv2:         'application/vnd.com.become.bid.api+json;v=2.0'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.excludes = ['/WEB-INF/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"
grails.mime.use.accept.header = true

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

grails.validateable.packages = ['com.become.bid']

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}

grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
		grails.plugin.springsecurity.debug.useFilter = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {

    appenders {
		
		console name: "stdout", layout: pattern(conversionPattern: "%c{2} %m%n")

        environments {
			production {
            }
        }

    }
	
	root {
		error 'stdout'
	}
	
	info 'com.become.bid',
		  'grails.plugin.springsecurity',
		  'grails.plugin.springsecurity.web.filter.DebugFilter',
		  'org.codehaus.groovy.grails.orm.hibernate',
		  'org.springframework',
		  'org.hibernate'
	

    info  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'net.sf.ehcache.hibernate'
}

grails.mail.default.from="noreply@become.com"
grails {
	mail {
	  host = "localhost"
	  props = ["mail.smtp.auth":"false"]
	}
 }

// Spring Security Core plugin
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.become.bid.User'
grails.plugin.springsecurity.authority.className = 'com.become.bid.Role'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              		['permitAll'],
	'/index':                         		['permitAll'],
	'/index.gsp':                     		['permitAll'],
	'/**/js/**':                      		['permitAll'],
	'/**/css/**':                     		['permitAll'],
	'/**/images/**':                  		['permitAll'],
	'/**/favicon.ico':                		['permitAll'],
	'/**/merchants/**':						['permitAll']
]
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.basic.realmName = "Become Bid Query"
grails.plugin.springsecurity.filterChain.chainMap = [
	'/api/**': 'JOINED_FILTERS,-exceptionTranslationFilter',
	'/**': 'JOINED_FILTERS,-basicAuthenticationFilter,-basicExceptionTranslationFilter'
 ]
