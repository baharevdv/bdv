package com.company.its.screen.task;

import com.company.its.entity.MagicSquareTask;
import com.company.its.entity.TaskType;
import com.company.its.entity.TwoArraysTask;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.company.its.entity.Task;
import io.jmix.ui.upload.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

@UiController("its_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
public class TaskEdit extends StandardEditor<Task> {
    @Autowired
    private TextArea<String> resultField;
    @Autowired
    private TextArea<String> inputField;
    @Autowired
    private EntityComboBox<TaskType> typeField;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private FileStorageUploadField importBtn;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Button calculateBtn;
    @Autowired
    private Downloader downloader;
    @Autowired
    private Button exportBtn;
    @Autowired
    private ScreenBuilders screenBuilders;


    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        event.getEntity().setDate(new Date());
    }

    @Subscribe("export")
    public void onExport(Action.ActionPerformedEvent event) {
        //String taskInfoText = typeField.getValue().getName().concat("/n").concat(inputField.getValue()).getBytes();
        byte[] content = typeField.getValue().getName().concat("\n").concat(inputField.getValue()).getBytes();
        downloader.download(content, "TaskInfo.txt", DownloadFormat.TEXT);
    }

    @Subscribe("load")
    public void onLoad(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(Task.class,this)
                .withSelectHandler(tasks -> {
                    screenBuilders.editor(Task.class, this)
                            .editEntity(tasks.iterator().next())
                            .build()
                            .show();
                })
                .build()
                .show();
    }

    @Subscribe("calculate")
    public void onCalculate(Action.ActionPerformedEvent event) {
        calculate(typeField.getValue(), inputField.getValue());
    }

    private void calculate(TaskType taskType, String value) {
        if (taskType.getId().toString().equals("3fe3bd68-ded1-3af5-5ab4-0108919a165c")) {//Магический квадрат
            resultField.setValue(taskType.getName() + "\n" + MagicSquareTask.calculate( value));
        } else if (taskType.getId().toString().equals("591bbae1-5d62-8e3c-9b52-2bba2e6b65e5")) {//Two arrays of strings
            resultField.setValue(taskType.getName() + "\n" + TwoArraysTask.calculate( value));
        }
    }

    @Subscribe("importBtn")
    public void onImportBtnFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        UUID fileId = importBtn.getFileId();
        File file = temporaryStorage.getFile(fileId);
        if (file!= null) {
            processFile(file);
            temporaryStorage.deleteFile(fileId);
            calculate(typeField.getValue(), inputField.getValue());
        }else{
            notifications.create()
                    .withCaption("<code>Ошибка!</code>")
                    .withDescription("<u>Файл не найден.</u>")
                    .withType(Notifications.NotificationType.ERROR)
                    .withContentMode(ContentMode.HTML)
                    .show();
        }
    }

    private void processFile(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            String outString = "";
            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                String[] lineData = nextLine.split(" ");
                outString = outString.concat(nextLine + "\n");
            }
            inputField.setValue(outString);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Subscribe("typeField")
    public void onTypeFieldValueChange(HasValue.ValueChangeEvent<TaskType> event) {
        calculateBtn.setEnabled(!(typeField.isEmpty()|inputField.isEmpty()));
        exportBtn.setEnabled(!(typeField.isEmpty()|inputField.isEmpty()));
        importBtn.setEnabled(!typeField.isEmpty());
    }

    @Subscribe("inputField")
    public void onInputFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        calculateBtn.setEnabled(!(typeField.isEmpty()|inputField.isEmpty()));
        exportBtn.setEnabled(!(typeField.isEmpty()|inputField.isEmpty()));
    }
}