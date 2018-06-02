package reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  非私有public 的用getField ...
 *  私有用declare...
 * @author rainyday
 * @createTime 2018/6/2.
 */
public class MyReact {
    public String className = null;
    public Class personClass = null;

    /**
     * 反射Person类
     * @throws ClassNotFoundException
     */
    @Before
    public void init() throws ClassNotFoundException {
        className = "reflect.Person";
        personClass = Class.forName(className);
    }

    /**
     *  获取某个Class文件对象
     */
    @Test
    public void getClassName() {
        System.out.println(personClass);
    }

    /**
     *  已经拥有了在获取对象
     */
    @Test
    public void getClassName2() {
        System.out.println(Person.class);
    }

    /**
     * 创建一个Class文件表示的实例对象，底层会调用无参数的构造方法
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void getNewInstance() throws IllegalAccessException, InstantiationException {
        System.out.println(personClass.newInstance());
    }

    /**
     * 获得非私有构造函数
     */
    @Test
    public void getPublicConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Person person = (Person)constructor.newInstance(100L, "zhangsan");
        System.out.println(person.getId());
        System.out.println(person.getName());
    }

    /**
     *  获得私有的构造函数
     */
    @Test
    public void getPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor con = personClass.getDeclaredConstructor(Long.class);
        // 强制取消Java的权限检测
        con.setAccessible(true);
        Person pereson = (Person)con.newInstance(100L);
        System.out.println("id = " + pereson.getId());
    }

    /**
     *  访问非私有成员变量
     */
    @Test
    public void getNotPrivateField() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor con = personClass.getConstructor(Long.class, String.class);
        Object obj = con.newInstance(100L, "zhangsan");

        Field field = personClass.getField("id");
        field.set(obj, 100L);
    }

    /**
     *  获取私有的成员变量
     */
    @Test
    public void getPrivateField() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = personClass.getDeclaredConstructor(Long.class);
        constructor.setAccessible(true);
        Object obj = constructor.newInstance(100L);

        Field field = personClass.getDeclaredField("name");
        // 强制取消Java的权限检测
        field.setAccessible(true);
        field.set(obj, "zhangsan");
        System.out.println(field.get(obj));
    }

    /**
     *  获取非私有的成员函数
     */
    @Test
    public void getNotPrivateMethod() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println(personClass.getMethod("toString"));

        Object obj = personClass.newInstance();
        Object object = personClass.getMethod("toString").invoke(obj);
        System.out.println(object);
    }

    /**
     *  获取私有成员函数
     */
    @Test
    public void getPrivateMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = personClass.newInstance();
        Method method = personClass.getDeclaredMethod("getSomething");
        method.setAccessible(true);
        Object value = method.invoke(obj);
        System.out.println(value);
    }

    /**
     *  反射其他方法
     */
    @Test
    public void otherMethod() throws ClassNotFoundException {
        // 获取加载这个class文件的类加载器对象
        System.out.println(personClass.getClassLoader());
        // 获取某个类实现的所有接口
        Class[] interfaces = personClass.getInterfaces();
        for (Class class1 : interfaces) {
            System.out.println(class1);
        }
        // 反射当前类的直接父类
        System.out.println(personClass.getGenericSuperclass());

        // path "/"开头表示当前类目录下取资源， 不以"/"开头表示从classpath下取
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));
        System.out.println(personClass.getResourceAsStream("log4j.properties"));

        // 判断当前Class是否是数组
        System.out.println(personClass.isArray());
        System.out.println(new String[3].getClass().isArray());

        // 判断当前的Class对象表示是否是枚举
        System.out.println(personClass.isEnum());
        System.out.println(Class.forName("reflect.Person").isEnum());

        // 判断当前的Class是否是接口
        System.out.println(personClass.isInterface());
        System.out.println(Class.forName("reflect.Person").isInterface());
        
    }

}
