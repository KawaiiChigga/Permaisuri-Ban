package cv.sunwell.permaisuriban.model;

import android.support.annotation.NonNull;

public class Address
{
    private int systemId;
    private int provinsiId;
    private int regencyId;
    private String jalan;
    private String regency;
    private String provinsi;


    public Address(int systemId, int provinsiId, int regencyId, String jalan, String regency, String provinsi) {
        this.systemId = systemId;
        this.provinsiId = provinsiId;
        this.regencyId = regencyId;
        this.jalan = jalan;
        this.regency = regency;
        this.provinsi = provinsi;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(int provinsiId) {
        this.provinsiId = provinsiId;
    }

    public int getRegencyId() {
        return regencyId;
    }

    public void setRegencyId(int regencyId) {
        this.regencyId = regencyId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getRegency() {
        return regency;
    }

    public void setRegency(String regency) {
        this.regency = regency;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
}
