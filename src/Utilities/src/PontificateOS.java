public class PontificateOS
{
    static boolean windows = false;
    static boolean linux = false;
    static boolean mac = false;

    static
    {
        String os = System.getProperty("os.name").toLowerCase();
        windows = os.contains("win");
        linux = os.contains("nux") || os.contains("nix");
        mac = os.contains("mac");
    }
    public static void main(String[] string)
    {
        PontificateOS p = new PontificateOS();
        System.out.println(p.windows);
    }
}