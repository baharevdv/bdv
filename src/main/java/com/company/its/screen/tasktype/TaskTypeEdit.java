package com.company.its.screen.tasktype;

import io.jmix.ui.screen.*;
import com.company.its.entity.TaskType;

@UiController("its_TaskType.edit")
@UiDescriptor("task-type-edit.xml")
@EditedEntityContainer("taskTypeDc")
public class TaskTypeEdit extends StandardEditor<TaskType> {
}