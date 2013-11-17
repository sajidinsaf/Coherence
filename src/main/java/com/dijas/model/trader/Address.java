package com.dijas.model.trader;

import java.io.IOException;

import com.tangosol.io.pof.PortableObject;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;

public class Address implements PortableObject {


	private String streetAddress;
	private String postCode;
	private String country;
	
	public Address() {
		
	}
	
	public Address(String streetAddress, String postCode, String country) {
		super();
		this.streetAddress = streetAddress;
		this.postCode = postCode;
		this.country = country;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {
		setStreetAddress(reader.readString(0));
		setPostCode(reader.readString(1));
		setCountry(reader.readString(2));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getStreetAddress());
        writer.writeString(1, getPostCode());
        writer.writeString(2, getCountry());	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
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
		Address other = (Address) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", postCode="
				+ postCode + ", country=" + country + "]";
	}
	
	
}
