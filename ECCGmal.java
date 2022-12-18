// assignment Advanced cryptography...
//      Apply Elgamal with elliptic curve
//
//      Name                 ID
// Ahmed mohmed Abdou      20195008
// mahmoud Atef Elsa3dni   20195007

import java.util.Scanner;

public class ECCGmal {
    public static Scanner cin = new Scanner(System.in);
    private static int a, b, prime, E, M, i, Alpha;
    public static double y, y2;
    public static int[] Zp;

    public static void prime() {
        boolean flag = false, flag2;
        do {
            System.out.println("Enter the prime num ");
            prime = cin.nextInt();

            for (int i = 2; i <= prime / 2; ++i) {
                // condition for nonprime number
                if (prime % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                System.out.println(prime + " is a prime number.");
            else {
                System.out.println(prime + " is not a prime number.");
            }
            flag2 = flag;
            flag = false;
        } while (flag2 == true);

    }

    public static void inputAB() {
        System.out.println("Enter Alpha wich is point on courve  : ");
        Alpha = cin.nextInt();
        System.out.println("Enter E : ");
        E = cin.nextInt();
        System.out.println("Enter Massage : ");
        M = cin.nextInt();
        System.out.println("Enter random i : ");
        i = cin.nextInt();
        if ((E >= 2 && E < prime) && (Alpha >= 2 && Alpha < prime)) {
            System.out.println("Alpha = " + Alpha);
            System.out.println("E = " + E);
            System.out.println("M = " + M);
            System.out.println("i = " + i);
        } else {
            System.out.println("invalid input : ");
            inputAB();
        }
    }

    public static int power(int base, int exponent) {
        int result = 0;

        if (exponent == 0) {
            result = 1;
        } else if (exponent == 1) {
            result = base;
        } else if (exponent > 1) {
            // result = 2147483647;
            result = base * power(base, exponent - 1);
        }

        int resultTemp = result;
        result = 0;
        return resultTemp;
    }

    public static void CheckEquation() {

        System.out.println("Enter a : ");
        a = cin.nextInt();
        System.out.println("Enter b : ");
        b = cin.nextInt();

        if ((a > 1 && a < prime) && (b > 1 && b < prime)) {
            int x = -64 * power(a, 3) - 432 * power(b, 2);

            if (x != 0) {
                System.out.println("the calcolation -16(4 a^3 + 27 b^2) is Equal to " + x);
                System.out.println(" so a and b are exist to be eleptic curve ");
            } else {
                System.out.println("the calcolation -16(4 a^3 + 27 b^2) is Equal to " + x);
                System.out.println("the number are not exist plz try annther numbers");
                // inputAB();
                CheckEquation();
            }

        } else {
            System.out.println("you have an error a/b on your equation..");
            CheckEquation();
        }

    }

    public static void vlazp(int prime) {
        Zp = new int[prime];
        for (int i = 0; i < prime; i++) {
            Zp[i] = i;
        }
        for (int i : Zp) {
            System.out.print(Zp[i] + " ");
        }

        System.out.println(" ");

    }

    private static int count = 0;
    private static int x = 0;

    public static void calculateEquation(int a, int b) {
        // y^2 = x^3 + ax + b
        while (x < prime) {
            if (count < 1) {
                y = power(x, 3) + (a * x) + b;
            }
            y2 = Math.sqrt(y);
            // System.out.println(y);
            int fraction = (int) y2;
            double sol2 = y2 - fraction;

            if (y2 >= 0) {
                if (sol2 == 0.00) {
                    // print positive value
                    y2 = y2 % prime;
                    System.out.print("(" + x + " , " + (int) y2 + ')' + " , ");
                    // to print negative value as "positive"
                    if (y2 > 0) {
                        int negative = prime - (int) y2;
                        System.out.println("(" + x + " , " + negative + ')');
                    }
                    x++;
                    if (count >= 1) {
                        count = 0;
                    }

                } else {
                    y = y - prime;
                    count++;
                    calculateEquation(a, b);
                }
            } else {
                System.out.println("no points on " + x);
                x++;
                if (count >= 1) {
                    count = 0;
                }
                break;
            }
        }
    }

    // public static void calculateEquation(int a, int b, int x) {
    // // y^2 = x^3 + ax + b

    // while (x < prime) {
    // if (count < 1) {
    // y = power(x, 3) + (a * x) + b;
    // }
    // y2 = Math.sqrt(y);
    // int fraction = (int) y2;
    // double sol2 = y2 - fraction;

    // if (y2 >= 0) {
    // if (sol2 == 0.00) {
    // // print positive value
    // y2 = y2 % prime;
    // System.out.print("(" + x + " , " + (int) y2 + ')' + " , ");
    // // to print negative value as "positive"
    // if (y2 > 0) {
    // int negative = prime - (int) y2;
    // System.out.println("(" + x + " , " + negative + ')');
    // }
    // if (count >= 1) {
    // count = 0;
    // }
    // break;
    // } else {
    // y = y - prime;
    // count++;
    // calculateEquation(a, b, x);
    // }
    // } else {
    // System.out.println("no points on " + x);
    // if (count >= 1) {
    // count = 0;
    // }
    // break;
    // }

    // }
    // }

    public static int Beta(int Alpha, int i, int prime) {
        int beta = 0;
        beta = (Alpha * a);

        return beta;
    }

    public static int calcy1(int Alpha, int i, int prime) {
        int y1 = 0;
        y1 = (Alpha * i);

        return y1;
    }

    public static int encryptionM(int Alpha, int M, int i, int prime) {
        int y2 = 0;
        y2 = M + (i * Beta(Alpha, i, prime));

        return y2;
    }

    public static int decryption(int Alpha, int M, int i, int prime) {
        int Dec2 = 0;
        Dec2 = encryptionM(Alpha, M, i, prime) - a * calcy1(Alpha, i, prime);

        return Dec2;

    }

    public static void basemain() {

        // System.out.println("Enter the choice 1 CLI , 2 GuI");
        // choice = cin.nextInt();
        // if (choice != 1 && choice != 2) {
        // System.out.println("invalid choice plz try agine");
        // basemain();
        // }

        prime();

        CheckEquation();

        vlazp(prime);

        System.out.println("Y^2 = X^3+aX+b");

        // System.out.println("select point");
        // int x = cin.nextInt();
        calculateEquation(a, b);

        // int q = 0;
        // System.out.println("do you wont to change point enter yes");
        // String choo = cin.nextLine();
        // if (choo == "Yes" || choo == "yes") {
        // System.out.println("enter the ");
        // q = cin.nextInt();
        // calculateEquation(a, b, q);
        // }

        System.out.println("select one point");
        inputAB();

        int beta = Beta(Alpha, i, prime);
        System.out.println("Value of Beta = " + beta);

        int finalY1 = calcy1(Alpha, i, prime);
        System.out.println("Value of Y1 = " + finalY1);

        int Encrypt = encryptionM(Alpha, M, i, prime);
        System.out.println("Value of Encrypted Massage = " + Encrypt);

        int Decrypt = decryption(Alpha, M, i, prime);
        System.out.println("Value of Decryption data = " + Decrypt);
    }

    public static void main(String[] args) {
        basemain();
    }
}
