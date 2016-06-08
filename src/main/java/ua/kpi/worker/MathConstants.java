package ua.kpi.worker;

/**
 * Created by dmytryguly on 5/19/16.
 */
public class MathConstants {
    public static final double KQ = 0.1;
    public static final double C = 0.01;
    public static final double ALPHA = 0.01;
    public static final double BETA = 1;
    public static final double EPSILON = 1500;
    public static final double e = 2.73;
    // постоянная времени элементов памяти
    //public static final double tau = 1 / wp;

    // электрика в механику, механика в электрику

    // нижняя и верхняя границы числа адресатов
    public static final int m_LOWER = 1;
    public static final int m_UPPER = 100;

    // 0.02 наносекунд
    public static final double t = 2 * Math.pow(10, -11);

    // длина элемента
    public static final int d_LOWER = 10;
    public static final int d_MIDDLE = 130;
    public static final int d_UPPER = 1000;

    // входное напряжение
    public static final double Uvh = 1;
    public static final double Uvh_LOWER = 1;
    public static final double Uvh_UPPER = 3;

    public static final String KQ_NAME = "KQ";
    public static final String C_NAME = "C";
    public static final String ALPHA_NAME = "ALPHA";
    public static final String BETA_NAME = "BETA";
    public static final String e_NAME = "e";
    public static final String Uvh_NAME = "Uvh";
    public static final String m_NAME = "m";

}
