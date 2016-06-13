package ua.kpi.worker;

import java.util.ArrayList;
import java.util.List;

import static ua.kpi.worker.MathConstants.BETA;
import static ua.kpi.worker.MathConstants.e;

/**
 * Created by dmytryguly on 5/18/16.
 */

public class MathWorker {

    private DataStorage dataStorage;

    public MathWorker(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public List<Integer> getTimeForChart() {
        List<Integer> time = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            time.add(i);
        }
        return time;
    }


    // Ua = (KQ * Qvh / m * C) * (1 - e^(-t/tau))
    // Qvh = Uvh * beta * C
    // 0.5 < Uvh < 5

    /**
     * @param Uvh < 3, Uvh > 1
     * @param t
     * @param m   - число адресов
     * @return
     */
    public double calculateUa(double Uvh, double t, double m, int d) {
        return (dataStorage.getKQ() * Uvh * BETA / m) * (1 - Math.pow(e, (-t / getTau(d))));
    }

    public double calculateUb(double Uvh, double t, double m, int d) {
        return -calculateUa(Uvh, t, m, d);
    }

    public double getTau(int d) {
        return d * Math.pow(10, -12);
    }

    public double calculateFread(double d) {
        return (1.0 / (4 * getTau((int) d))) * Math.pow(10, -9);
    }

    public List<Double> getMs() {
        List<Double> ms = new ArrayList<>();
        for (int i = dataStorage.getM_LOWER(); i <= dataStorage.getM_UPPER(); i++) {
            ms.add((double) i);
        }
        return ms;
    }

    public List<Double> getDs() {
        List<Double> ds = new ArrayList<>();
        for (int i = dataStorage.getD_LOWER(); i <= dataStorage.getD_MIDDLE(); i++) {
            ds.add((double) i);
        }
        return ds;
    }


    public List<Double> getUas(double Uvh, double t, int d) {
        List<Double> uas = new ArrayList<>();
        List<Double> ms = getMs();
        ms.forEach(m -> {
            uas.add(calculateUa(Uvh, t, m, d));
        });
        return uas;
    }

    public List<Double> getUbs(double Uvh, double t, int d) {
        List<Double> uas = new ArrayList<>();
        List<Double> ms = getMs();
        ms.forEach(m -> uas.add(calculateUb(Uvh, t, m, d)));
        return uas;
    }

    public List<Double> getFreads() {
        List<Double> freads = new ArrayList<>();
        List<Double> ds = getDs();
        ds.forEach(d -> freads.add(calculateFread(d)));
        return freads;
    }
}
