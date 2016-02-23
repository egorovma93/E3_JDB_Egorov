
import BusinessLogic.BusinessLogic;
import Tables.*;
import java.util.Scanner;
import java.util.*;

public class Main
{
    static BusinessLogic businessLogic= new BusinessLogic();;
    public static void main(String[] args)
    {
        //Create();
        Input();
    }
    static List<User> userList = new ArrayList<User>();
    static Scanner in = new Scanner(System.in);
    public static void Input()
    {
        System.out.println("1-Добавить, 2-Обновить, 3-Выбрать, 4-Удалить 5-Добавить связь пользователь-продукт");
        System.out.println("Введите число команд:  ");
        int index = in.nextInt();  //считывает инт со строки
        switch (index) {
            case 1:  Insert();
                break;
            case 2:  Update();
                break;
            case 3:  Select();
                break;
            case 4:  Delete();
                break;
            case 5: AddBooking();
            default:
                break;
        }
        Input();
    }
    public static void AddBooking()
    {
        System.out.println("ID Пользователя:");
        System.out.println("ID Продукт:");
        int user_id = in.nextInt();
        int product_id = in.nextInt();
        Booking Booking = new Booking(user_id);
        Booking= (Booking)businessLogic.Insert(Booking);
        Bookings Bookings = new Bookings(Booking.id,product_id);
        Bookings = (Bookings)businessLogic.Insert(Bookings);
        System.out.println("Вставка завершена");
        User user = (User) businessLogic.Select("Пользователь", "ID",Integer.toString(user_id));
        System.out.println("ID Пользователя = " + user.id);
        System.out.println("Имя = " + user.name);
        System.out.println("Фамилия = " + user.surname);
        Product product = (Product)businessLogic.Select("ПРОДУКТ", "ID", Integer.toString(product_id));
        System.out.println( "ID Продукта = " + product.id );
        System.out.println( "Название = " + product.title );
    }
    public static void Create()
    {
        businessLogic.Create();
    }
    public static void Insert() {
        System.out.println("Вставить: 1-Пользователь, 2-Продукт, 3-Заказ, 4-Заказы ");
        int index = in.nextInt();
        switch (index) {
            case 1:
                System.out.println("Пользователь:");
                System.out.println("Вставить: ИМЯ");
                System.out.println("Вставить: ФАМИЛИЯ");
                String name =in.next();
                String surname = in.next();
                User user = new User(name, surname);
                user = (User)businessLogic.Insert(user);
                System.out.println("Вставка пользователя завершена ");
                System.out.println("ID = " + user.id);
                System.out.println("ИМЯ = " + user.name);
                System.out.println("ФАМИЛИЯ = " + user.surname);
                break;
            case 2:
                System.out.println("Продукт:");
                System.out.println("Вставить: Название");
                String title = in.next();
                Product product = new Product(title);
                product= (Product)businessLogic.Insert(product);
                System.out.println("Вставка продукта завершена");
                System.out.println("ID = " + product.id);
                System.out.println("Название = " + product.title);
                break;
            case 3:
                System.out.println("Заказ:");
                System.out.println("Вставить: ID_Пользователя");
                int ID_USER = in.nextInt();
                Booking Booking = new Booking(ID_USER);
                Booking= (Booking)businessLogic.Insert(Booking);
                System.out.println("Вставка заказа завершена");
                System.out.println("ID = " + Booking.id);
                System.out.println("ID_Пользователя = " + Booking.id_user);
                break;
            case 4:
                System.out.println("Заказы:");
                System.out.println("Вставить: ID_Заказ");
                System.out.println("Вставить: ID_Продукт");
                int ID_BOOKING = in.nextInt();
                int ID_PRODUCT = in.nextInt();
                Bookings Bookings = new Bookings(ID_BOOKING,ID_PRODUCT);
                Bookings = (Bookings)businessLogic.Insert(Bookings);
                System.out.println("Вставка заказов завершена");
                System.out.println("id_заказа = " + Bookings.id_booking);
                System.out.println("id_продукта = " + Bookings.id_product);
                break;
            default:
                break;
        }
        System.out.println("--------------------------------------------");
    }
    public static void Update() {
        System.out.println("Обновить: 1-Пользователь, 2-Продукт, 3-Заказ");
        int index = in.nextInt();
        String str="";
        switch (index) {
            case 1:
                User user = new User();
                System.out.println("ID Пользователя:");
                str = new Scanner(System.in).nextLine();
                user = (User) businessLogic.Select("User", "ID", str);
                System.out.println("ID = " + user.id);
                System.out.println("Имя = " + user.name);
                System.out.println("Фамилия = " + user.surname);
                if(user.id != 0) {
                    System.out.println("Обновить: Имя");
                    System.out.println("Обновить: Фамилия");
                    user.name = in.next();
                    user.surname = in.next();
                    businessLogic.Update(user);
                }
                break;
            case 2:
                Product product = new Product();
                System.out.println("ID продукта:");
                str= new Scanner(System.in).nextLine();
                product = (Product)businessLogic.Select("Product", "ID", str);
                System.out.println( "ID = " + product.id );
                System.out.println( "Название = " + product.title );
                if(product.id != 0) {
                    System.out.println("Обновить: Название");
                    product.title = in.next();
                    businessLogic.Update(product);
                }
                break;
            case 3:
                List<Booking> booking = new ArrayList<Booking>();
                System.out.println("ID заказа:");
                str= new Scanner(System.in).nextLine();
                booking = (ArrayList<Booking>)businessLogic.Select("Booking", "ID", str);
                for (Booking b:booking)
                {
                    System.out.println("ID = " + b.id);
                    System.out.println("ID_Пользователь = " + b.id_user);
                }
                if(booking.get(0).id != 0) {
                    System.out.println("Обновить: ID_Пользователь");
                    booking.get(0).id_user = in.nextInt();
                    businessLogic.Update(booking.get(0));
                }
                break;
            default:
                break;
        }
        System.out.println("Обновление завершено");
        System.out.println("--------------------------------------------");
    }
    public static void Select() {
        System.out.println("Выбрать: 1-Пользователь, 2-Продукт, 3-Заказ 4-Заказы");
        int index = in.nextInt();
        String str="";
        switch (index) {
            case 1:
                User user = new User();
                System.out.println("Пользователь:");
                System.out.println("Выбрать:1-ID, 2-Имя, 3-Фамилия 4-Все");
                index = in.nextInt();
                if(index>0 && index<5)
                {
                    switch (index) {
                        case 1:
                            System.out.println("ID Пользователя:");
                            str = new Scanner(System.in).nextLine();
                            user = (User) businessLogic.Select("User", "ID", str);
                            System.out.println("ID = " + user.id);
                            System.out.println("Имя = " + user.name);
                            System.out.println("Фамилия = " + user.surname);
                            break;
                        case 2:
                            System.out.println("Имя Пользователя:");
                            str = new Scanner(System.in).nextLine();
                            user = (User) businessLogic.Select("User", "NAME", str);
                            System.out.println("ID = " + user.id);
                            System.out.println("NAME = " + user.name);
                            System.out.println("Фамилия = " + user.surname);
                            break;
                        case 3:
                            System.out.println("Фамилия Пользователя:");
                            str = new Scanner(System.in).nextLine();
                            user = (User) businessLogic.Select("User", "SURNAME", str);
                            System.out.println("ID = " + user.id);
                            System.out.println("Имя = " + user.name);
                            System.out.println("Фамилия = " + user.surname);
                            break;
                        case 4:
                            List<User> users = (List<User>) businessLogic.Select("User", "ALL", str);
                            for (User u:users) {
                                System.out.println("ID = " + u.id);
                                System.out.println("Имя = " + u.name);
                                System.out.println("Фамилия = " + u.surname);
                            }
                            return;
                        default:
                            break;
                    }
                    System.out.println("Выбор пользователя:1-Заказ, 2-Продукт");
                    index = in.nextInt();
                    List<Booking> booking;
                    switch (index) {
                        case 1:
                            System.out.println( "Заказ: ");
                            booking = (List<Booking>)businessLogic.Select("Booking", "ID_USER", Integer.toString(user.id));
                            for (Booking b:booking) {
                                System.out.println("ID = " + b.id);
                                System.out.println("ID_Пользователя = " + b.id_user);
                            }
                            break;
                        case 2:
                            System.out.println( "Продукт: ");
                            booking = (List<Booking>)businessLogic.Select("Booking", "ID_USER", Integer.toString(user.id));
                            for (Booking b:booking)
                            {
                                List<Bookings> bookings = (ArrayList<Bookings>)businessLogic.Select("Bookings", "ID_BOOKING", Integer.toString(b.id));
                                for (Bookings bk:bookings)
                                {
                                    Product product = (Product)businessLogic.Select("Product", "ID", Integer.toString(bk.id_product));
                                    System.out.println( "Имя = " + product.title );
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                break;
            case 2:
                Product product = new Product();
                System.out.println("Продукт:");
                System.out.println("Выбор: 1-ID, 2-Название 3-Все");
                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("ID Продукта:");
                        str= new Scanner(System.in).nextLine();
                        product = (Product)businessLogic.Select("Product", "ID", str);
                        System.out.println( "ID = " + product.id );
                        System.out.println( "Название = " + product.title );
                        break;
                    case 2:
                        System.out.println("Название Продукта:");
                        str= new Scanner(System.in).nextLine();
                        product = (Product)businessLogic.Select("Product", "TITLE", str);
                        System.out.println( "ID = " + product.id );
                        System.out.println( "Название = " + product.title);
                        break;
                    case 3:
                        List<Product> products = (List<Product>) businessLogic.Select("Product", "ALL", str);
                        for (Product p:products)
                        {
                            System.out.println("ID = " + p.id);
                            System.out.println("Название = " + p.title);
                        }
                    default:
                        break;
                }
                break;
            case 3:
                List<Booking> booking = new ArrayList<Booking>();
                System.out.println("Заказ:");
                System.out.println("Выбрать: 1-ID, 2-ID_Пользователя 3-Все");
                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("ID Заказа:");
                        str= new Scanner(System.in).nextLine();
                        booking = (ArrayList<Booking>)businessLogic.Select("Booking", "ID", str);
                        for (Booking b:booking) {
                            System.out.println("ID = " + b.id);
                            System.out.println("ID_Пользователя = " + b.id_user);
                        }
                        break;
                    case 2:
                        System.out.println("Заказ ID_Пользователя:");
                        str= new Scanner(System.in).nextLine();
                        booking = (List<Booking>)businessLogic.Select("Booking", "ID_USER", str);
                        for (Booking b:booking) {
                            System.out.println("ID = " + b.id);
                            System.out.println("ID_Пользователя = " + b.id_user);
                        }
                        break;
                    case 3:
                        List<Booking> Booking = (List<Booking>) businessLogic.Select("Booking", "ALL", str);
                        for (Booking b:Booking) {
                            System.out.println("ID = " + b.id);
                            System.out.println("ID_Пользователя = " + b.id_user);
                        }
                        return;
                    default:
                        break;
                }
                break;
            case 4:
                List<Bookings> bookings = new ArrayList<Bookings>();
                System.out.println("Заказы:");
                System.out.println("Выбрать: 1-ID_Заказа, 2-ID_Продукта 3-Все");
                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("Заказ ID_Заказа:");
                        str= new Scanner(System.in).nextLine();
                        bookings = (ArrayList<Bookings>)businessLogic.Select("Bookings", "ID_BOOKING", str);
                        for (Bookings b:bookings) {
                            System.out.println("ID_Заказа = " + b.id_booking);
                            System.out.println("ID_Продукта = " + b.id_product);
                        }
                        break;
                    case 2:
                        System.out.println("Заказ ID_Продукта:");
                        str= new Scanner(System.in).nextLine();
                        bookings = (List<Bookings>)businessLogic.Select("Bookings", "ID_PRODUCT", str);
                        for (Bookings b:bookings) {
                            System.out.println("ID_Заказа = " + b.id_booking);
                            System.out.println("ID_Продукта = " + b.id_product);
                        }
                        break;
                    case 3:
                        List<Bookings> Bookings = (List<Bookings>) businessLogic.Select("Bookings", "ALL", str);
                        for (Bookings bs:Bookings) {
                            System.out.println("id_заказа = " + bs.id_booking);
                            System.out.println("id_продукта = " + bs.id_product);
                        }
                        return;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        System.out.println("--------------------------------------------");
    }
    public static void Delete() {
        System.out.println("Удалить: 1-Пользователя, 2-Продукт, 3-Заказ");
        int index = in.nextInt();
        int id;
        switch (index) {
            case 1:
                System.out.println("Удалить пользователя: ID");
                id = in.nextInt();
                List<Booking> booking = (List<Booking>)businessLogic.Select("Booking", "ID_USER", Integer.toString(id));
                for (Booking b:booking)
                {
                    List<Bookings> bookings = (ArrayList<Bookings>)businessLogic.Select("Bookings", "ID_BOOKING", Integer.toString(b.id));
                    for (Bookings bk:bookings)
                    {
                        businessLogic.Delete("Bookings","ID_BOOKING", bk.id_booking);
                    }
                    businessLogic.Delete("Booking","ID", b.id_user);
                }
                businessLogic.Delete("User","ID", id);
                break;
            case 2:
                System.out.println("Удалить продукт: ID");
                id = in.nextInt();
                List<Bookings> bookings = (ArrayList<Bookings>)businessLogic.Select("Bookings", "ID_PRODUCT", Integer.toString(id));
                for (Bookings bk:bookings)
                {
                    businessLogic.Delete("Bookings","ID_PRODUCT", bk.id_product);
                }
                businessLogic.Delete("Product","ID", id);
                break;
            case 3:
                System.out.println("Удалить Заказ: ID");
                id = in.nextInt();
                List<Bookings> bookingse = (ArrayList<Bookings>)businessLogic.Select("Bookings", "ID_BOOKING", Integer.toString(id));
                for (Bookings bk:bookingse)
                {
                    businessLogic.Delete("Bookings","ID_BOOKING", bk.id_booking);
                }
                businessLogic.Delete("Booking","ID", id);
                break;
            default:
                break;
        }
        System.out.println("Удаление завершено");
        System.out.println("--------------------------------------------");

    }

}
