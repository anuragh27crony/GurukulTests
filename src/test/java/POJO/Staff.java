package POJO;

import org.openqa.selenium.WebElement;

public class Staff {
    public Integer id;
    public String name;
    public String branchName;


    private WebElement viewButton;
    private WebElement editButton;
    private WebElement deleteButton;


    public Staff(Integer id, String StaffName, String branchName) {
        this(id, StaffName, branchName, null, null, null);
    }

    public Staff(Integer id, String staffName, String branchName, WebElement viewBranchButton, WebElement editBranchButton, WebElement deleteBranchButton) {
        this.id = id;
        this.name = staffName;
        this.branchName = branchName;
        this.viewButton = viewBranchButton;
        this.editButton = editBranchButton;
        this.deleteButton = deleteBranchButton;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranchName() {
        return branchName;
    }

    public WebElement getViewButton() {
        return viewButton;
    }

    public WebElement getEditButton() {
        return editButton;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = Boolean.FALSE;

        if (obj instanceof Staff) {
            Staff target = (Staff) obj;

            if (this.getName().equals(target.getName()) && this.getBranchName().equals(target.getBranchName())) {
                isEqual = Boolean.TRUE;
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }

}
