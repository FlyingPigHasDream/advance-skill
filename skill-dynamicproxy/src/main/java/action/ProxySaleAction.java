package action;

import org.junit.Test;
import proxyclass.ProxyBoss;
import service.IBoss;
import service.impl.Boss;

/**
 * @author rainyday
 * @createTime 2018/6/2.
 */
public class ProxySaleAction {

    /**
     *  使用代理，在这个代理中，只代理了Boss的yifu方法
     *  定制化服务，可以改变原接口的参数，返回值等等
     */
    @Test
    public void saleByProxy() {
        IBoss boss = ProxyBoss.getProxy(10, IBoss.class, Boss.class);
        System.out.println("代理经营！");
        int money = boss.yifu("xxl");
        System.out.println("衣服成交价格：" + money);
    }

}
