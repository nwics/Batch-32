package day5.model;

public class Tax {
    private double pph;
    private double tapera;
    private double PPn;

    public Tax(double pph, double tapera, double pPn) {
        this.pph = pph;
        this.tapera = tapera;
        PPn = pPn;
    }

    public double getPph() {
        return pph;
    }

    public void setPph(double pph) {
        this.pph = pph;
    }

    public double getTapera() {
        return tapera;
    }

    public void setTapera(double tapera) {
        this.tapera = tapera;
    }

    public double getPPn() {
        return PPn;
    }

    public void setPPn(double pPn) {
        PPn = pPn;
    }
}
