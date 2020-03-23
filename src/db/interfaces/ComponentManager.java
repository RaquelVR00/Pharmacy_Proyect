package db.interfaces;
import java.util.List;

import db.pojos.Component;

public interface ComponentManager {
	public void add(Component component);
	public List<Component> searchByName(String name);
	public List<Component> searchBySupplier(String supplier);
}