/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author lewiskelly
 */
public class FamilyTreeJUnitTest {

    //B00387967 Family Tree JUnit Tests
    public FamilyTreeJUnitTest() {
    }

    @Test
    public void TestToStringOutput() {
        // this test checks if the toString method returns the expected output when only the ancestor is set up
        FamilyTree testFamilyTree = new FamilyTree("james"); // declare test family tree with hard coded ancestor name for testing purposes

        String expectedOutput = "james(identifier 1) has no partner \n\n";
        String actualOutput = testFamilyTree.toString(); // call toString method

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void TestGetFamilyMemberOutput() {
        // this test checks if the getFamilyMember method returns the expected output when only the ancestor is set up
        FamilyTree testFamilyTree = new FamilyTree("james"); // declare test family tree with hard coded ancestor name for testing purposes
        String actualOutput = null; // declare actualOutput as null initially to prevent error
        String expectedOutput = "james(identifier 1) has no partner \n";
        try {
            actualOutput = testFamilyTree.getFamilyMember(1); /// call getFamilyTree method passing in identifier of 1 (ancestor identifier)
        } catch (FamilyTree.NoMatchFoundForIdentifierException e) { // try and catch to prevent unreported exception error although identifier is valid
        }

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void TestAddPartner() {
        // this test checks if a partner can be added to the ancestor by checking that the getFamilyMember method returns the expected output when the ancestor and partner are set up
        FamilyTree testFamilyTree = new FamilyTree("james"); // declare test family tree with hard coded ancestor name for testing purposes
        try {
            testFamilyTree.checkPartner(1); //pass in identifier of 1 (ancestor identifier) to checkPartner method to set up current attribute used by addPartner method
            testFamilyTree.addPartner("mary"); // add partner with hard coded name for testing purposes
        } catch (FamilyTree.NoMatchFoundForIdentifierException | FamilyTree.FamilyMemberHasPartnerException e) { // try and catch to prevent unreported exception error although identifier is valid and ancestor has no partner
        }
      
        String actualOutput = null; // declare actualOutput as null initially to prevent error
        String expectedOutput = "mary(identifier 2) partner james(identifier 1) \n\t no children \n";
        try {
            actualOutput = testFamilyTree.getFamilyMember(2); /// call getFamilyTree method passing in identifier of 2 (ancestor's partner identifier)
        } catch (FamilyTree.NoMatchFoundForIdentifierException e) {// try and catch to prevent unreported exception error although identifier is valid
        }

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void TestAddChild() {
        // this test checks if a child can be added to the ancestor and partner by checking that the getFamilyMember method returns the expected output when the ancestor, partner and first child are set up
        FamilyTree testFamilyTree = new FamilyTree("james"); // declare test family tree with hard coded ancestor name for testing purposes
        try {
            testFamilyTree.checkPartner(1); // pass in identifier of 1 (ancestor identifier) to checkPartner method to set up current attribute used by addPartner method
            testFamilyTree.addPartner("mary"); // add partner with hard coded name for testing purposes
        } catch (FamilyTree.NoMatchFoundForIdentifierException | FamilyTree.FamilyMemberHasPartnerException e) { // try and catch to prevent unreported exception error although identifier is valid and ancestor has no partner
        }

        try {
            testFamilyTree.checkAncestor(); // checkAncestor method is not needed in this case but is included to show how it is used in the main method
            testFamilyTree.addChild("john"); // add child with hard coded name for testing purposes
        } catch (FamilyTree.AncestorHasNoPartnerException | FamilyTree.ChildNameNotUniqueException e) { // catch to prevent unreported exception error although ancestor has a partner and child name is unique
        }
     

        String actualOutput = null; // declare actualOutput as null initially to prevent error
        String expectedOutput = "john(identifier 3) has no partner \n";
        try {
            actualOutput = testFamilyTree.getFamilyMember(3); /// call getFamilyTree method passing in identifier of 3 (ancestor's first child identifier)
        } catch (FamilyTree.NoMatchFoundForIdentifierException e) {// try and catch to prevent unreported exception error although identifier is valid
        }

        Assert.assertEquals(expectedOutput, actualOutput);

    }
}
