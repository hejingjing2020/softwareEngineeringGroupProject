package page.page1;

public class RecruitmentData {
    private String rid;
    private String uid;
    private String title;
    private String email;
    private String salary;
    private String type;
    private String description;


    public RecruitmentData() {}

    public RecruitmentData(int uid, String title, String email, String salary, String school, String description) {

    }
    public String getRid() { return rid; }
    public void setRid(String rid) { this.rid = rid; }

    public String getUid() { return uid; }
    public void setUid(String rid) { this.uid = uid; }

    public void setTitle(String title) {this.title = title;}
    public String getTitle() {
        return title;
    }

    public void setEmail(String email) {this.email = email;}
    public String getEmail() {
        return email;
    }

    public void setSalary(String salary) { this.salary = salary; }
    public String getSalary() {
        return salary;
    }

    public void setType(String school) { this.type = school; }
    public String getType() {
        return type;
    }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() {
        return description;
    }

}
