package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region {
	
	@SerializedName("region_id")
	private Integer region_id;
	
	@SerializedName("region_name")
	@Expose
	private String region_name;
	
	public Integer getRegionId() {
		return region_id;
	}
	public void setRegionId(Integer region_id) {
		this.region_id = region_id;
	}
	public String getRegionName() {
		return region_name;
	}
	public void setRegionName(String region_name) {
		this.region_name = region_name;
	}
}
