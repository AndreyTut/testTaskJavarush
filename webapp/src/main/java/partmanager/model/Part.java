package partmanager.model;



import org.hibernate.annotations.Proxy;

import javax.persistence.*;



@Entity
@Table(name = "part")
@Proxy(lazy = false)
public class Part {


    public Part(){}


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String compName;
    private String manuf;
    private boolean ness;
    private int quant;

    public int getId() {
        return id;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf;
    }

    public boolean isNess() {
        return ness;
    }

    public void setNess(boolean ness) {
        this.ness = ness;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(id));

        if (id < 10) stringBuilder.append("  | ");
        else stringBuilder.append(" | ");

        stringBuilder.append(compName);
        for (int i = compName.length(); i <= 30; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("|");

        stringBuilder.append(manuf);
        for (int i = manuf.length(); i <= 20; i++) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("|");

        if (ness) stringBuilder.append("necessary"+"   |");
        else stringBuilder.append("unnecessary"+" |");

        stringBuilder.append(quant);

        return stringBuilder.toString();
    }


}
