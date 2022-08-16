package com.sanhuo.ucode.container;

import com.sanhuo.ucode.toolwindow.AbstractToolWindow;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:03
 **/
public class ToolWindowComponentContainer extends AbstractComponentContainer<AbstractToolWindow> {


    @Override
    protected AbstractToolWindow createAndReturnBean(Class<? extends AbstractToolWindow> beanClass) throws Exception {
        //do nothing
        return null;
    }

    @Override
    protected void putBeanCheck(Class<? extends AbstractToolWindow> beanClas, AbstractToolWindow bean) {
        //do nothing
    }
}
