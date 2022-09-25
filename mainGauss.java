import java.util.*;

public class mainGauss {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Fill n");
        int n = sc.nextInt();
        double a[][], a1[][], b[], b1[];
        a = new double[n][n];
        a1 = new double[n][n];
        b = new double[n];
        b1 = new double[n];
        System.out.println("Fill matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextDouble();
                a1[i][j] = a[i][j];
            }
        }
        System.out.println("Fill vector b");
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextDouble();
            b1[i] = b[i];
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(a[i][j]+" | ");
            }
            System.out.println(b[i]);
        }
        int k = 0; // индекс диагонали (n=2 k=0; k<1 i=0; i<1 j=0; j<2)
        while (k<n-1) {
            double tmpa = 0;
            double tmpb = 0;
            for (int ij = 0; ij < n; ij++) { // проверка на ноль ik элемента и перестановка строк
                for (int i = k; i < n - 1; i++) {
                    if (a[i][k] == 0) {
                        for (int j = 0; j < n; j++) {
                            tmpa = a[i][j];
                            a[i][j] = a[i + 1][j];
                            a[i + 1][j] = tmpa;
                        }
                        tmpb = b[i];
                        b[i] = b[i + 1];
                        b[i + 1] = tmpb;
                    }
                }
            }
            if (a[k][k]==0) {
                System.out.print("Matrix is not compatibility");
                System.exit(0);
            }
            double str = a[k][k]; // делаем главный элемент = 1(делим строку на первый элемент)
            b[k] /= str;
            for (int j = 0; j < n; j++) {
                a[k][j] /= str;
            }
            for (int i = k + 1; i < n; i++) { // цикл вычитания
                if (a[i][k] != 0) {
                    str = a[i][k]; // первый элемент в строке
                    b[i] -= b[k] * str;
                    for (int j = 0; j < n; j++) {
                        a[i][j] -= a[k][j] * str;
                    }
                }
            }
            k++;
            /*for (int i1=0; i1<n; i1++) { //проверка на нулевую строку
                boolean nullstr = true;
                for (int j1=0; j1<n; j1++) {
                    if (a[i1][j1]!=0){
                        nullstr=false;
                    }
                }
                if (nullstr==true) {
                    System.out.print("Matrix is not compatibility");
                    System.exit(0);
                }
            }*/
        }
        double str = a[k][k]; // делаем последний главный элемент = 1
        b[k] /= str;
        a[k][k]/=str;
        System.out.println();
        int per;// переменная для запоминания индекса из какого эл-та будем вычитать
        for (int i=n-2; i>=0; i--) { // обратный цикл вычитания
            per=n-1;
            for (int j=n-1; j>=i+1; j--) {
                b[i]=b[i]-b[per]*a[i][j];
                a[i][j]=a[i][j]-a[per][j]*a[i][j];
                per-=1;
            }
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(a[i][j]+" | ");
            }
            System.out.println(b[i]);
        }
        System.out.println();
        for (int i=0; i<n; i++) {
            double sum =0.0;
            for (int j=0; j<n; j++){
                sum+=a1[i][j]*b[j];
            }
            System.out.print("inaccuracy is: "+Math.abs(b1[i]-sum)+" | ");
        }
        //System.out.println(0.1+0.2);
    }
}

/*
0 1 2 3
1 0 2 3
1 2 0 3
1 2 3 0
 */
