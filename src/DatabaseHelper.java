import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
class UserAdd
{
    public void addUser(List<User> user)
    {
        String sql = "INSERT INTO user_records (age, department, salary, experience) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseHelper.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            for (User u : user) {
                ps.setInt(1, u.getAge());
                ps.setString(2, u.getDepartment());
                ps.setString(3, u.getSalary());
                ps.setInt(4, u.getExperience());
                ps.addBatch();
            }
            ps.executeUpdate();
            System.out.println("User records inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    /*public void addAge(List<Integer> age) {
        String sql = "INSERT INTO age(age) VALUES (?)";
        try (Connection connection = DatabaseHelper.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            for (i = 0; i <= age.size() - 1; i++) {
                ps.setInt(1, age.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addDepartment(List<String> department) {
        String sql = "INSERT INTO department(department) VALUES (?)";
        try (Connection connection = DatabaseHelper.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            for (i = 0; i <= department.size() - 1; i++) {
                ps.setString(1, department.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addSalary(List<String> salary) {
        String sql = "INSERT INTO salary(salary) VALUES (?)";
        try (Connection connection = DatabaseHelper.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            for (i = 0; i <= salary.size() - 1; i++) {
                ps.setString(1, salary.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addExperience(List<Integer> experience) {
        String sql = "INSERT INTO experience(experience) VALUES (?)";
        try (Connection connection = DatabaseHelper.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            for (i = 0; i <= experience.size() - 1; i++) {
                ps.setInt(1, experience.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

class DataFetcher
{
    public List<User> fetchData()
    {
        List<User> ul = new ArrayList<>();
        String q = "SELECT age, department, salary, experience FROM user_records";
        try(Connection connection = DatabaseHelper.getConnection();Statement statement = connection.createStatement();ResultSet resultSet = statement.executeQuery(q))
        {
            while(resultSet.next())
            {
                int age = resultSet.getInt("age");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");
                int experience = resultSet.getInt("experience");
                ul.add(new User(age, department, salary, experience));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ul;
    }
}
class DatabaseHelper
{
    public static Connection getConnection() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/user_records";
        String user = "";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

        //public static void main(String[] args) {
        /*String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";
        String createTableSQL = "CREATE TABLE user_records (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "age INT NOT NULL, " +
                "department VARCHAR(100) NOT NULL, " +
                "salary VARCHAR(20) NOT NULL, " +  // Store salary as string
                "experience INT NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'user_records' created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //}
}
class Json
{
    public List<User> writeJson(List<User> ul)
    {
        Gson gson = new Gson();
        String json = gson.toJson(ul);
        //System.out.println(json);
        List<User> u;
        try(FileWriter fw = new FileWriter("user.json"))
        {
            fw.write(json);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try(BufferedReader br = new BufferedReader(new FileReader("user.json")))
        {
            u = gson.fromJson(br, new TypeToken<List<User>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
    public String readast()
    {
        BufferedReader br;
        String st;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader("ast.json"));
            while((st=br.readLine())!=null)
            {
                sb.append(st);
            }
            //System.out.println(sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}