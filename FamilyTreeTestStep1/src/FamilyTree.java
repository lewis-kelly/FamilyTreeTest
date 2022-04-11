/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lewiskelly
 */
public class FamilyTree {
    // B00387967 FamilyTree Class Version 1

    // declare child name not unique exception
    protected class ChildNameNotUniqueException extends Exception {
    };

    // declare node class
    private class FamilyTreeNode {

        private String name;
        private FamilyTreeNode partner;
        private FamilyTreeNode nextSibling;
        private FamilyTreeNode firstChild;
    }

    // declare ancestor node
    private FamilyTreeNode ancestor; // ancestor is root node of tree

    // declare current node
    private FamilyTreeNode current; // current node is used to traverse tree

    // constructor
    public FamilyTree(String ancestorName, String ancestorPartnerName) {
        this.ancestor = new FamilyTreeNode();
        this.ancestor.partner = new FamilyTreeNode();
        this.ancestor.name = ancestorName; // set up ancestor
        this.ancestor.partner.name = ancestorPartnerName; // set up partner
        this.ancestor.partner.partner = this.ancestor;
        this.current = this.ancestor; // set up current node
    }

    // addChild method
    public void addChild(String childName) throws ChildNameNotUniqueException {
        this.current = this.ancestor; // reset current node to point to ancestor

        if (this.current.firstChild == null) {
            this.current.firstChild = new FamilyTreeNode();
            this.current.firstChild.name = childName; // if first child is null add at first child
            this.current.partner.firstChild = this.current.firstChild; // link first child node to partner
            return; // return to avoid running unnecessary code
        }
        if (this.current.firstChild.nextSibling == null) {
            if (this.current.firstChild.name.equalsIgnoreCase(childName)) {
                throw new ChildNameNotUniqueException(); // if name equals child then throw exception
            } else {
                this.current.firstChild.nextSibling = new FamilyTreeNode();
                this.current.firstChild.nextSibling.name = childName; // if next sibling is null add at next sibling
            }
        } else if (this.current.firstChild.name.equalsIgnoreCase(childName) || this.current.firstChild.nextSibling.name.equalsIgnoreCase(childName)) {
            throw new ChildNameNotUniqueException(); // if name equals child or sibling then throw exception
        } else {
            this.current = this.current.firstChild.nextSibling; // set current to next sibling
            while (this.current.nextSibling != null) {
                this.current = this.current.nextSibling; // traverse siblings
                if (this.current.name.equalsIgnoreCase(childName)) {
                    throw new ChildNameNotUniqueException(); // if name equals current sibling then throw exception 
                }
            }
            this.current.nextSibling = new FamilyTreeNode();
            this.current.nextSibling.name = childName; // add at first null sibling
        }

    }

    // toString method
    public String toString() {
        String familyTreeDetails = new String(); // declare String object
        // display via ancestor
        if (this.ancestor != null) {
            familyTreeDetails += this.ancestor.name; // if ancestor not null, add name to string
            if (this.ancestor.partner != null) {
                familyTreeDetails += " partner " + this.ancestor.partner.name + "\n"; // if ancestor partner not null, add name to string
            } else {
                familyTreeDetails += "\n"; // else add line break
            }
            if (this.ancestor.firstChild != null) {
                familyTreeDetails += "\t" + this.ancestor.firstChild.name + "\n"; // if not null, add first child name to string
                if (this.ancestor.firstChild.nextSibling != null) {
                    this.current = this.ancestor.firstChild.nextSibling; // set current to next sibling
                    
                    while (this.current != null) {
                        familyTreeDetails += "\t" + this.current.name + "\n"; // traverse siblings
                        this.current = this.current.nextSibling;
                    }

                }
            } else {
                familyTreeDetails +=  "\t no children \n"; // else there are no children, add message to string
            }
        } 

        familyTreeDetails += "\n";

        // display via partner
        if (this.ancestor.partner != null) {
            familyTreeDetails += this.ancestor.partner.name; // if ancestor partner not null, add name to string
            if (this.ancestor.partner.partner != null) {
                familyTreeDetails += " partner " + this.ancestor.partner.partner.name + "\n"; // if ancestor (via partner) not null, add name to string
            } else {
                familyTreeDetails += "\n"; // else add line break
            }
            if (this.ancestor.partner.firstChild != null) {
                familyTreeDetails += "\t" + this.ancestor.partner.firstChild.name + "\n"; // if not null, add first child name to string
                if (this.ancestor.partner.firstChild.nextSibling != null) {
                    this.current = this.ancestor.partner.firstChild.nextSibling; // set current to next sibling
                    
                    while (this.current != null) {
                        familyTreeDetails += "\t" + this.current.name + "\n"; // traverse siblings
                        this.current = this.current.nextSibling;
                    }

                }
            } else {
                familyTreeDetails +=  "\t no children \n"; // else there are no children, add message to string
            }
        }
        return familyTreeDetails; // return string
    }
}
