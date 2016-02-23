package Tables;
public class Product {
    public int id;
    public String title;
    public Product(){}
    public Product(String title)
    {
        this.title=title;
    }
    public Product(int id,String title)
    {
        this.id = id;
        this.title=title;
    }
}
