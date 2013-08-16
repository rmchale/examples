package org.mchaler.controller

import javax.annotation.Resource

import org.mchaler.AWClass;
import org.mchaler.DIClass
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SampleController {

	/**
	 * Resource wired from XML file
	 */
	@Resource
	DIClass fromXml;
	
	/**
	 * Resource autowired from component-scan
	 */
	@Resource
	AWClass fromAW
	
	/**
	 * Returns JSON { message: "xml" }
	 * @return
	 */
	@RequestMapping("/json")
	@ResponseBody
	public Map<String, String> helloWorld() {
		return Collections.singletonMap("message",
				this.fromXml.text());
	}
	
	/**
	 * Returns JSON { message: "wired" }
	 * @return
	 */
	@RequestMapping("/foo")
	@ResponseBody
	public Map<String, String> foo() {
		return Collections.singletonMap("message",
				this.fromAW.text());
	}
	
	/**
	 * Directs to page
	 * @param model
	 * @return
	 */
		@RequestMapping("/page")
		public String page(Map<String, Object> model) {
			model.put("message", "Hello World");
			model.put("title", "Hello Home");
			model.put("date", new Date());
			return "page";
		}
}
