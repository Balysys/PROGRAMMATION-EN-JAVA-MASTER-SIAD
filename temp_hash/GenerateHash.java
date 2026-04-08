import org.mindrot.jbcrypt.BCrypt;
public class GenerateHash {
    public static void main(String[] args) {
        String[] mdp = {"hassana", "moussa123", "rachida", "koffi123", "fatima", 
                        "souleymane", "benedicte", "romain123", "aissatou", "admin123"};
        for (String m : mdp) {
            System.out.println(BCrypt.hashpw(m, BCrypt.gensalt()));
        }
    }
}
