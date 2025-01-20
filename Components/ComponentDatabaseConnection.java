package Pc_database.Components;
import Pc_database.DBconnection.DBConnection;
import Pc_database.Models.Gettersetter;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ComponentDatabaseConnection {
    private ArrayList<Gettersetter> gettersetters;
    static DBConnection sl = new DBConnection();

    public ComponentDatabaseConnection() throws Exception {
        gettersetters = new ArrayList<>();
        loadComponents();
    }

    public static Scanner ps = new Scanner(System.in);

    private void loadComponents() throws Exception {
        Statement statement = sl.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM components");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            Gettersetter gettersetter = new Gettersetter(id, name, type, brand, price);
            gettersetters.add(gettersetter);
        }
        statement.close();
        sl.getConnection().close();
    }

    public void addComponent(Gettersetter gettersetter) {
        gettersetters.add(gettersetter);
    }

    public Gettersetter searchComponentById(int id) {
        for (Gettersetter gettersetter : gettersetters) {
            if (gettersetter.getId() == id) {
                return gettersetter;
            }
        }
        return null;
    }

    public Gettersetter searchComponentByName(String name) {
        for (Gettersetter gettersetter : gettersetters) {
            if (gettersetter.getName().equalsIgnoreCase(name)) {
                System.out.println(gettersetter);
                return gettersetter;
            }
        }
        return null;
    }

    public void searchComponentByType(String type) {
        ArrayList<Gettersetter> results = new ArrayList<>();
        for (Gettersetter gettersetter : gettersetters) {
            if (gettersetter.getType().equalsIgnoreCase(type)) {
                results.add(gettersetter);
            }
        }
        for (Gettersetter i : results) {
            System.out.println(i);
        }
    }

    public ArrayList<Gettersetter> searchComponentByBrand(String brand) {
        ArrayList<Gettersetter> results = new ArrayList<>();
        for (Gettersetter gettersetter : gettersetters) {
            if (gettersetter.getBrand().equalsIgnoreCase(brand)) {
                results.add(gettersetter);
            }
        }
        return results;
    }

    public ArrayList<Gettersetter> getAllComponents() {
        return gettersetters;
    }

    public void updateComponent(Gettersetter gettersetterToUpdate) {
        for (int i = 0; i < gettersetters.size(); i++) {
            Gettersetter gettersetter = gettersetters.get(i);
            if (gettersetter.getId() == gettersetterToUpdate.getId()) {
                gettersetters.set(i, gettersetterToUpdate);
                break;
            }
        }
    }

    public void removeComponent(Gettersetter gettersetterToRemove) throws Exception {
        Statement statement = sl.getConnection().createStatement();
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM components WHERE id=" + gettersetterToRemove.getId());
        statement.close();
        sl.getConnection().close();
    }
    public void removeUser(String username) throws Exception {
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate("DELETE FROM username WHERE name = '" + username + "' ");
        statement.close();
        sl.getConnection().close();
    }

    public boolean authenticate(String username, String password, String type,String id) throws Exception {
        PreparedStatement statement = sl.getConnection().prepareStatement(" SELECT * FROM username WHERE name=? AND password= ? AND type = ? AND id = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, type);
        statement.setString(4, id);
        ResultSet resultSet = statement.executeQuery();
        boolean success = resultSet.next();
        statement.close();
        sl.getConnection().close();
        return success;
    }

    public ArrayList<String> allUser() throws Exception {
        ArrayList<String> t1 = new ArrayList<>();
        PreparedStatement statement = sl.getConnection().prepareStatement("SELECT * FROM USERNAME ORDER BY TYPE");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("Name");
            String password = resultSet.getString("Password");
            String Type = resultSet.getString("Type");
            String id = resultSet.getString("Id");
            t1.add("Username: " + username + "  |  Password: " + password + "  |  Type: " + Type + "  |  Id: " + id);
        }
        return t1;
    }

        Connection conn = sl.getConnection();
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO Order_details (Name, Items_ordered, Address,Phone_no,Total_price,Date,id,Delivery_date) VALUES (?, ?, ?, ?, ?,CURRENT_DATE,?,Date_add(CURRENT_DATE,INTERVAL 7 DAY))");
            stmt.setString(1, orderName);
            stmt.setString(2, itemsOrdered);
            stmt.setString(3, orderAddress);
            stmt.setString(4, orderPhone);
            stmt.setDouble(5, totalPrice);
            stmt.setString(6, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void removeSelectedComponents(ArrayList<Gettersetter> selectedGettersetters) throws Exception {
        Connection conn = null;
        conn = sl.getConnection();
        conn = DBConnection.getConnection();
        for (Gettersetter gettersetter : selectedGettersetters) {
            String query = "DELETE FROM components WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, gettersetter.getId());
            ps.executeUpdate();
        }
    }
    public ArrayList<String> showOrder()throws Exception{
        ArrayList<String> t1 = new ArrayList<>();
        PreparedStatement statement = sl.getConnection().prepareStatement("SELECT * FROM Order_details ORDER BY CURRENT_DATE");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("Name");
            String items = resultSet.getString("Items_ordered");
            String address = resultSet.getString("Address");
            String Phone_no = resultSet.getString("Phone_no");
            Double totalprice = resultSet.getDouble("Total_price");
            Date date = resultSet.getDate("Date");
            String id = resultSet.getString("ID");
            String dev_date = resultSet.getString("Delivery_date");
            t1.add("Username: " + username + " | Items_order: " + items + " | Address: " + address + " | Phone_no: " + Phone_no + " | Total price: "+totalprice+" | Date: "+date+ " | Id: "+id+"Delivery date: "+dev_date);
        }
        return t1;
    }
    public void addNewUser(String name,String password,String type,String id) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO username(Name,password, type,id) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, type);
            stmt.setString(4, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
