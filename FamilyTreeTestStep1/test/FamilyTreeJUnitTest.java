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
    
    public FamilyTreeJUnitTest() {
    }

    @Test
    public void TestToStringOutput() {
        // this test checks if the toString method returns the expected output when only the ancestor and partner are set up
        FamilyTree testFamilyTree = new FamilyTree("james", "mary"); // declare test family tree with hard coded ancestor and partner name for testing purposes

        String expectedOutput = "james partner mary\n\t no children \n\nmary partner james\n\t no children \n";
        String actualOutput = testFamilyTree.toString(); // call toString method

        Assert.assertEquals(expectedOutput, actualOutput);

    }
    
    @Test
    public void TestAddChild() {
        // this test checks if a child can be added to the ancestor and partner by checking that the toString method returns the expected output when the ancestor, partner and first child are set up
        FamilyTree testFamilyTree = new FamilyTree("james", "mary"); // declare test family tree with hard coded ancestor and partner name for testing purposes
        

        try {
            testFamilyTree.addChild("john"); // add child with hard coded name for testing purposes
        } catch (FamilyTree.ChildNameNotUniqueException e) { // try and catch to prevent unreported exception error although child name is unique
        }
     

        String actualOutput = testFamilyTree.toString(); // declare actualOutput as null initially to prevent error
        String expectedOutput = "james partner mary\n\tjohn\n\nmary partner james\n\tjohn\n";

        Assert.assertEquals(expectedOutput, actualOutput);

    }
}
