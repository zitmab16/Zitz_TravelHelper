
package BL;


public class Destination {
    private String zipCode;
    private String destname;

    public Destination(String zipCode, String destname) {
        this.zipCode = zipCode;
        this.destname = destname;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDestname() {
        return destname;
    }

    public void setDestname(String destname) {
        this.destname = destname;
    }
    
    
}
