package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.exemple.spring.Hotel;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchCriteria;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/hotels/*")
public class HotelsController {

	@Autowired
	@Qualifier("monService3")
	private HotelSearchService searchService;

	public void setSearchService(HotelSearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Default action, displays the search page.
	 * 
	 * @param searchCriteria
	 *            The criteria to search for
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(HotelSearchCriteria searchCriteria) {

	}

	/**
	 * Validates the searchCriteria then executes the search with the
	 * HotelSearchService
	 * 
	 * @param searchCriteria
	 *            The criteria to search for
	 * @param bindResult
	 *            Holds searchCriteria validation errors
	 * @param model
	 *            Holds the resulting list of hotels
	 * @return Success or error view
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(HotelSearchCriteria searchCriteria,
			BindingResult bindResult, Model model) {
		if (searchCriteria.getQuery() == null
				|| "".equals(searchCriteria.getQuery())) {
			bindResult.rejectValue("query", "required",
					"Please enter valid search criteria");
		}
		if (bindResult.hasErrors()) {
			return "hotels/index";
		} else {
			System.out.println(searchCriteria);
			// model.addAttribute("hotels",
			// searchService.search(searchCriteria));
			return "hotels/search";
		}
	}

	/**
	 * Details for a single hotel
	 * 
	 * @param id
	 *            The id of the Hotel to find
	 * @return The Hotel
	 */
	@RequestMapping(value = "details", method = RequestMethod.GET)
	@ModelAttribute("hotel")
	public Hotel details(@RequestParam("id") Long id) {
		return searchService.getHotel(id);
	}

}
