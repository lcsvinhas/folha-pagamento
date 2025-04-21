package org.serratec.servico;

public enum CalculoINSS {
    FAIXA1(1518.00, 7.50 / 100, 0.00),
    FAIXA2(2793.88, 9.00 / 100, 22.77),
    FAIXA3(4190.83, 12.00 / 100, 106.60),
    FAIXA4(8157.41, 14.00 / 100, 190.42);

    private Double remuneracaoINSS;
    private Double aliquotaINSS;
    private Double parcelaINSS;

    CalculoINSS(Double remuneracaoINSS, Double aliquotaINSS, Double parcelaINSS) {
        this.remuneracaoINSS = remuneracaoINSS;
        this.aliquotaINSS = aliquotaINSS;
        this.parcelaINSS = parcelaINSS;
    }

    public Double getRemuneracaoINSS() {
        return remuneracaoINSS;
    }

    public void setRemuneracaoINSS(Double remuneracaoINSS) {
        this.remuneracaoINSS = remuneracaoINSS;
    }

    public Double getAliquotaINSS() {
        return aliquotaINSS;
    }

    public void setAliquotaINSS(Double aliquotaINSS) {
        this.aliquotaINSS = aliquotaINSS;
    }

    public Double getParcelaINSS() {
        return parcelaINSS;
    }

    public void setParcelaINSS(Double parcelaINSS) {
        this.parcelaINSS = parcelaINSS;
    }
}
