package Pc_database.View;
import Pc_database.Components.Gettersetter;
import Pc_database.Components.ComponentDatabaseConnection;
import Pc_database.Acess.DataAccess;
import java.util.*;
public class Main{
    static String username;
    static String password;
    static String type,choice = "",id;
    static Scanner ps = new Scanner(System.in);
    static ComponentDatabaseConnection database;
    static{
        try {
            database = new ComponentDatabaseConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static DataAccess pc = new DataAccess(database);
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println(">>>>>>     PS Tech shop    <<<<<<");
        System.out.println();
        System.out.println();
        System.out.println("+================================+");
        System.out.println("|          Login Page            |");
        System.out.println("+================================+");
        int valid1 = 0;
        while (valid1 != 1) {
            while (true) {
                System.out.println();
                System.out.print("Enter username : ");
                username = ps.next();
                System.out.print("Enter password : ");
                password = ps.next();
                System.out.print("Enter type(Admin/User): ");
                type = ps.next();
                System.out.print("Enter user id : ");
                id = ps.next();
                if (pc.validateAdmin(username, password, type,id)) {
                    valid1 = 1;
                    System.out.println();
                    System.out.println("Login Successful!!!");
                    break;
                } else if (valid1 != 1) {
                    System.out.println();
                    System.out.println("Invalid username or password!!!");
                    System.out.println();
                }
            }
        }
        if (type.equalsIgnoreCase("Admin")){ forAdmin();
        }
        else if(type.equalsIgnoreCase("user")) forUser();
    }
        public static void forAdmin() throws Exception {
            System.out.println();
            System.out.println("+==============================+");
            System.out.println("|          Main menu           |");
            System.out.println("+==============================+");
            System.out.println();
            System.out.println("1.  Display Component Menu: ");
            System.out.println("2.  To add Components: ");
            System.out.println("3.  To see order details: ");
            System.out.println("4.  To search by Components: ");
            System.out.println("5.  To update a Component: ");
            System.out.println("6.  To remove a Component: ");
            System.out.println("7.  To see user options: ");
            System.out.println("8.  To Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = ps.next();
            System.out.println();
            switch (choice){
                case "1":
                    pc.displayComponentMenu();
                    forAdmin();
                case "2":
                    System.out.println();
                    System.out.print("Enter id : ");
                    int id = ps.nextInt();
                    System.out.println();
                    System.out.print("Enter name :");
                    String name = ps.next();
                    System.out.println();
                    ps.nextLine();
                    System.out.print("Enter type :");
                    String type = ps.next();
                    System.out.println();
                    System.out.print("Enter brand :");
                    String brand = ps.next();
                    System.out.println();
                    System.out.print("Enter price :");
                    double price = ps.nextDouble();
                    pc.add(id, name, type, brand, price);
                    System.out.println();
                    System.out.println("Component added successfully");
                    forAdmin();
                    System.out.println();
                    break;
                case "3":
                    ArrayList<String> t1 = pc.orderedItems();
                    for(String i : t1 ){
                        System.out.println(i);
                    }
                    forAdmin();
                    break;
                case "4":
                    System.out.println("Enter 1 to search by name: ");
                    System.out.println("Enter 2 to search by type: ");
                    System.out.println("Enter 3 to search by brand: ");
                    int a = ps.nextInt();
                    search(a);
                    forAdmin();
                    break;
                case "5":
                    System.out.print("Enter the ID of the component you want to update: ");
                    int componentIdToUpdate = ps.nextInt();
                    Gettersetter gettersetterToUpdate = pc.getComponentById(componentIdToUpdate);
                    if (gettersetterToUpdate == null) {
                        System.out.println("Component not found.");
                    } else {
                        System.out.print("Enter new id (or press enter to skip): ");
                        id = ps.nextInt();
                        if (id != 0) {
                            gettersetterToUpdate.setName(String.valueOf(id));
                        }
                        ps.nextLine();
                        System.out.print("Enter new name (or press enter to skip): ");
                        name = ps.nextLine().trim();
                        if (!name.isEmpty()) {
                            gettersetterToUpdate.setName(name);
                        }
                        System.out.print("Enter new type (or press enter to skip): ");
                        type = ps.nextLine().trim();
                        if (!type.isEmpty()) {
                            gettersetterToUpdate.setType(type);
                        }
                        System.out.print("Enter new brand (or press enter to skip): ");
                        brand = ps.nextLine().trim();
                        if (!brand.isEmpty()) {
                            gettersetterToUpdate.setBrand(brand);
                        }
                        System.out.print("Enter new price (or press enter to skip): ");
                        String priceString = ps.nextLine().trim();
                        if (!priceString.isEmpty()) {
                            price = Double.parseDouble(priceString);
                            gettersetterToUpdate.setPrice(price);
                        }
                        pc.updateComponent(gettersetterToUpdate);
                        System.out.println("Component updated successfully.");
                    }
                    forAdmin();
                    break;
                case "6":
                    System.out.print("Enter the ID of the component you want to remove: ");
                    int componentIdToRemove = ps.nextInt();
                    System.out.println();
                    Gettersetter gettersetterToRemove = database.searchComponentById(componentIdToRemove);
                    if (gettersetterToRemove == null) {
                        System.out.println("Component not found.");
                    } else {
                        database.removeComponent(gettersetterToRemove);
                        System.out.println("Component removed successfully.");
                    }
                    forAdmin();
                    break;
                case "7":
                        User();
                        forAdmin();
                        break;
                case "8":
                    System.out.println();
                    System.out.println("Exiting🚫");
                    System.out.println();
                    System.out.println("\n" +
                            "████████╗██╗░░██╗░█████╗░███╗░░██╗██╗░░██╗  ██╗░░░██╗░█████╗░██╗░░░██╗\n" +
                            "╚══██╔══╝██║░░██║██╔══██╗████╗░██║██║░██╔╝  ╚██╗░██╔╝██╔══██╗██║░░░██║\n" +
                            "░░░██║░░░███████║███████║██╔██╗██║█████═╝░  ░╚████╔╝░██║░░██║██║░░░██║\n" +
                            "░░░██║░░░██╔══██║██╔══██║██║╚████║██╔═██╗░  ░░╚██╔╝░░██║░░██║██║░░░██║\n" +
                            "░░░██║░░░██║░░██║██║░░██║██║░╚███║██║░╚██╗  ░░░██║░░░╚█████╔╝╚██████╔╝\n" +
                            "░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝  ░░░╚═╝░░░░╚════╝░░╚═════╝░");
                    System.exit(0);
                default:
                    System.out.println();
                    System.out.println("Invalid choice!");
                    System.out.println();
                    forAdmin();
                    break;
            }
            System.out.println();
        }
        public static void forUser() throws Exception {
                System.out.println();
                System.out.println("+===========================+");
                System.out.println("|       PC Builder Menu     |");
                System.out.println("+===========================+");
                System.out.println();
                System.out.println("1. Display Available Components: ");
                System.out.println("2. Select Component: ");
                System.out.println("3. Display Selected Components: ");
                System.out.println("4. Place order: ");
                System.out.println("5. To Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                choice = ps.next();
                System.out.println();
                switch (choice){
                    case "1":
                        System.out.println();
                        System.out.println("+===========================+");
                        System.out.println("|      Component Menu       |");
                        System.out.println("+===========================+");
                        System.out.println();
                        pc.displayComponentMenu();
                        System.out.println();
                        forUser();
                    case "2":
                        int check = 0;
                        while (check != 1) {
                            pc.displayComponentMenu();
                            System.out.println();
                            System.out.print("Enter the ID of the component you want to select: ");
                            int componentId = ps.nextInt();
                            System.out.print("Enter 1 to exit selection or 2 to continue: ");
                            check = ps.nextInt();
                            pc.selectComponent(componentId);
                        }
                        forUser();
                    case "3":
                        System.out.println();
                        ArrayList<String>t1 = pc.displaySelectedComponents();
                        if(!t1.isEmpty()) {
                            for (String i : t1) {
                                System.out.println(i);
                            }
                        }
                        else{
                            System.out.println("");
                        }
                        forUser();
                    case "4":
                        if(pc.place()==true) {
                            System.out.println();
                            System.out.println("Enter 1 to confirm order: ");
                            System.out.println("Enter 2 to exit main menu: ");
                            System.out.print("Enter your choice: ");
                            String choice = ps.next();
                            if (choice.equalsIgnoreCase("1")) item();
                            else if (choice.equalsIgnoreCase("2")) forUser();
                        }
                        else System.out.println();System.out.println("No components where selected!!!");
                        System.out.println();
                    case "5":
                        System.out.println();
                        System.out.println("Exiting🚫");
                        System.out.println();
//                        System.out.println("♡  Thank you for shopping  ♡");
                        System.out.println("\n" +
                                "████████╗██╗░░██╗░█████╗░███╗░░██╗██╗░░██╗  ██╗░░░██╗░█████╗░██╗░░░██╗\n" +
                                "╚══██╔══╝██║░░██║██╔══██╗████╗░██║██║░██╔╝  ╚██╗░██╔╝██╔══██╗██║░░░██║\n" +
                                "░░░██║░░░███████║███████║██╔██╗██║█████═╝░  ░╚████╔╝░██║░░██║██║░░░██║\n" +
                                "░░░██║░░░██╔══██║██╔══██║██║╚████║██╔═██╗░  ░░╚██╔╝░░██║░░██║██║░░░██║\n" +
                                "░░░██║░░░██║░░██║██║░░██║██║░╚███║██║░╚██╗  ░░░██║░░░╚█████╔╝╚██████╔╝\n" +
                                "░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝  ░░░╚═╝░░░░╚════╝░░╚═════╝░");
                        System.out.println();
                        System.exit(0);
                    default:
                        System.out.println();
                        System.out.println("Invalid choice!");
                        System.out.println();
                        forUser();
                }
                System.out.println();
    }
    public static void search(int check) {
        if (check == 1) {
            System.out.print("Enter the name to search: ");
            String s = ps.next();
            database.searchComponentByName(s);
        } else if (check == 2) {
            System.out.print("Enter the type to search: ");
            String s = ps.next();
            database.searchComponentByType(s);
        } else if (check == 3) {
            System.out.print("Enter the brand to search: ");
            String s = ps.next();
            database.searchComponentByBrand(s);
        }
    }
public static void User() throws Exception {
    System.out.println();
    System.out.println("Enter 1 to see user details: ");
    System.out.println("Enter 2 to add new user: ");
    System.out.println("Enter 3 to remove user: ");
    System.out.println("Enter 4 to go to mainmenu: ");
    System.out.println();
    System.out.print("Enter your choice: ");
    String user = ps.nextInt()+"";
    if(user.equalsIgnoreCase("1")){
        System.out.println();
        System.out.println("================================");
        System.out.println("|          User details        |");
        System.out.println("================================");
        System.out.println();
        ArrayList<String> s = pc.showAllUser();
        for(String i : s){
            System.out.println(i);
        }
        User();
    }
    if (user.equalsIgnoreCase("2")) {
        System.out.println();
        System.out.print("Enter user name: ");String name = ps.next();
        System.out.println();
        System.out.print("Enter user password: ");String password = ps.next();
        System.out.println();
        System.out.print("Enter user type: ");String type = ps.next();
        System.out.println();
        System.out.print("Enter user id: ");String id = ps.next();
        pc.addUser(name,password,type,id);
        System.out.println();
        System.out.println("New user added successfully!!!");
        System.out.println();
    }
    if (user.equalsIgnoreCase("3")) {
        System.out.print("Enter username to remove: ");
        String username = ps.next();
        database.removeUser(username);
        System.out.println("User removed successfully!!!");
    }
    if (user.equalsIgnoreCase("4")) forAdmin();
}
public static void item() throws Exception {
    System.out.println();
    System.out.print("Enter your name: "); String name = ps.next();
    System.out.println();
    System.out.print("Enter your address: ");String address = ps.next();
    System.out.println();
    System.out.print("Enter your phone_no: ");String phone = ps.next();
    System.out.println();
    System.out.print("Enter your user_id: ");String id = ps.next();
    pc.orderItem(name,address,phone,id);
    System.out.println();
    System.out.println("♡  Order placed successfully  ♡");
    forUser();
    }
}