package com.sanhuo.ucode.codetime;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.sanhuo.ucode.container.ContainerManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangzs
 * @description 右侧菜单
 * @date 2022/8/10 17:04
 **/
public class CodeTimeToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        CodeTimeToolWindow codeTimeToolWindow = new CodeTimeToolWindow(toolWindow);
        ContainerManager.putBeanIfNonExist(CodeTimeToolWindow.class, codeTimeToolWindow);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(codeTimeToolWindow.getContent(), "", false);
        codeTimeToolWindow.flush();
        toolWindow.getContentManager().addContent(content);
    }
}
