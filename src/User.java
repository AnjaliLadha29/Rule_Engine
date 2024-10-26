import java.util.*;
class User {
    private int age;
    private String department;
    private String salary;
    private int experience;
    User(int age,String department,String salary,int experience)
    {
        this.age=age;
        this.department=department;
        this.salary=salary;
        this.experience=experience;
    }

    public int getAge()
    {
        return age;
    }
    public String getDepartment()
    {
        return department;
    }
    public String getSalary()
    {
        return salary;
    }
    public int getExperience()
    {
        return experience;
    }
    public String toString()
    {
        return ("age = "+age+" department = '"+department+"' salary = "+salary+" experience = "+experience+"\n");
    }
    public static void main(String[] args) {
        //It will export data into database
        int choice = 1;
        int age=0,experience;
        String department;
        String salary;
        List<User> ul = new ArrayList<>();
        List<User> u1;
        String s1;
        Scanner s = new Scanner(System.in);
        while (choice == 1) {
            System.out.println("Enter choice: 1.Enter Details  2.Exit");
            choice = s.nextInt();
            if(choice==2)
                break;
            System.out.println("Enter Age: ");
            age=s.nextInt();
            System.out.println("Enter Department: ");
            department=s.next();
            System.out.println("Enter Salary:");
            salary=s.next();
            System.out.println("Enter Experience: ");
            experience=s.nextInt();
            ul.add(new User(age,department,salary,experience));
        }
        UserAdd ua = new UserAdd();
        if(age!=0)
            ua.addUser(ul);
        Rules r = new Rules();
        r.rules();
        DataFetcher df = new DataFetcher();
        List<User> ul1 = df.fetchData();
        Json j = new Json();
        u1=j.writeJson(ul1);
        //System.out.println(u1.toString());
        s1=j.readast();
        API a = new API();
        a.evaluate_rule(u1.toString(), s1);
    }
}
