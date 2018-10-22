package cv.sunwell.permaisuriban.model;

public class Regency {
    private int regencyId;
    private String regencyName;

    public Regency(int regencyId, String regencyName) {
        this.regencyId = regencyId;
        this.regencyName = regencyName;
    }

    public int getRegencyId() {
        return regencyId;
    }

    public void setRegencyId(int regencyId) {
        this.regencyId = regencyId;
    }

    public String getRegencyName() {
        return regencyName;
    }

    public void setRegencyName(String regencyName) {
        this.regencyName = regencyName;
    }

    @Override
    public String toString() {
        return regencyName;
    }
}
