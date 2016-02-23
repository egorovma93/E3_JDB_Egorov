package Tables;
public class User  {
    public int id;
    public String name;
    public String surname;
    public User(){}
    public User(String name,String surname)
    {
        this.name=name;
        this.surname=surname;
    }
    public User(int id,String name,String surname)
    {
        this.id= id;
        this.name=name;
        this.surname=surname;
    }
}
