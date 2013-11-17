package com.dijas.model.trader;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class PhoneNumber implements PortableObject   {
	
	private String number;

	public PhoneNumber() {
		super();
	}


	public PhoneNumber(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	

	@Override
	public void readExternal(PofReader reader) throws IOException {
		setNumber(reader.readString(0));
		
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getNumber());
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		PhoneNumber other = (PhoneNumber) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PhoneNumber [number=" + number + "]";
	}

}
