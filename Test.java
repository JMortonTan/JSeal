public class Test
{
    public static void main(String[] args)
    {
        String myString = "65";
        char ch = 'A';
        
        String hex = String.format("%04x", (int) ch);
        System.out.println(hex);
        
    }
    
    
    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
            {
                hex.append(Integer.toHexString((int) chars[i]));
            }
        return hex.toString();
    }
}