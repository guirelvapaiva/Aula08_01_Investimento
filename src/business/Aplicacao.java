package business;

public class Aplicacao implements IAplicacao {
    
    private double montante;

    @Override
    public void calcularRendimento(float valorAplicado, int prazo, float taxa) {
        double i = taxa / 100.0;
        this.montante = valorAplicado * Math.pow(1.0 + i, prazo);
    }

    public double getMontante() {
        return montante;
    }
}