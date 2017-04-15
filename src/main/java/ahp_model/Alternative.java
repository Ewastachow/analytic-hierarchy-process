package ahp_model;

/**
 * Created by yevvy on 26/03/2017.
 */
public class Alternative  implements Element{

    public int id;

    public String name;

    public Alternative(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }

}
