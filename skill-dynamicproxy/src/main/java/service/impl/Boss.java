package service.impl;

import service.IBoss;

/**
 * @author rainyday
 * @createTime 2018/6/2.
 */
public class Boss implements IBoss {
    public int yifu(String size) {
        System.out.println("天猫小强旗舰店，给客户发快递-- 衣服型号，" + size);
        // 衣服价格数据库里读取
        return 50;
    }

    public void kuzi() {
        System.out.println("天猫小强旗舰店，老板给客户发快递-- 裤子");
    }
}
