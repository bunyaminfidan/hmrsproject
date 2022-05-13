package javaCamp.hmrs.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "employer_users")
public class EmployerUser extends User {

	@Column(name = "user_id")
	private int userId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "website")
	private String website;

	@Column(name="phone_number")
	private String phoneNumber;

}
