package com.demo.transaction;

import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ITransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBootTransactionApplicationTests {


    /**
     *
     *
     * propagation_supports
     *
     * 表示当前方法不必需要具有一个事务上下文，但是如果有一个事务的话，它也可以在这个事务中运行
     *
     *
     * propagation_mandatory
     *
     * 表示当前方法必须在一个事务中运行，如果没有事务，将抛出异常
     *
     *
     * propagation_nested
     *
     * 表示如果当前方法正有一个事务在运行中，则该方法应该运行在一个嵌套事务中，被嵌套的事务可以独立于被封装的事务中进行提交或者回滚。
     * 如果封装事务存在，并且外层事务抛出异常回滚，那么内层事务必须回滚，反之，内层事务并不影响外层事务。如果封装事务不存在，则同propagation_required的一样
     *
     * propagation_never
     *
     * 表示当方法务不应该在一个事务中运行，如果存在一个事务，则抛出异常
     *
     *
     * propagation_requires_new
     *
     * 表示当前方法必须运行在它自己的事务中。一个新的事务将启动，而且如果有一个现有的事务在运行的话，
     * 则这个方法将在运行期被挂起，直到新的事务提交或者回滚才恢复执行。
     *
     *
     * propagation_not_supported
     *
     * 表示该方法不应该在一个事务中运行。如果有一个事务正在运行，他将在运行期被挂起，直到这个事务提交或者回滚才恢复执行
     */



    @Autowired
    private ITransactionService transactionService;


    @Test
    void noTransaction() {
        boolean flag =  transactionService.noTransaction("无事务");

    }


    @Test
    void errorNoTransaction() {
       boolean flag =  transactionService.checkErrorNoTransaction("检查异常无事务");
    }




    /**
     * propagation_required
     *
     *  表示当前方法必须在一个具有事务的上下文中运行，如有客户端有事务在进行，那么被调用端将在该事务中运行，否则的话重新开启一个事务。
     *  （如果被调用端发生异常，那么调用端和被调用端事务都将回滚）
     */
    @Test
    void propagationRequired_01() {
        boolean flag =  transactionService.propagationRequiredA("propagationRequired");

    }


    /**
     * 捕获异常上抛- 如果不使用rollback 接收异常 不会回滚
     * */
    @Test
    void propagationRequired_02() throws Exception {
        // 异常上抛
        boolean flag =  transactionService.propagationRequiredB("propagationRequired");
    }


    /**
     * propagation_supports
     *
     * 表示当前方法不必需要具有一个事务上下文，但是如果有一个事务的话，它也可以在这个事务中运行
     **/
    @Test
    void propagationSupports() throws Exception {
        // 异常上抛
        boolean flag =  transactionService.propagationSupportsA("propagationSupports");
    }





    @Test
    void rollbackTest() {
        String name = "ROLK";
        try {
            transactionService.rollback(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
