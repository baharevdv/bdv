package com.company.its.screen.tasktype;

import io.jmix.ui.screen.*;
import com.company.its.entity.TaskType;

@UiController("its_TaskType.browse")
@UiDescriptor("task-type-browse.xml")
@LookupComponent("taskTypesTable")
public class TaskTypeBrowse extends StandardLookup<TaskType> {
}