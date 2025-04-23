package org.serratec.servico;

public enum CalculoIR {
    BASE1(2259.20, 0.00, 0.00),
    BASE2(2826.65, 7.50 / 100, 169.44),
    BASE3(3751.05, 15.00 / 100, 381.44),
    BASE4(4664.68, 22.50 / 100, 662.77),
    BASE5(4664.68, 27.50 / 100, 896.00);

    public static final Double valorDependentes = 189.59;
    private Double baseCalculoIR;
    private Double aliquotaIR;
    private Double parcelaIR;

    CalculoIR(Double baseCalculoIR, Double aliquotaIR, Double parcelaIR) {
        this.baseCalculoIR = baseCalculoIR;
        this.aliquotaIR = aliquotaIR;
        this.parcelaIR = parcelaIR;
    }

    public Double getBaseCalculoIR() {
        return baseCalculoIR;
    }

    public void setBaseCalculoIR(Double baseCalculoIR) {
        this.baseCalculoIR = baseCalculoIR;
    }

    public Double getAliquotaIR() {
        return aliquotaIR;
    }

    public void setAliquotaIR(Double aliquotaIR) {
        this.aliquotaIR = aliquotaIR;
    }

    public Double getParcelaIR() {
        return parcelaIR;
    }

    public void setParcelaIR(Double parcelaIR) {
        this.parcelaIR = parcelaIR;
    }
}
