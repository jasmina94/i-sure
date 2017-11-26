package com.ftn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.InsurancePolicyDTO;

import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_policy" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InsurancePolicy extends Base {

	@Column(nullable = false)
	private double totalValue;

	@Column(nullable = false)
	private Date dateOfIssue;

	@Column(nullable = false)
	private Date dateBecomeEffective;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Customer> customers = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private InternationalTravelInsurance iti;
	@OneToOne(cascade = CascadeType.ALL)
	private HomeInsurance homeInsurance;
	@OneToOne(cascade = CascadeType.ALL)
	private RoadsideAssistanceInsurance roadsideAssistanceInsurance;

	// private List<Risk> risks = new ArrayList<>();

	public InsurancePolicy(BaseDTO baseDTO) {
		super(baseDTO);
	}

	public void merge(InsurancePolicyDTO insurancePolicyDTO) {
		this.totalValue = insurancePolicyDTO.getTotalValue();
		this.dateOfIssue = insurancePolicyDTO.getDateOfIssue();
		this.dateBecomeEffective = insurancePolicyDTO.getDateBecomeEffective();

		InternationalTravelInsurance itiIntern = new InternationalTravelInsurance(insurancePolicyDTO.getIti());
		itiIntern.merge(insurancePolicyDTO.getIti());

		if (insurancePolicyDTO.getHomeInsurance() != null) {
			HomeInsurance hiIntern = new HomeInsurance(insurancePolicyDTO.getHomeInsurance());
			hiIntern.merge(insurancePolicyDTO.getHomeInsurance());
			this.homeInsurance = hiIntern;
		}

		if (insurancePolicyDTO.getRoadsideAssistanceInsurance() != null) {
			RoadsideAssistanceInsurance raiIntern = new RoadsideAssistanceInsurance(
					insurancePolicyDTO.getRoadsideAssistanceInsurance());
			raiIntern.merge(insurancePolicyDTO.getRoadsideAssistanceInsurance());
			this.roadsideAssistanceInsurance = raiIntern;
		}

		this.iti = itiIntern;
		this.customers.clear();
		for (CustomerDTO customerDTO : insurancePolicyDTO.getCustomers()) {
			this.customers.add(customerDTO.construct());
		}
	}
}
