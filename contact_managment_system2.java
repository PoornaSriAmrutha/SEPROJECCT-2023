import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class MyContact {
    private String name;
    private String phoneNumber;
    private boolean isFriend;

    public MyContact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isFriend = false;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public void updateContact(String newName, String newPhoneNumber) {
        this.name = newName;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Contact details updated:\n" + this);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Friend: " + (isFriend ? "Yes" : "No");
    }
}

class MyFriendManagementSystem {
    private ArrayList<MyContact> contacts;

    public MyFriendManagementSystem() {
        this.contacts = new ArrayList<>();
    }

    public void addMyContact(MyContact contact, boolean addToFriends) {
        contacts.add(contact);
        System.out.println(contact.getName() + " has been added to your contact list.");

        if (addToFriends) {
            contact.setFriend(true);
            System.out.println(contact.getName() + " has been marked as your friend.");
        }
    }

    public void removeMyContact(MyContact contact) {
        if (contacts.remove(contact)) {
            System.out.println(contact.getName() + " has been removed from your contacts.");
        } else {
            System.out.println(contact.getName() + " is not in your contacts.");
        }
    }

    public void displayMyContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Your contact list is currently empty.");
        } else {
            System.out.println("Here are your contacts:");
            for (MyContact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void displayMyFriends() {
        ArrayList<MyContact> friendList = new ArrayList<>();
        for (MyContact contact : contacts) {
            if (contact.isFriend()) {
                friendList.add(contact);
            }
        }

        if (friendList.isEmpty()) {
            System.out.println("You currently have no friends in your contacts.");
        } else {
            System.out.println("Your Friends:");
            for (MyContact friend : friendList) {
                System.out.println(friend);
            }
        }
    }

    public void searchMyContact(String contactName) {
        for (MyContact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(contactName)) {
                System.out.println("Contact found:\n" + contact);
                return;
            }
        }
        System.out.println("No contact found with the name: " + contactName);
    }

    public void updateMyContact(MyContact contact, String newName, String newPhoneNumber) {
        contact.updateContact(newName, newPhoneNumber);
    }

    public void displayNonFriends() {
        ArrayList<MyContact> nonFriendList = new ArrayList<>();
        for (MyContact contact : contacts) {
            if (!contact.isFriend()) {
                nonFriendList.add(contact);
            }
        }

        if (nonFriendList.isEmpty()) {
            System.out.println("You currently have no non-friend contacts.");
        } else {
            System.out.println("Your Non-Friend Contacts:");
            for (MyContact nonFriend : nonFriendList) {
                System.out.println(nonFriend);
            }
        }
    }

    public ArrayList<MyContact> getMyContacts() {
        return contacts;
    }
}

class MyFriendManagementSystemApp {
    public static void main(String[] args) {
        MyFriendManagementSystem myFriendSystem = new MyFriendManagementSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. do you want to add new contact?");
                System.out.println("2. do you want to remove a contact?");
                System.out.println("3. show all the contacts");
                System.out.println("4. show friends list");
                System.out.println("5. Search for a friend number");
                System.out.println("6. Update contact information");
                System.out.println("7. Display non-friend contacts");
                System.out.println("8. Exit");
                System.out.print("Please enter the operation you want to perform: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name of the new contact: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter the phone number for " + name + ": ");
                        String phoneNumber = scanner.nextLine();

                        System.out.print("Would you like to add " + name + " as your friend? (yes/no): ");
                        String addToFriendsChoice = scanner.nextLine().toLowerCase();
                        boolean addToFriends = addToFriendsChoice.equals("yes");

                        MyContact newContact = new MyContact(name, phoneNumber);
                        myFriendSystem.addMyContact(newContact, addToFriends);
                        break;

                    case 2:
                        System.out.print("Enter the name of the contact to remove: ");
                        String contactNameToRemove = scanner.nextLine();

                        for (MyContact contact : myFriendSystem.getMyContacts()) {
                            if (contact.getName().equalsIgnoreCase(contactNameToRemove)) {
                                myFriendSystem.removeMyContact(contact);
                                break;
                            }
                        }
                        break;

                    case 3:
                        myFriendSystem.displayMyContacts();
                        break;

                    case 4:
                        myFriendSystem.displayMyFriends();
                        break;

                    case 5:
                        System.out.print("Enter the name to search for: ");
                        String searchName = scanner.nextLine();
                        myFriendSystem.searchMyContact(searchName);
                        break;

                    case 6:
                        System.out.print("Enter the name of the contact to update: ");
                        String contactNameToUpdate = scanner.nextLine();

                        for (MyContact contact : myFriendSystem.getMyContacts()) {
                            if (contact.getName().equalsIgnoreCase(contactNameToUpdate)) {
                                System.out.print("Enter the new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter the new phone number: ");
                                String newPhoneNumber = scanner.nextLine();

                                myFriendSystem.updateMyContact(contact, newName, newPhoneNumber);
                                break;
                            }
                        }
                        break;

                    case 7:
                        myFriendSystem.displayNonFriends();
                        break;

                    case 8:
                        System.out.println("Exiting the My Friend Management System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = 0;
            } catch (NoSuchElementException e) {
                System.out.println("Input not provided. Exiting.");
                choice = 8;
            }

        } while (choice != 8);

        scanner.close();
    }
}
