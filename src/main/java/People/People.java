package People;

public abstract  class People implements IPeople {
    private int id ;
    private String name;
    private  String cpfOrCnpj;
    private double acount ;
    public People(String cpfOrCnpj, int id, String name, double acount) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.id = id;
        this.name = name;
        this.acount =acount;
    }

    public double getAcount() {
        return acount;
    }

    public void setAcount(double acount) {
        this.acount = acount;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
