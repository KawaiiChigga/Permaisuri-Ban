package cv.sunwell.permaisuriban.model;

import android.support.annotation.NonNull;

public class Item implements Comparable<Item>
{
    private int id;
    private String name;
    private int price;
    private String brand;
    private String memo;
    private String category;
    private String description;
    private int imgURL;
    private int count;

    public Item (int _id, String _brand, int _imgURL, String _description)
    {
        id = _id;
        brand = _brand;
        imgURL = _imgURL;
        description = _description;
    }

    public Item (int _id, String _brand, String _name, int _price, int _imgURL, String _category)
    {
        id = _id;
        brand = _brand;
        name = _name;
        price = _price;
        imgURL = _imgURL;
        category = _category;
    }

    public Item (int _id, String _brand, String _name, int _price, int _imgURL, int _count, String _category)
    {
        id = _id;
        brand = _brand;
        name = _name;
        price = _price;
        imgURL = _imgURL;
        count = _count;
        category = _category;
    }

    public int getId (){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String _description)
    {
        description = _description;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int _count)
    {
        count = _count;
    }

    public int getImgURL ()
    {
        return imgURL;
    }

    public void setImgURL (int _imgURL)
    {
        imgURL = _imgURL;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String _name)
    {
        name = _name;
    }

    public int getPrice ()
    {
        return price;
    }

    public void setPrice (int _price)
    {
        price = _price;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String _brand)
    {
        brand = _brand;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String _memo)
    {
        memo = _memo;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String _category)
    {
        category = _category;
    }

    @Override
    public int compareTo(@NonNull Item item) {
        return this.getName().compareTo(item.getName());
    }
}
