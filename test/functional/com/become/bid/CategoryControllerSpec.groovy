package com.become.bid

import static org.hamcrest.CoreMatchers.anything
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import grails.plugins.rest.client.RestBuilder

import org.codehaus.groovy.grails.web.json.JSONObject

import spock.lang.Specification

/**
 *
 *  Controller: category
 |   GET    | /api/merchants/${merchantId}/categories                   | Action: index            |
 |   POST   | /api/merchants/${merchantId}/categories                   | Action: save             |
 |   GET    | /api/merchants/${merchantId}/categories/${id}             | Action: show             |
 |   PUT    | /api/merchants/${merchantId}/categories/${id}             | Action: update           |
 |  DELETE  | /api/merchants/${merchantId}/categories/${id}             | Action: delete           |
 * 
 * @author arcseldon
 *
 */

class CategoryControllerSpec extends Specification {
	
	
	 // run before every feature method
	def setup() {

	}         

    // run after every feature method
	def cleanup() {
		
	}     

	// run before the first feature method
	def setupSpec() {
		
	}    

    // run after the last feature method
	def cleanupSpec() {
		
	}  
	
	
	def "Test GET all merchants"() {
		given:"A rest client instance"
				def rest = new grails.plugins.rest.client.RestBuilder()

		when:"A get request is issued for a response that returns JSON"
			def resp = rest.get("http://localhost:8080/dfm-core/v1/merchants") {
				accept "application/json"
			}

		then:"The response is a gpath result"
			resp != null
			resp.json instanceof List<JSONObject>
			resp.json[0].name == 'Dinos'
			resp.json[0].city == 'Tokyo'
	}

    def "Test GET individual merchant by id"() {
        given: "A rest client instance"
			def rest = new grails.plugins.rest.client.RestBuilder()

        when: "A get request is issued for a single merchant by id with JSON response type"
            def resp = rest.get("http://localhost:8080/dfm-core/v1/merchants/1") {
                accept "application/json"
            }

        then: "The response is a gpath result"
            resp != null
            resp.json instanceof JSONObject
            resp.json.name == 'Dinos'
            resp.json.city == 'Tokyo'
    }

	

    def "Test that obtaining a 404 response doesn't throw an exception but instead returns the response object for inspection"() {
        given:"A rest client instance"
            def rest = new RestBuilder()

        when:"A get request is issued to a URL that returns a 404"
            def resp = rest.get("http://localhost:8080/dfm-core/v1/nonsense") {
                accept "application/json"
            }

        then:"Check the status"
            resp.status == 404
            resp.text instanceof String
    }

}
