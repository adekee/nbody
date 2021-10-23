public class NBody {
    public static void main(String[] args) {
        double stoppingTime = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        int n = StdIn.readInt();
        double radius = StdIn.readDouble();
        double G = 6.67e-11;
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
        StdDraw.enableDoubleBuffering();
        StdAudio.play("2001.wav");
        for (double t = 0.0; t < stoppingTime; t += dt) {

            double[] fx = new double[n];
            double[] fy = new double[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double rx = px[j] - px[i];
                        double ry = py[j] - py[i];
                        double r = Math.sqrt(rx * rx + ry * ry);

                        double F = G * mass[i] * mass[j] / (r * r);
                        fx[i] += F * rx / r;
                        fy[i] += F * ry / r;
                        

                    }

                }
            }
            for (int i = 0; i < n; i++) {

                double ax = fx[i] / mass[i];
                double ay = fy[i] / mass[i];

                vx[i] += ax * dt;
                vy[i] += ay * dt;

                px[i] += vx[i] * dt;
                py[i] += vy[i] * dt;


            }
            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);
            }

            StdDraw.show();
            StdDraw.pause(20);
            StdDraw.picture(0, 0, "starfield.jpg");

        }
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }
    }
}

