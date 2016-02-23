package BusinessLogic;
import Tables.*;
import DataBase.DataBase;
import java.util.List;
public class BusinessLogic {
    public DataBase dataBase = new DataBase();
    public void Create()
    {
        dataBase.Create();
    }
    public Object Insert(Object object)
    {
        System.out.println(object.getClass().getSimpleName());
        if (object.getClass().getSimpleName().equals("User")) {
            object = (User) object;
            return dataBase.InsertUser(object);
        }
        else if (object.getClass().getSimpleName().equals("Product"))
        {
            object = (Product) object;
            return dataBase.InsertProduct(object);
        }
        else if (object.getClass().getSimpleName().equals("Booking")) {
            object = (Booking) object;
            return dataBase.InsertBooking(object);
        }
        else if (object.getClass().getSimpleName().equals("Bookings")) {
            object = (Bookings) object;
            return dataBase.InsertBookings(object);
        }
        else {return 0;}
    }
    public void Update(Object object)
    {
        if (object.getClass().getSimpleName().equals("User")) {
            object = (User) object;
            dataBase.UpdateUser(object);
        }
        else if (object.getClass().getSimpleName().equals("Product")) {
            object = (Product) object;
            dataBase.UpdateProduct(object);
        }
        else if (object.getClass().getSimpleName().equals("Booking")) {
            object = (Booking) object;
            dataBase.UpdateBooking(object);
        }
    }
    public Object Select(String table, String column, String item)
    {
        if (table.equals("User")) {
            return dataBase.SelectUser(table, column, item);
        }
        else if (table.equals("Product")){
            return dataBase.SelectProduct(table, column, item);
        }
        else if (table.equals("Booking")){
            return dataBase.SelectBooking(table, column, item);
        }
        else if (table.equals("Bookings")){
            return dataBase.SelectBookings(table, column, item);
        }
        else {return 0;}
    }
    public void Delete(String table, String column, int id)
    {
        if (table.equals("User")) {
            dataBase.DeleteUser(table, column, id);
        }
        else if (table.equals("Product")){
            dataBase.DeleteProduct(table, column, id);
        }
        else if (table.equals("Booking")){
            dataBase.DeleteBooking(table, column, id);
        }
        else if (table.equals("Bookings")){
            dataBase.DeleteBookings(table, column, id);
        }
    }
}
