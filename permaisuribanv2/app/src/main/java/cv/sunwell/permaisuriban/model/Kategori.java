package cv.sunwell.permaisuriban.model;

public class Kategori {

    int id;
    String namamerk;
    int imgURL;

    public Kategori(int id, String namamerk, int imgURL) {
        this.id = id;
        this.namamerk = namamerk;
        this.imgURL = imgURL;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamamerk() {
        return namamerk;
    }

    public void setNamamerk(String namamerk) {
        this.namamerk = namamerk;
    }
}
