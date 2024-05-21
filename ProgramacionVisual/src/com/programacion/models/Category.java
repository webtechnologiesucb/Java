package com.programacion.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class Category {
	public long categoryId;
	public String name;
	public LocalDateTime lastUpdate;
	public Collection<FilmCategory> filmCategory;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long newCategoryId) {
		categoryId = newCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime newLastUpdate) {
		lastUpdate = newLastUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Category o1 = (Category) o;
		return categoryId == o1.categoryId && Objects.equals(name, o1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, name);
	}

	@Override
	public String toString() {
		return "Category{" + "categoryId=" + categoryId + ", name='" + name + '\'' + ", lastUpdate=" + lastUpdate + '}';
	}
}