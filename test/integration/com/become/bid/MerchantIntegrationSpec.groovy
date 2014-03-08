package com.become.bid

import grails.plugin.spock.IntegrationSpec

/**
 * 
 * @author arcseldon
 *
 */
class MerchantIntegrationSpec extends IntegrationSpec {

	def setup() {
	}

	def cleanup() {
	}

	def "Saving our first merchant to the database"() {

		given: "A brand new merchant"
		def merchantOne = new Merchant(
					parentId: new Long(0),
					aggregator: false,
					agency: false,
					name: "One",
					displayName: "One",
                    billable: true,
					type: 0,
					accountDeleted: false)
				
		when: "the merchant is saved"
		merchantOne.save(flush: true, failOnError: true)

		then: "it saved successfully and can be found in the database"
		merchantOne.errors.errorCount == 0
		merchantOne.id != null
		Merchant.get(merchantOne.id).name == merchantOne.name
	}
	
	def "Updating a saved merchant changes its properties"() {
		given: "An existing merchant"
		def existingMerchant = new Merchant(
					parentId: new Long(0),
					aggregator: false,
					agency: false,
					name: "One",
					displayName: "One",
                    billable: true,
					type: 0,
					accountDeleted: false).save(flush: true, failOnError: true)
				
		when: "A property is changed"
		def foundMerchant = Merchant.get(existingMerchant.id)
		foundMerchant.displayName = "One Premier" 
		foundMerchant.save(failOnError: true) 

		then: "The change is reflected in the database"
		Merchant.get(existingMerchant.id).displayName == 'One Premier' 
	}
	
	def "Deleting an existing merchant removes it from the database"() {
		given: "An existing merchant"
		def existingMerchant = new Merchant(
					parentId: new Long(0),
					aggregator: false,
					agency: false,
					name: "One",
					displayName: "One",
                    billable: true,
					type: 0,
					accountDeleted: false).save(flush: true, failOnError: true)

		when: "The merchant is deleted"
		def foundMerchant = Merchant.get(existingMerchant.id)
		foundMerchant.delete(flush: true)

		then: "The merchant is removed from the database"
		! Merchant.exists(foundMerchant.id)
	}
	
	def "Saving a merchant with invalid properties causes an error"() {
		given: "A merchant which fails several field validations"
		def merchant =  new Merchant(
					parentId: new Long(-1),
					aggregator: false,
					agency: false,
					name: "",
					displayName: null,
                    billable: true,
					type: 2,
					accountDeleted: false)
		when: "The merchant is validated"
		merchant.validate() 
		then:
		merchant.hasErrors()
		"min.notmet" == merchant.errors.getFieldError("parentId").code 
		println ">>>>" + merchant.errors.getFieldError("parentId").rejectedValue
		-1 == merchant.errors.getFieldError("parentId").rejectedValue 
		"nullable" == merchant.errors.getFieldError("name").code 
		"nullable" == merchant.errors.getFieldError("displayName").code 
		!merchant.errors.getFieldError("aggregator") 
		!merchant.errors.getFieldError("agency") 
		!merchant.errors.getFieldError("billable") 
		!merchant.errors.getFieldError("accountDeleted") 
	}
	
	def "Recovering from a failed save by fixing invalid properties"() {
		given: "A merchant that has invalid properties"
		def merchant =  new Merchant(
					parentId: new Long(-1),
					aggregator: false,
					agency: false,
					name: "One",
					displayName: "One User",
                    billable: true,
					type: 2,
					accountDeleted: false)
		assert merchant.save() == null
		assert merchant.hasErrors() 

		when: "We fix the invalid properties"
		merchant.parentId = new Long(1) 
		merchant.type = 2 
		merchant.validate()

		then: "The merchant saves and validates fine"
		!merchant.hasErrors() 
		merchant.save() 
	}
	
}
