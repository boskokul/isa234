package ftn.isa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="companys_admins")
public class CompanyAdmin extends BaseUser{
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", nullable = true)
    private Company company;

}
