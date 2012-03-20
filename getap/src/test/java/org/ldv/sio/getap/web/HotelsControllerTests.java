package org.ldv.sio.getap.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ldv.sio.getap.app.exemple.spring.Hotel;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchCriteria;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchService;
import org.ldv.sio.getap.app.exemple.spring.impl.Stub2HotelSearchService;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * Tests for the HotelController
 */
public class HotelsControllerTests {

	private HotelsController controller;

	/**
	 * Initialize the HotelsController with the Stub2HotelSearchService.
	 */
	@Before
	public void initialize() {
		HotelSearchService searchService;

		searchService = new Stub2HotelSearchService();
		controller = new HotelsController();

		controller.setSearchService(searchService);
	}

	/**
	 * Test for /hotels/index action. There is no logic in the controller to test.
	 */
	@Test
	public void testIndex() {
	}

	/**
	 * Test for /hotels/search action. A successful search.
	 */
	@Test
	public void testSearch() {
		HotelSearchCriteria searchCriteria = new HotelSearchCriteria("Westin");
		Model model = new ExtendedModelMap();
		BindingResult bindResult = new BeanPropertyBindingResult(searchCriteria,
		    "HotelSearchCriteriaBis");
		String selectedView = controller.search(searchCriteria, bindResult, model);

		Assert.assertTrue("The search results page should be returned",
		    "hotels/search".equals(selectedView));
		Assert
		    .assertFalse("The bind should have no errors", bindResult.hasErrors());
		Assert.assertTrue("The hotels list should be in the model",
		    model.containsAttribute("hotels"));
	}

	/**
	 * Test for /hotels/search action. A null search results in a search error.
	 */
	@Test
	public void testNullSearch() {
		HotelSearchCriteria searchCriteria = new HotelSearchCriteria(null);
		Model model = new ExtendedModelMap();
		BindingResult bindResult = new BeanPropertyBindingResult(searchCriteria,
		    "HotelSearchCriteriaBis");
		String selectedView = controller.search(searchCriteria, bindResult, model);

		Assert.assertTrue("The search errors page should be returned",
		    "hotels/index".equals(selectedView));
		Assert.assertTrue("The bind should contain errors", bindResult.hasErrors());
		Assert.assertFalse("The model should not contain the hotels list",
		    model.containsAttribute("hotels"));
	}

	/**
	 * Test for /hotels/search action. An empty search results in a search error.
	 */
	@Test
	public void testEmptySearch() {
		HotelSearchCriteria searchCriteria = new HotelSearchCriteria("");
		Model model = new ExtendedModelMap();
		BindingResult bindResult = new BeanPropertyBindingResult(searchCriteria,
		    "HotelSearchCriteriaBis");
		String selectedView = controller.search(searchCriteria, bindResult, model);

		Assert.assertTrue("The search errors page should be returned",
		    "hotels/index".equals(selectedView));
		Assert.assertTrue("The bind should contain errors", bindResult.hasErrors());
		Assert.assertFalse("The model should not contain the hotels list",
		    model.containsAttribute("hotels"));
	}

	/**
	 * Test for /hotels/details action. Confirm the expected Hotel is returned.
	 */
	@Test
	public void testDetails() {
		Long id = 1L;
		Hotel hotel = controller.details(id);
		Assert.assertTrue("The expected hotel was not found",
		    hotel.getId().equals(id));
	}
}
