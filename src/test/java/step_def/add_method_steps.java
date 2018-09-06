package step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class add_method_steps {
	
	public int i_have_a_method(String a, String b) {
		int first = Integer.parseInt(a);
		int second = Integer.parseInt(b);
		int sum = first + second;
		return sum;
	}

	@When("I \"([^\"]*)\" and \"([^\"]*)\"")
	public void i_and(String string, String string2) {
		try {
		i_have_a_method(string, string2);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Then("I verify the \"([^\"]*)\" in step")
	public void i_verify_the_in_step(String string) {
	   if(string == "NumberFormatException") {
		   System.out.println(string);
	   }else {
		   System.out.println(string);
	   }
	}
}
