class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
		"404"(controller: "error", action: "notFound")
		"500"(controller: "error", action: "serverError")
		
		"/api/merchants"(resources:"merchant", includes:['index', 'show']) {
			"/categories"(resources:"category", includes:['show'])
		}

	}

}
