/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lewiskelly
 */
public class FamilyTreeTest {

    // B00387967 FamilyTreeTest Version 1
    public static void main(String[] args) {
        // get ancestor and partner name from user
        String ancestorName = Input.getString("Ancestor Name: ");
        String ancestorPartnerName = Input.getString("Partner Name: ");
        // declare FamilyTree object
        FamilyTree familyTree = new FamilyTree(ancestorName, ancestorPartnerName);

        // menu
        String childName;
        Integer option;
        do {
            System.out.println("0: Quit");
            System.out.println("1: Add Child");
            System.out.println("2: Display Family Tree");
            option = Input.getInteger("Option: ");

            switch (option) {

                case 0:
                    System.out.println("Quitting Program");
                    break;
                case 1:
                    childName = Input.getString("Child Name: ");
                    try {
                        familyTree.addChild(childName);
                    } catch (FamilyTree.ChildNameNotUniqueException e) {
                        System.out.println("Child Name Not Unique - please try again");
                    }
                    break;

                case 2:
                    System.out.println(familyTree);
                    break;

                default:
                    System.out.println("Invalid Option - please try again");
            }
        } while (option != 0);
    }

}
