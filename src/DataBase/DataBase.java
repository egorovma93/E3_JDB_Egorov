package DataBase;
import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Tables.*;
public class DataBase {
    public void Create()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db"); // подключение к базе

            stmt = c.createStatement(); // создаем объект для запросов
            /*String sql = "CREATE TABLE USERS " +
                    "(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
                    " NAME           TEXT, " +
                    " SURNAME        TEXT)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE PRODUCTS " +
                    "(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
                    " TITLE           TEXT)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE BOOKING " +
                    "(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
                    " ID_USER INTEGER , " +
                    " FOREIGN KEY(ID_USER) REFERENCES USERS(ID))";
            stmt.executeUpdate(sql);*/

            /*String sql = "CREATE TABLE BOOKINGS " +
                    "(ID_BOOKING INTEGER NOT NULL, " +
                    " ID_PRODUCT INTEGER NOT NULL," +
                    " FOREIGN KEY(ID_BOOKING) REFERENCES BOOKING(ID), " +
                    " FOREIGN KEY(ID_PRODUCT) REFERENCES PRODUCTS(ID))";
            stmt.executeUpdate(sql);*/

            stmt.close();
            c.close();
            System.out.println("Таблицы созданы");
        }
        catch ( Exception e ) {  // ошибки
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public Object InsertUser(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            ResultSet rs = null;
            PreparedStatement pst = c.prepareStatement("INSERT INTO USERS (NAME,SURNAME) VALUES (?,?);");
            pst.setString(1, ((User) object).name);
            pst.setString(2, ((User) object).surname);
            pst.executeUpdate();
            pst = c.prepareStatement("SELECT last_insert_rowid()");
            rs = pst.executeQuery();
            int id = 0;
            while (rs.next())
            {
                id = rs.getInt(1);
            }
            object = new User(id,((User) object).name,((User) object).surname);
            rs.close();
            c.commit();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object InsertProduct(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            ResultSet rs = null;
            PreparedStatement pst = c.prepareStatement("INSERT INTO Products (TITLE) VALUES (?);");
            pst.setString(1, ((Product) object).title);
            pst.executeUpdate();
            pst = c.prepareStatement("SELECT last_insert_rowid()");
            rs = pst.executeQuery();
            int id = 0;
            while (rs.next())
            {
                id = rs.getInt(1);
            }
            object = new Product(id,((Product) object).title);
            rs.close();
            c.commit();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object InsertBooking(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            ResultSet rs = null;
            PreparedStatement pst = c.prepareStatement("INSERT INTO Booking (ID_USER) VALUES (?);");
            pst.setInt(1, ((Booking) object).id_user);
            pst.executeUpdate();
            pst = c.prepareStatement("SELECT last_insert_rowid()");
            rs = pst.executeQuery();
            int id = 0;
            while (rs.next())
            {
                id = rs.getInt(1);
            }
            object = new Booking(id,((Booking) object).id_user);
            rs.close();
            c.commit();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object InsertBookings(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            ResultSet rs = null;
            PreparedStatement pst = c.prepareStatement("INSERT INTO Bookings (id_booking,id_product) VALUES (?,?);");
            pst.setInt(1, ((Bookings) object).id_booking);
            pst.setInt(2, ((Bookings) object).id_product);
            pst.executeUpdate();
            c.commit();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public void UpdateUser(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            if (object.getClass().getSimpleName().equals("User")) {
                PreparedStatement pst = c.prepareStatement("UPDATE Users set NAME = ?, SURNAME = ? where ID=?;");
                pst.setString(1, ((User) object).name);
                pst.setString(2, ((User) object).surname);
                pst.setInt(3, ((User) object).id);
                pst.executeUpdate();
            }
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void UpdateProduct(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            PreparedStatement pst = c.prepareStatement("UPDATE Products set TITLE = ? where ID=?;");
            pst.setString(1, ((Product) object).title);
            pst.setInt(2, ((Product) object).id);
            pst.executeUpdate();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void UpdateBooking(Object object)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            PreparedStatement pst = c.prepareStatement("UPDATE Booking set USER_ID = ? where ID=?;");
            pst.setInt(1, ((Booking) object).id_user);
            pst.executeUpdate();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public Object SelectUser(String table, String column, String item)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            object = new User();
            PreparedStatement statement= null;
            ResultSet rs = null;
            if(column.equals("ID")) {
                statement = c.prepareStatement("select * from USERS where ID = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("NAME")) {
                statement = c.prepareStatement("select * from USERS where NAME = ?");
                statement.setString(1, item);
                rs = statement.executeQuery();
            }
            if(column.equals("SURNAME")) {
                statement = c.prepareStatement("select * from USERS where SURNAME = ?");
                statement.setString(1, item);
                rs = statement.executeQuery();
            }
            if(column.equals("ALL")) {
                List<User> users = new ArrayList<User>();
                statement = c.prepareStatement("select * from USERS");
                rs = statement.executeQuery();
                int id = 0;
                String  name="";
                String  surname="";
                while (rs.next())
                {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    surname = rs.getString("surname");
                    users.add(new User(id,name,surname));
                }
                rs.close();
                statement.close();
                stmt.close();
                c.close();
                return  users;
            }
            int id = 0;
            String  name="";
            String  surname="";
            while (rs.next())
            {
                id = rs.getInt("id");
                name = rs.getString("name");
                surname = rs.getString("surname");
            }
            rs.close();
            statement.close();
            object = new User(id,name,surname);
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object SelectProduct(String table, String column, String item)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            object = new Product();
            PreparedStatement statement= null;
            ResultSet rs = null;
            if(column.equals("ID")) {
                statement = c.prepareStatement("select * from Products where ID = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("TITLE")) {
                statement = c.prepareStatement("select * from Products where TITLE = ?");
                statement.setString(1, item);
                rs = statement.executeQuery();
            }
            if(column.equals("ALL")) {
                List<Product> Products = new ArrayList<Product>();
                statement = c.prepareStatement("select * from Products");
                rs = statement.executeQuery();
                int id = 0;
                String  title="";
                while (rs.next())
                {
                    id = rs.getInt("id");
                    title = rs.getString("title");
                    Products.add(new Product(id,title));
                }
                rs.close();
                statement.close();
                stmt.close();
                c.close();
                return  Products;
            }
            int id = 0;
            String  title="";
            while (rs.next())
            {
                id = rs.getInt("id");
                title = rs.getString("title");
            }
            rs.close();
            statement.close();
            object = new Product(id,title);
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object SelectBooking(String table, String column, String item)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            List<Booking> list = new ArrayList<Booking>();
            PreparedStatement statement= null;
            ResultSet rs = null;
            if(column.equals("ID")) {
                statement = c.prepareStatement("select * from Booking where ID = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("ID_USER")) {
                statement = c.prepareStatement("select * from Booking where ID_USER = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("ALL")) {
                List<Booking> Booking = new ArrayList<Booking>();
                statement = c.prepareStatement("select * from Booking");
                rs = statement.executeQuery();
                int id = 0;
                int id_user = 0;
                while (rs.next())
                {
                    id = rs.getInt("id");
                    id_user = rs.getInt("id_user");
                    Booking.add(new Booking(id,id_user));
                }
                rs.close();
                statement.close();
                stmt.close();
                c.close();
                return  Booking;
            }
            int id = 0;
            int  id_user=0;
            while (rs.next())
            {
                id = rs.getInt("id");
                id_user = rs.getInt("id_user");
                list.add(new Booking(id,id_user));
            }
            rs.close();
            statement.close();
            object = list;
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public Object SelectBookings(String table, String column, String item)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            List<Bookings> list = new ArrayList<Bookings>();
            PreparedStatement statement= null;
            ResultSet rs = null;
            if(column.equals("ID_BOOKING")) {
                statement = c.prepareStatement("select * from Bookings where ID_BOOKING = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("ID_PRODUCT")) {
                statement = c.prepareStatement("select * from Bookings where ID_PRODUCT = ?");
                statement.setInt(1, Integer.parseInt(item));
                rs = statement.executeQuery();
            }
            if(column.equals("ALL")) {
                List<Bookings> Bookings = new ArrayList<Bookings>();
                statement = c.prepareStatement("select * from Bookings");
                rs = statement.executeQuery();
                int id_booking = 0;
                int id_product = 0;
                while (rs.next())
                {
                    id_booking = rs.getInt("id_booking");
                    id_product = rs.getInt("id_product");
                    Bookings.add(new Bookings(id_booking,id_product));
                }
                rs.close();
                statement.close();
                stmt.close();
                c.close();
                return  Bookings;
            }
            int id_booking = 0;
            int  id_product=0;
            while (rs.next())
            {
                id_booking = rs.getInt("id_booking");
                id_product = rs.getInt("id_product");
                list.add(new Bookings(id_booking,id_product));
            }
            rs.close();
            statement.close();
            object = list;
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
    public void DeleteUser(String table, String column, int id)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            PreparedStatement statement= null;
            statement = c.prepareStatement("DELETE from Users where ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            c.commit();
            statement.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void DeleteProduct(String table, String column, int id)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            PreparedStatement statement= null;
            statement = c.prepareStatement("DELETE from Products where ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            c.commit();
            statement.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void DeleteBooking(String table, String column, int id)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            PreparedStatement statement= null;
            statement = c.prepareStatement("DELETE from Booking where ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            c.commit();
            statement.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public void DeleteBookings(String table, String column, int id)
    {
        Connection c = null;
        Statement stmt = null;
        Object object = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            PreparedStatement statement= null;
            if(column.equals("ID_BOOKING")) {
                statement = c.prepareStatement("DELETE from Bookings where ID_BOOKING = ?");
                statement.setInt(1, id);
            }
            else if(column.equals("ID_PRODUCT")) {
                statement = c.prepareStatement("DELETE from Bookings where ID_PRODUCT = ?");
                statement.setInt(1, id);
            }
            statement.executeUpdate();
            c.commit();
            statement.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
