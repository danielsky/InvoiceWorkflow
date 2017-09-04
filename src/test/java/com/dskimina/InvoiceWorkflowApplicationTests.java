package com.dskimina;

import com.dskimina.logic.BusinessLogic;
import org.junit.Test;


public class InvoiceWorkflowApplicationTests {

	@Test
	public void contextLoads() {
		BusinessLogic logic = new BusinessLogic();
		for(String location : logic.getLocations()){
			System.out.println(location);
		}
	}

}
