/**
 * Created by luxin on 6/19/17.
 */

/**
 * Created by luxin on 6/19/17.
 * Email: luxin0311@live.com
 */
public class BasicTest {

    public static void main(String args[]) {

//        String[] a = {"world" , "hello"};
//        String word = "hello";
//        System.out.println(con.password.hashCode());
//        System.out.println(word.hashCode());
//        System.out.println(String.valueOf("hello".hashCode()==word.hashCode()));
//        String a = "ad123";
        String b = new String("ad123");
//        System.out.println(a == b);
        String c = b.intern();
        System.out.println(b == c);
    }
}
