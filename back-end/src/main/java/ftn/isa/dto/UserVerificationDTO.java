package ftn.isa.dto;

import ftn.isa.domain.BaseUser;

public class UserVerificationDTO {
	private Integer id;
	private boolean verified;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public UserVerificationDTO() {}
	public UserVerificationDTO(Integer id, boolean verified) {
		super();
		this.id = id;
		this.verified = verified;
	}
	public UserVerificationDTO(BaseUser u) {
		super();
		this.id = u.getId();
		this.verified = u.isVerified();
	}
	
	
}
