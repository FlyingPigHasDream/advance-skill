package reflect;

/**
 * @author rainyday
 * @createTime 2018/6/2.
 */
public class Person {
    public Long id;
    private String name;

    private String getSomething() {
        System.out.println("Person Class getSomething Methon.");
        return "invoke getSomething method success!";
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private Person(Long id) {
        this.id = id;
    }

    public Person() {
        this.setName("zhangsan");
        this.setId(100L);
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
