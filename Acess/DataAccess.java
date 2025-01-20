package pc_database.access;

import pc_database.components.Gettersetter;
import pc_database.components.ComponentDatabaseConnection;

import java.util.ArrayList;

public class DataAccess {
    private final ComponentDatabaseConnection database;
    private final ArrayList<Gettersetter> selectedGettersetters;
    private double totalPrice;

    public DataAccess(ComponentDatabaseConnection database) {
        this.database = database;
        selectedGettersetters = new ArrayList<>();
        totalPrice = 0;
    }

    public void displayComponentMenu() {
        for (Gettersetter gettersetter : database.getAllComponents()) {
            System.out.println(gettersetter);
        }
    }

    public void selectComponent(int componentId) {
        Gettersetter gettersetter = database.searchComponentById(componentId);
        if (gettersetter != null) {
            selectedGettersetters.add(gettersetter);
            totalPrice += gettersetter.getPrice();
        } else {
            System.out.println("Component not found.");
        }
    }

    public boolean validateAdmin(String username, String password, String type, String id) throws Exception {
        return database.authenticate(username, password, type, id);
    }

    public void add(int id, String name, String type, String brand, double price) throws Exception {
        database.addComponent(new Gettersetter(id, name, type, brand, price));
    }

    public void updateComponent(Gettersetter i) {
        database.updateComponent(i);
    }

    public ArrayList<String> displaySelectedComponents() {
        ArrayList<String> t1 = new ArrayList<>();
        if (!selectedGettersetters.isEmpty()) {
            t1.add("+===== Selected Components =====+");
            for (Gettersetter gettersetter : selectedGettersetters) {
                t1.add(String.valueOf(gettersetter));
            }
            t1.add("Total Price: ₹ " + totalPrice);
        } else {
            t1.add("No components were selected!!!");
        }
        return t1;
    }

    public Gettersetter getComponentById(int componentIdToUpdate) {
        return database.searchComponentById(componentIdToUpdate);
    }

    public ArrayList<String> showAllUser() throws Exception {
        return database.allUser();
    }

    public void orderItem(String name, String address, String phone_no, String id) throws Exception {
        StringBuilder itemsOrdered = new StringBuilder();
        for (Gettersetter gettersetter : selectedGettersetters) {
            itemsOrdered.append(gettersetter.getName()).append(" | ").append(gettersetter.getType()).append(", ");
        }
        if (itemsOrdered.length() > 0) {
            itemsOrdered.setLength(itemsOrdered.length() - 2);
        }
        double totalPrice = this.totalPrice;
        database.addOrderedUserDetails(name, itemsOrdered.toString(), address, phone_no, totalPrice, id);
        database.removeSelectedComponents(selectedGettersetters);
        selectedGettersetters.clear();
    }

    public ArrayList<String> orderedItems() throws Exception {
        return database.showOrder();
    }

    public void addUser(String name, String password, String type, String id) throws Exception {
        database.addNewUser(name, password, type, id);
    }

    public boolean place() {
        return !selectedGettersetters.isEmpty();
    }
}
