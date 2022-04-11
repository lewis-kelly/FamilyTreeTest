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
    // B00387967 FamilyTree Class Version 2

    // declare child name not unique exception
    protected class ChildNameNotUniqueException extends Exception {
    };

    // declare no match found for identifier exception
    protected class NoMatchFoundForIdentifierException extends Exception {
    };

    // declare family member has partner exception
    protected class FamilyMemberHasPartnerException extends Exception {
    };

    // declare ancestor has no partner exception
    protected class AncestorHasNoPartnerException extends Exception {
    };

    // declare node class
    private class FamilyTreeNode {

        private String name;
        private Integer identifier;
        private FamilyTreeNode partner;
        private FamilyTreeNode nextSibling;
        private FamilyTreeNode firstChild;
    }

    // declare ancestor node
    private FamilyTreeNode ancestor; // ancestor is root node of tree

    // declare current node
    private FamilyTreeNode current; // current node is used to traverse tree

    // declare identifier counter
    private Integer identifierCounter; // identifier counter is used to keep track of assigned identifiers

    // constructor
    public FamilyTree(String ancestorName) {
        this.ancestor = new FamilyTreeNode();
        this.ancestor.name = ancestorName; // set up ancestor
        this.identifierCounter = 1;
        this.ancestor.identifier = this.identifierCounter; // set up ancestor identifier
        this.current = this.ancestor; // set up current node
    }

    // checkPartner method
    public void checkPartner(Integer identifier) throws NoMatchFoundForIdentifierException, FamilyMemberHasPartnerException {
        checkIdentifier(identifier); // check identifier
        this.current = retrieveFamilyMemberNode(identifier); // get family member object and assign it to current
        if (this.current.partner != null) {
            throw new FamilyMemberHasPartnerException();
        }

    }

    // checkIdentifer method
    private void checkIdentifier(Integer identifier) throws NoMatchFoundForIdentifierException {
        if (identifier > this.identifierCounter || identifier < 1) { // identifierCounter keeps track of assigned identifiers with the current value being the last assigned identifier, therefore can compare it to given identifier to check if valid
            throw new NoMatchFoundForIdentifierException();
        }

    }

    // retrieveFamilyMemberNode method
    private FamilyTreeNode retrieveFamilyMemberNode(Integer identifier) {
        FamilyTreeNode familyMember = null;
        this.current = this.ancestor; // reset current node to point to ancestor

        if (this.current.identifier.equals(identifier)) { //check ancestor
            familyMember = this.current;
            return familyMember;
        } if(this.current.partner != null){ 
            if (this.current.partner.identifier.equals(identifier)) { // check ancestor partner
            familyMember = this.current.partner;
            return familyMember;
        }} if(this.current.firstChild != null){ 
            if (this.current.firstChild.identifier.equals(identifier)) {// check first child of ancestor
            familyMember = this.current.firstChild;
            return familyMember;
        }} if(this.current.firstChild.partner != null){ 
            if (this.current.firstChild.partner.identifier.equals(identifier)) { //check partner of ancestor's first child
            familyMember = this.current.firstChild.partner;
            return familyMember;
        }} if (this.current.firstChild.nextSibling != null) {
            this.current = this.current.firstChild.nextSibling;
            while (this.current != null) { // traverse siblings and their partners
                if (this.current.identifier.equals(identifier)) {
                    familyMember = this.current;
                    return familyMember;
                }
                if (this.current.partner != null) {
                    if (this.current.partner.identifier.equals(identifier)) {
                        familyMember = this.current.partner;
                        return familyMember;
                    }
                }
                this.current = this.current.nextSibling;
            }
        }
        return familyMember;
    }

// addPartner method
    public void addPartner(String partnerName) {
        this.current.partner = new FamilyTreeNode();
        this.current.partner.name = partnerName;
        this.identifierCounter++;
        this.current.partner.identifier = this.identifierCounter;
        this.current.partner.partner = this.current;

    }

    // checkAncestor method
    public void checkAncestor() throws AncestorHasNoPartnerException {
        if (this.ancestor.partner == null) {
            throw new AncestorHasNoPartnerException();
        }

    }

    // addChild method
    public void addChild(String childName) throws ChildNameNotUniqueException {
        this.current = this.ancestor; // reset current node to point to ancestor

        if (this.current.firstChild == null) {
            this.current.firstChild = new FamilyTreeNode();
            this.current.firstChild.name = childName; // if first child is null add at first child
            this.identifierCounter++;
            this.current.firstChild.identifier = this.identifierCounter;
            this.current.partner.firstChild = this.current.firstChild; // link first child node to partner
            return; // return to avoid running unnecessary code
        }
        if (this.current.firstChild.nextSibling == null) {
            if (this.current.firstChild.name.equalsIgnoreCase(childName)) {
                throw new ChildNameNotUniqueException(); // if name equals child then throw exception
            } else {
                this.current.firstChild.nextSibling = new FamilyTreeNode();
                this.current.firstChild.nextSibling.name = childName; // if next sibling is null add at next sibling
                this.identifierCounter++;
                this.current.firstChild.nextSibling.identifier = this.identifierCounter;
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
            this.identifierCounter++;
            this.current.nextSibling.identifier = this.identifierCounter;
        }

    }

    // toString method
    public String toString() {
        String familyTreeDetails = new String(); // declare String object
        
        if (this.ancestor != null) {
            familyTreeDetails += this.ancestor.name + "(identifier " + this.ancestor.identifier + ")"; // if ancestor name not null, add name and identifier to string
            if (this.ancestor.partner != null) {
                familyTreeDetails += " partner " + this.ancestor.partner.name + "(identifier " + this.ancestor.partner.identifier + ") \n"; // if ancestor partner not null, add name and identifier to string
            } else {
                familyTreeDetails += " has no partner \n"; // else add has no partner to string
            }
            if (this.ancestor.firstChild != null) {
                familyTreeDetails += "\t" + this.ancestor.firstChild.name + "(identifier " + this.ancestor.firstChild.identifier + ")"; // if not null, add first child name and identifier to string
                if (this.ancestor.firstChild.partner != null) {
                    familyTreeDetails += " partner " + this.ancestor.firstChild.partner.name + "(identifier " + this.ancestor.firstChild.partner.identifier + ") \n"; // if not null, add first child's partner's name and identifier to string
                    if (this.ancestor.firstChild.firstChild != null) { // displaying children of children is beyond spec
                    } else {
                        familyTreeDetails += "\t \t no children \n";
                    }
                } else {
                    familyTreeDetails += " has no partner \n"; // else add has no partner to string
                }
                if (this.ancestor.firstChild.nextSibling != null) {
                    this.current = this.ancestor.firstChild.nextSibling; // set current to next sibling

                    while (this.current != null) {
                        familyTreeDetails += "\t" + this.current.name + "(identifier " + this.current.identifier + ")"; // traverse siblings

                        if (this.current.partner != null) {
                            familyTreeDetails += " partner " + this.current.partner.name + "(identifier " + this.current.partner.identifier + ") \n"; // if not null, add next sibling's partner's name and identifier to string
                            if (this.current.firstChild != null) { // displaying children of children is beyond spec
                            } else {
                                familyTreeDetails += "\t \t no children \n";
                            }
                        } else {
                            familyTreeDetails += " has no partner \n"; // else add has no partner to string
                        }
                        this.current = this.current.nextSibling;
                    }

                }
            } else {
                if (this.ancestor.partner != null) {
                    familyTreeDetails += "\t no children \n"; // else there are no children, add message to string
                }
            }
        }

        familyTreeDetails += "\n";

        return familyTreeDetails; // return string
    }

    public String getFamilyMember(Integer identifier) throws NoMatchFoundForIdentifierException {
        checkIdentifier(identifier); // check identifier
        this.current = retrieveFamilyMemberNode(identifier); // retrieve family member node and assign it to current

        String familyMemberDetails = new String(); // declare String object
        
            familyMemberDetails += this.current.name + "(identifier " + this.current.identifier + ")"; // add family member name and identifier to string
            if (this.current.partner != null) {
                familyMemberDetails += " partner " + this.current.partner.name + "(identifier " + this.current.partner.identifier + ") \n"; // if partner not null, add name and identifier to string
            } else {
                familyMemberDetails += " has no partner \n"; // else add has no partner to string
            }
            if (this.current.firstChild != null) {
                familyMemberDetails += "\t" + this.current.firstChild.name + "(identifier " + this.current.firstChild.identifier + ")"; // if not null, add first child name and identifier to string
                if (this.current.firstChild.partner != null) {
                    familyMemberDetails += " partner " + this.ancestor.firstChild.partner.name + "(identifier " + this.ancestor.firstChild.partner.identifier + ") \n"; // if not null, add first child's partner's name and identifier to string
                    if (this.current.firstChild.firstChild != null) { // displaying children of children is beyond spec
                    } else {
                        familyMemberDetails += "\t \t no children \n";
                    }
                } else {
                    familyMemberDetails += " has no partner \n"; // else add has no partner to string
                }
                if (this.current.firstChild.nextSibling != null) {
                    this.current = this.current.firstChild.nextSibling; // set current to next sibling

                    while (this.current != null) {
                        familyMemberDetails += "\t" + this.current.name + "(identifier " + this.current.identifier + ")"; // traverse siblings

                        if (this.current.partner != null) {
                            familyMemberDetails += " partner " + this.current.partner.name + "(identifier " + this.current.partner.identifier + ") \n"; // if not null, add next sibling's partner's name and identifier to string
                            if (this.current.firstChild != null) { // displaying children of children is beyond spec
                            } else {
                                familyMemberDetails += "\t \t no children \n";
                            }
                        } else {
                            familyMemberDetails += " has no partner \n"; // else add has no partner to string
                        }
                        this.current = this.current.nextSibling;
                    }

                }
            } else {
                if (this.current.partner != null) {
                    familyMemberDetails += "\t no children \n"; // else there are no children, add message to string
                }
            }

        return familyMemberDetails; // return string
    }
        
    }

