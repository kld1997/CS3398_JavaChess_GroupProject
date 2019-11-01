
import java.util.Vector;

public class Profile {
    private String username;
    private int score;
    private int rank;

    Vector profiles = new Vector();

    public Profile(String username) {
        this.username = username;
        score = 0;
        rank = 0;
    }

    public void addProfile(Profile p) {
        profiles.add(p);
    }

    public void findProfile(String usernameToFind, Profile p) {
        for (int i = 0; i < profiles.size(); i++ ) {
            if (p.username == usernameToFind) {
            }
            }
        }

}
