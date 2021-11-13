@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
