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

    // B00387967 FamilyTreeTest Version 2
    public static void main(String[] args) {
        // get ancestor name from user
        String ancestorName = Input.getString("Ancestor Name: ");
        // declare FamilyTree object
        FamilyTree familyTree = new FamilyTree(ancestorName);

        // menu
        String partnerName, childName;
        Integer option, identifier;
        do {
            System.out.println("0: Quit");
            System.out.println("1: Add Partner");
            System.out.println("2: Add Child");
            System.out.println("3: Display Family Tree");
            System.out.println("4: Display Family Member");
            option = Input.getInteger("Option: ");

            switch (option) {

                case 0:
                    System.out.println("Quitting Program");
                    break;
                case 1:
                    System.out.println(familyTree);
                    identifier = Input.getInteger("Identifier: ");
                    try {
                        familyTree.checkPartner(identifier);
                        partnerName = Input.getString("Partner Name: ");
                        familyTree.addPartner(partnerName);
                    } catch(FamilyTree.NoMatchFoundForIdentifierException e){
                        System.out.println("Identifier does not match any existing family members - please try again");
                    }
                      catch(FamilyTree.FamilyMemberHasPartnerException e){
                        System.out.println("Family member already has a partner - please try again");
                    }
                    break;
                case 2:
                    try {
                        familyTree.checkAncestor();
                        childName = Input.getString("Child Name: ");
                        familyTree.addChild(childName);
                    } catch(FamilyTree.AncestorHasNoPartnerException e){
                        System.out.println("Ancestor has no partner, so no children can be added");
                    }
                      catch(FamilyTree.ChildNameNotUniqueException e) {
                        System.out.println("Child Name Not Unique - please try again");
                    }
                    break;

                case 3:
                    System.out.println(familyTree);
                    break;
                    
                case 4:
                    identifier = Input.getInteger("Identifier: ");
                    try {
                        System.out.println(familyTree.getFamilyMember(identifier));
                    } catch(FamilyTree.NoMatchFoundForIdentifierException e){
                        System.out.println("Identifier does not match any existing family members - please try again");
                    }
                    break;

                default:
                    System.out.println("Invalid Option - please try again");
            }
        } while (option != 0);
    }

}
