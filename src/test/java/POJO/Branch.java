package POJO;

import org.openqa.selenium.WebElement;

public class Branch {
    private Integer id = 0;
    private String name;
    private String code;

    private WebElement viewButton;
    private WebElement editButton;
    private WebElement deleteButton;



    public Branch(Integer id, String branchName, String branchCode) {
        this(id, branchName, branchCode, null, null, null);
    }

    public Branch(Integer id, String branchName, String branchCode, WebElement viewBranchButton, WebElement editBranchButton, WebElement deleteBranchButton) {
        this.id = id;
        this.name = branchName;
        this.code = branchCode;
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

    public String getCode() {
        return code;
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

        if (obj instanceof Branch) {
            Branch target = (Branch) obj;

            if (this.getName().equals(target.getName()) && this.getCode().equals(target.getCode())) {
                isEqual = Boolean.TRUE;
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
