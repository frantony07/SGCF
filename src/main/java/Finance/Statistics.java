package Finance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistics {
    private final List<Ledger> vetor;
    private final List<Double> values;

    public Statistics(List<Ledger> vetor) {
        this.vetor = new ArrayList<>(vetor);
        this.values = new ArrayList<>();
        for (Ledger ledger : this.vetor) {
            this.values.add(ledger.getRecordedMoney());
        }
        Collections.sort(this.values);
    }

    private void validateNotEmpty() {
        if (values.isEmpty()) {
            throw new IllegalStateException("Nao ha dados para calcular estatisticas.");
        }
    }

    public double maxValue() {
        validateNotEmpty();
        return values.get(values.size() - 1);
    }

    public double minValue() {
        validateNotEmpty();
        return values.get(0);
    }

    public double avg() {
        validateNotEmpty();
        double sumOfTotal = 0;
        for (double value : values) {
            sumOfTotal += value;
        }
        return sumOfTotal / values.size();
    }

    public double median() {
        validateNotEmpty();
        int mid = values.size() / 2;
        if (values.size() % 2 == 0) {
            return (values.get(mid - 1) + values.get(mid)) / 2.0;
        }
        return values.get(mid);
    }

    public double desvioPadrao() {
        validateNotEmpty();
        if (values.size() == 1) {
            return 0.0;
        }
        double average = avg();
        double sum = 0;
        for (double value : values) {
            sum += Math.pow(value - average, 2);
        }
        double varianciaAmostral = sum / (values.size() - 1);
        return Math.sqrt(varianciaAmostral);
    }

}

