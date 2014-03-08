package com.become.bid

import grails.rest.Resource
import groovy.transform.ToString

/**
 * 
 * @author arcseldon
 *
 */
@ToString
@Resource(formats = ['apiv1', 'apiv2'],  readOnly=true)
class Merchant {
	
	long id
	long parentId
	Boolean aggregator
	Boolean agency
	String name
	String displayName
	Boolean billable
	Integer type
	Boolean accountDeleted

    static constraints = {
		parentId(nullable: false, min: new Long(0))
		aggregator(nullable: false)
		agency(nullable: false)
		name(nullable: false, blank: false)
		displayName(nullable: false, blank: false)
		billable(nullable: false)
		type(nullable:false)
        accountDeleted(nullable: false)
		
    }
	
	static mapping = {
		version false
	}
}
