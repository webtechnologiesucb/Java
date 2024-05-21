package com.programacion.models;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class Actor {
	public long actorId;
	public String firstName;
	public String lastName;
	public Date lastUpdate;
	public Collection<FilmActor> filmActor;

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long newActorId) {
		actorId = newActorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Actor o1 = (Actor) o;
		return actorId == o1.actorId && Objects.equals(firstName, o1.firstName)
				&& Objects.equals(lastName, o1.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId, firstName, lastName);
	}

	@Override
	public String toString() {
		return "Actor{" + "actorId=" + actorId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", lastUpdate=" + lastUpdate + '}';
	}
}