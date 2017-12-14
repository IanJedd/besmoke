public class Valid {
    static String goodPhone;
    static String unformatted;
    public static boolean phone(String badPhone)
    {
        char c;
        String s;
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < badPhone.length(); i++)
        {
            c = badPhone.charAt(i);
            if (Character.isDigit(badPhone.charAt(i)))
            {
                s = String.valueOf(c);
                digits.append(s);
            }

        }
        unformatted = digits.toString();
        String country = "";
        String area = "";
        String first = "";
        String last = "";
        if (unformatted.length() == 11)
        {
            country = unformatted.substring(0, 1) + "-";
            unformatted = unformatted.substring(1, 11);
        }
        if (unformatted.length() == 10)
        {
            area = unformatted.substring(0, 3) + "-";
            unformatted = unformatted.substring(3, 10);
        }
        if (unformatted.length() == 7)
        {
            first = unformatted.substring(0, 3) + "-";
            last = unformatted.substring(3, 7);
            unformatted = "";
        }
        goodPhone = country + area + first + last;
        if (goodPhone.length() > 0) return true;
        else
        {
            goodPhone = "";
            return false;
        }
    }
    static String goodEmail = "";
    public static boolean email(String badEmail)
    {
        boolean at = false;
        int atIndex = 0;
        boolean dot = false;
        int dotIndex = 0;
        if (badEmail.length() >= 5)
        {
            char c;
            String s;
            for (int i = 0; i < badEmail.length(); i++)
            {
                c = badEmail.charAt(i);
                s = String.valueOf(c);
                if (s.equals("@"))
                {
                    at = true;
                    atIndex = i;
                }
                else if (s.equals("."))
                {
                    dot = true;
                    dotIndex = i;
                }
            }
            if (atIndex - dotIndex == 1 || atIndex - dotIndex == -1) {
                at = false;
                dot = false;
            }
        }
        if(at && dot)
        {
            goodEmail = badEmail;
            return true;
        }
        else return false;
    }
    public static void main(String[] args)
    {
        Valid valid = new Valid();
        String phone = "234";
        String email = "it@.com";

        System.out.println("Bad Phone " + phone);
        System.out.println(valid.phone(phone));
        System.out.println(valid.goodPhone);

        phone = "23454394854";
        System.out.println("Good Phone " + phone);
        System.out.println(valid.phone(phone));
        System.out.println(valid.goodPhone);

        System.out.println("Bad email " + email);
        System.out.println(valid.email(email));
        System.out.println(valid.goodEmail);

        email = "it@works.com";
        System.out.println("Good email " + email);
        System.out.println(valid.email(email));
        System.out.println(valid.goodEmail);
    }
}
