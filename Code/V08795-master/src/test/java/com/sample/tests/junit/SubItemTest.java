package com.sample.tests.junit;

import org.junit.Assert;
import org.junit.Test;

import com.sample.tests.pages.DestinationLookupPage;

public class SubItemTest extends TestCommon {

	public SubItemTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testSubItemOnLocationSearch() throws Exception {
		String destination = "London";
		DestinationLookupPage lookup = searchPage.editDestination.click(DestinationLookupPage.class);
		lookup.editDestinationInput.setText(destination);
		Assert.assertTrue("Result list is still empty", lookup.tableDestinationResults.isNotEmpty());
		int count = lookup.tableDestinationResults.getItemsCount();
		for (int i = 0; i < count; i++) {
			String title = lookup.tableDestinationResults.getSubItem("Title", i).getText();
			Assert.assertTrue(String.format("Item #%d doesn't have %s text", i + 1, destination)
					, title.contains(destination));
		}
	}
}
