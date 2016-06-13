package ua.kpi.worker;

/**
 * Created by dmytryguly on 6/9/16.
 */
public class DataStorage {

    private static DataStorage instance = null;

    private double KQ = MathConstants.KQ;
    private double BETA = MathConstants.BETA;
    private int m_LOWER = MathConstants.m_LOWER;
    private int m_UPPER = MathConstants.m_UPPER;
    private int d_LOWER = MathConstants.d_LOWER;
    private int d_MIDDLE = MathConstants.d_MIDDLE;
    private int d_UPPER = MathConstants.d_UPPER;
    private double Uvh_LOWER = MathConstants.Uvh_LOWER;
    private double Uvh_UPPER = MathConstants.Uvh_UPPER;
    private double Uvh = MathConstants.Uvh;


    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public double getKQ() {
        return KQ;
    }

    public void setKQ(double KQ) {
        this.KQ = KQ;
    }

    public double getBETA() {
        return BETA;
    }

    public void setBETA(double BETA) {
        this.BETA = BETA;
    }

    public int getM_LOWER() {
        return m_LOWER;
    }

    public void setM_LOWER(int m_LOWER) {
        this.m_LOWER = m_LOWER;
    }

    public int getM_UPPER() {
        return m_UPPER;
    }

    public void setM_UPPER(int m_UPPER) {
        this.m_UPPER = m_UPPER;
    }

    public int getD_LOWER() {
        return d_LOWER;
    }

    public void setD_LOWER(int d_LOWER) {
        this.d_LOWER = d_LOWER;
    }

    public int getD_MIDDLE() {
        return d_MIDDLE;
    }

    public void setD_MIDDLE(int d_MIDDLE) {
        this.d_MIDDLE = d_MIDDLE;
    }

    public int getD_UPPER() {
        return d_UPPER;
    }

    public void setD_UPPER(int d_UPPER) {
        this.d_UPPER = d_UPPER;
    }

    public double getUvh_LOWER() {
        return Uvh_LOWER;
    }

    public void setUvh_LOWER(double uvh_LOWER) {
        Uvh_LOWER = uvh_LOWER;
    }

    public double getUvh_UPPER() {
        return Uvh_UPPER;
    }

    public void setUvh_UPPER(double uvh_UPPER) {
        Uvh_UPPER = uvh_UPPER;
    }

    public double getUvh() {
        return Uvh;
    }

    public void setUvh(double uvh) {
        Uvh = uvh;
    }
}
