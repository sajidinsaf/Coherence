package com.dijas.model.trader;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Trader implements PortableObject {

	public final static long MILLS_IN_YEAR = 1000L * 60L * 60L * 24 * 365;
	
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private Address homeAddress;
	private Address workAddress;
	private Map<String, PhoneNumber> phoneNumbers;
	private Date dateOfBirth;
	
	public Trader() {
		
	}

	public Trader(int id, String firstName, String lastName, String gender, Address homeAddress, Address workAddress, Map<String, PhoneNumber> phoneNumbers, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.phoneNumbers = phoneNumbers;
		this.dateOfBirth = dateOfBirth;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address address) {
		this.homeAddress = address;
	}
	

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Map<String, PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<String, PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	public PhoneNumber getHomePhoneNumber() {
		return phoneNumbers.get("home");
	}

	public PhoneNumber getWorkPhoneNumber() {
		return phoneNumbers.get("work");
	}
	
	Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getAge() {
		return (int) ((System.currentTimeMillis() - getDateOfBirth().getTime())/MILLS_IN_YEAR);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(PofReader reader) throws IOException {
		setId(reader.readInt(0));
		setFirstName(reader.readString(1));
		setLastName(reader.readString(2));
		setGender(reader.readString(3));
		setHomeAddress((Address)reader.readObject(4));
		setWorkAddress((Address)reader.readObject(5));
		setPhoneNumbers((Map<String, PhoneNumber>)reader.readMap(6, null));
		setDateOfBirth(new Date(reader.readLong(7)));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeInt(0, getId());
		writer.writeString(1, getFirstName());
		writer.writeString(2, getLastName());
		writer.writeString(3, getGender());
		writer.writeObject(4, getHomeAddress());
		writer.writeObject(5, getWorkAddress());
		writer.writeMap(6, getPhoneNumbers());
		writer.writeLong(7, getDateOfBirth().getTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		result = prime * result
				+ ((workAddress == null) ? 0 : workAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trader other = (Trader) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumbers == null) {
			if (other.phoneNumbers != null)
				return false;
		} else if (!phoneNumbers.equals(other.phoneNumbers))
			return false;
		if (workAddress == null) {
			if (other.workAddress != null)
				return false;
		} else if (!workAddress.equals(other.workAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", homeAddress="
				+ homeAddress + ", workAddress=" + workAddress
				+ ", phoneNumbers=" + phoneNumbers + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

}
