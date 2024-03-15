package Pojo;

import java.util.List;

public class MiniCourse {
	
	private List<WebAutomation> webAutomation;
	private List<API> api;
	private List<mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<API> getApi() {
		return api;
	}
	public void setApi(List<API> api) {
		this.api = api;
	}
	public List<Pojo.mobile> getMobile() {
		return mobile;
	}	
	public void setMobile(List<Pojo.mobile> mobile) {
		this.mobile = mobile;
	}


}
